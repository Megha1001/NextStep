# Roku prep — Coding solutions (Java)

Every coding problem reported in Roku interviews over the last two years, with a
worked Java solution, the reasoning to say out loud, complexity, and the
follow-ups interviewers actually ask.

Roku uses HackerRank or CoderPad and expects **runnable, debuggable code**. Write
real Java with imports in your head, handle nulls and empty inputs, and state
complexity before they ask.

---

## Contents

1. [LRU Cache](#1-lru-cache)
2. [Max Stack (thread-safe)](#2-max-stack-thread-safe)
3. [Deep clone a directed graph](#3-deep-clone-a-directed-graph)
4. [First non-repeating character in a stream](#4-first-non-repeating-character-in-a-stream)
5. [Top K Frequent Elements](#5-top-k-frequent-elements)
6. [Substring with Concatenation of All Words](#6-substring-with-concatenation-of-all-words)
7. [Peak element (modified binary search)](#7-peak-element-modified-binary-search)
8. [ASCII string to integer (atoi)](#8-ascii-string-to-integer-atoi)
9. [Anagram detection in constant space](#9-anagram-detection-in-constant-space)
10. [Best time to buy and sell stock](#10-best-time-to-buy-and-sell-stock)
11. [Max Area of Island](#11-max-area-of-island)
12. [Minimum Cost for Tickets](#12-minimum-cost-for-tickets)
13. [0/1 Knapsack](#13-01-knapsack)
14. [Sort a singly linked list](#14-sort-a-singly-linked-list)
15. [Detect a cycle in a linked list](#15-detect-a-cycle-in-a-linked-list)
16. [Reverse a linked list and a sub-segment](#16-reverse-a-linked-list-and-a-sub-segment)
17. [FIFO ring buffer with IsFIFOFull](#17-fifo-ring-buffer-with-isfifofull)
18. [Race condition: debug and fix](#18-race-condition-debug-and-fix)

---

## 1. LRU Cache

**The most repeated Roku question.** Practise until you can write it in eight
minutes without thinking.

### Approach

You need O(1) lookup and O(1) eviction of the least recently used entry. A hash
map gives you the lookup. A doubly linked list gives you O(1) removal from the
middle and O(1) insertion at the head. The map stores key to node, so you can
find a node and unlink it in constant time.

Use sentinel head and tail nodes. They remove every null check from the unlink
and insert paths, which is where people introduce bugs under pressure.

```java
public class LRUCache {

    private static final class Node {
        final int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final java.util.Map<Integer, Node> map;
    private final Node head; // sentinel: head.next is most recently used
    private final Node tail; // sentinel: tail.prev is least recently used

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.map = new java.util.HashMap<>(capacity * 2);
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node existing = map.get(key);
        if (existing != null) {
            existing.value = value;
            moveToFront(existing);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;
            unlink(lru);
            map.remove(lru.key);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        insertAtFront(node);
    }

    private void moveToFront(Node node) {
        unlink(node);
        insertAtFront(node);
    }

    private void unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
```

**Complexity.** O(1) for both operations. O(capacity) space.

**Say this out loud:** why the list must be doubly linked (you need the
predecessor to unlink in O(1)), and why sentinels simplify the edge cases.

### Follow-ups they ask

- *Make it thread-safe.* Simplest correct answer is a single `ReentrantLock`
  around both methods, since even `get` mutates the list. Mention that
  `ConcurrentHashMap` alone is not enough because the linked list is shared
  mutable state. If they push on contention, talk about striping the cache into
  N independent shards keyed by `hash(key) % N`, each with its own lock.
- *Add TTL.* Store an expiry timestamp on the node, check it on read and treat
  expired as a miss, plus a background sweeper for entries never read again.
- *Why not `LinkedHashMap`?* You can — `new LinkedHashMap<>(cap, 0.75f, true)`
  with `removeEldestEntry` overridden gives access-order LRU in five lines.
  Mention it, then write the manual version, because they want to see the
  pointer work.

---

## 2. Max Stack (thread-safe)

Reported as "design a custom Max Stack that handles concurrency issues".

### Approach

A stack that also returns the maximum in O(1). Keep a second stack holding the
running maximum: every push records `max(newValue, currentMax)`. Both stacks pop
together, so the max stack always reflects the current contents.

For thread safety, guard every operation with one lock. A max stack has an
invariant spanning two structures, so per-structure concurrency does not help —
you need the pair to move atomically.

```java
public class MaxStack {

    private final java.util.Deque<Integer> values = new java.util.ArrayDeque<>();
    private final java.util.Deque<Integer> maxes = new java.util.ArrayDeque<>();
    private final java.util.concurrent.locks.ReentrantLock lock =
            new java.util.concurrent.locks.ReentrantLock();

    public void push(int value) {
        lock.lock();
        try {
            values.push(value);
            maxes.push(maxes.isEmpty() ? value : Math.max(value, maxes.peek()));
        } finally {
            lock.unlock();
        }
    }

    public int pop() {
        lock.lock();
        try {
            if (values.isEmpty()) {
                throw new java.util.NoSuchElementException("stack is empty");
            }
            maxes.pop();
            return values.pop();
        } finally {
            lock.unlock();
        }
    }

    public int peek() {
        lock.lock();
        try {
            if (values.isEmpty()) {
                throw new java.util.NoSuchElementException("stack is empty");
            }
            return values.peek();
        } finally {
            lock.unlock();
        }
    }

    public int max() {
        lock.lock();
        try {
            if (maxes.isEmpty()) {
                throw new java.util.NoSuchElementException("stack is empty");
            }
            return maxes.peek();
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return values.isEmpty();
        } finally {
            lock.unlock();
        }
    }
}
```

**Complexity.** O(1) per operation, O(n) extra space.

**Points to make:** the `finally` block matters — an exception inside the
critical section must not leak the lock. And `isEmpty()` is only useful as a
hint in concurrent code, since the answer can change before the caller acts on
it; that is why `pop` throws rather than relying on callers checking first.

**Follow-up:** *popMax()* — remove and return the maximum element, not just the
top. That needs a `TreeMap<Integer, List<Node>>` plus a doubly linked list, and
becomes O(log n). Know that it exists; you probably will not be asked to write
it.

---

## 3. Deep clone a directed graph

### Approach

Depth-first traversal with a map from original node to cloned node. The map does
double duty: it is the visited set (preventing infinite loops on cycles) and the
lookup that lets two different parents point at the same clone.

The single most common bug is creating the clone *after* recursing. Create the
clone and put it in the map first, then recurse into the neighbours.

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphCloner {

    public static class Node {
        public int val;
        public List<Node> neighbors = new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }
    }

    public Node cloneGraph(Node start) {
        if (start == null) {
            return null;
        }
        return dfs(start, new HashMap<>());
    }

    private Node dfs(Node original, Map<Node, Node> cloned) {
        Node existing = cloned.get(original);
        if (existing != null) {
            return existing;
        }
        Node copy = new Node(original.val);
        cloned.put(original, copy); // register before recursing, or cycles loop forever
        for (Node neighbor : original.neighbors) {
            copy.neighbors.add(dfs(neighbor, cloned));
        }
        return copy;
    }
}
```

**Complexity.** O(V + E) time, O(V) space for the map plus recursion stack.

**Follow-ups.** *Avoid stack overflow on a deep graph* — convert to an iterative
BFS with an explicit queue, same map. *Disconnected graph* — this only clones
the component reachable from `start`; if the input is a list of nodes, loop over
all of them sharing one map. *Identity vs equality* — `HashMap<Node, Node>` uses
`equals`/`hashCode`; if `Node` overrides those by value, two distinct nodes with
the same value collapse into one. Use `IdentityHashMap` to be safe, and say so.

---

## 4. First non-repeating character in a stream

Reported as "optimise for read performance over a massive stream".

### Approach

The read must be O(1), so you cannot rescan on each query. Keep a
`LinkedHashMap` of candidate characters in arrival order plus a count array.
When a character arrives for the second time, evict it from the candidate map
permanently. The head of the map is always the answer.

```java
public class FirstNonRepeating {

    private final int[] counts = new int[Character.MAX_VALUE + 1];
    private final java.util.LinkedHashMap<Character, Boolean> candidates =
            new java.util.LinkedHashMap<>();

    public void accept(char c) {
        counts[c]++;
        if (counts[c] == 1) {
            candidates.put(c, Boolean.TRUE);
        } else {
            candidates.remove(c); // once repeated, never a candidate again
        }
    }

    /** Returns the first non-repeating character so far, or '\0' if none. */
    public char firstNonRepeating() {
        java.util.Iterator<Character> it = candidates.keySet().iterator();
        return it.hasNext() ? it.next() : '\0';
    }
}
```

**Complexity.** O(1) amortised per character, O(1) for the query. Space is
bounded by the alphabet, not the stream length — that is the key insight to
state, since the stream is described as massive.

**Follow-up.** *Unbounded alphabet, for example Unicode or words instead of
characters* — swap the count array for a `HashMap<String, Integer>`; space then
grows with distinct tokens, and you would bound it with a sketch if that is too
large.

---

## 5. Top K Frequent Elements

### Approach

Count with a map, then select the top K. Two options worth mentioning:

A min-heap of size K gives O(n log k) and is the standard answer. Bucket sort by
frequency gives O(n), because frequency is bounded by n — buckets indexed 1..n,
walk down from the end. Offer the heap, then mention the bucket version as the
linear improvement. That contrast is what scores.

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {

    /** O(n log k) — min-heap of size k. */
    public int[] byHeap(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) {
            counts.merge(n, 1, Integer::sum);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            heap.offer(e);
            if (heap.size() > k) {
                heap.poll(); // drop the smallest count
            }
        }
        int[] result = new int[heap.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = heap.poll().getKey();
        }
        return result;
    }

    /** O(n) — bucket by frequency, since frequency <= n. */
    public int[] byBuckets(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) {
            counts.merge(n, 1, Integer::sum);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            int freq = e.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(e.getKey());
        }
        int[] result = new int[k];
        int idx = 0;
        for (int freq = buckets.length - 1; freq >= 1 && idx < k; freq--) {
            if (buckets[freq] == null) {
                continue;
            }
            for (int value : buckets[freq]) {
                if (idx == k) {
                    break;
                }
                result[idx++] = value;
            }
        }
        return result;
    }
}
```

**Follow-up that Roku actually cares about:** *what if the data does not fit on
one machine, or it is a stream?* This is the bridge to the Top N Words design
question. Answer: partition by key, compute local top K per shard, merge the
partial results — exact if you partition by key, approximate if you partition by
volume. For a stream with limited memory, use Count-Min Sketch for frequency
estimates plus a heap of heavy hitters, and be explicit that you are trading
exactness for bounded memory.

---

## 6. Substring with Concatenation of All Words

The hard one. All words are the same length, which is the whole trick.

### Approach

Let `wordLen` be the word length, `numWords` the count, `windowLen` their
product. A naive scan checks every index — O(n × numWords × wordLen).

Better: because every word has the same length, only `wordLen` distinct
alignments exist. For each offset `0..wordLen-1`, slide a window forward in
steps of `wordLen`, maintaining a count map. When a word overflows its allowed
count, shrink from the left until it does not. Each character is visited a
constant number of times per offset.

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcatenatedSubstrings {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }
        int wordLen = words[0].length();
        int numWords = words.length;
        int windowLen = wordLen * numWords;
        if (s.length() < windowLen) {
            return result;
        }

        Map<String, Integer> need = new HashMap<>();
        for (String w : words) {
            need.merge(w, 1, Integer::sum);
        }

        for (int offset = 0; offset < wordLen; offset++) {
            Map<String, Integer> window = new HashMap<>();
            int count = 0;
            int left = offset;
            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);
                if (!need.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }
                window.merge(word, 1, Integer::sum);
                count++;
                while (window.get(word) > need.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);
                    window.merge(leftWord, -1, Integer::sum);
                    count--;
                    left += wordLen;
                }
                if (count == numWords) {
                    result.add(left);
                    String leftWord = s.substring(left, left + wordLen);
                    window.merge(leftWord, -1, Integer::sum);
                    count--;
                    left += wordLen;
                }
            }
        }
        return result;
    }
}
```

**Complexity.** O(wordLen × n) time, which for fixed word length is linear in
the string. O(numWords) space.

**State the invariant out loud:** the window always holds at most the required
count of each word, so `count == numWords` is both necessary and sufficient for
a match.

---

## 7. Peak element (modified binary search)

### Approach

A peak is any element strictly greater than both neighbours, with out-of-bounds
treated as negative infinity. Binary search works even though the array is
unsorted: if `nums[mid] < nums[mid + 1]` then the right half must contain a peak
(the sequence is rising, and it must eventually stop rising or hit the boundary).
Otherwise the left half, including mid, must contain one.

```java
public class PeakElement {

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("array must be non-empty");
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2; // avoids overflow
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1; // rising: a peak exists to the right
            } else {
                hi = mid;     // falling or flat-descending: peak at mid or left
            }
        }
        return lo;
    }
}
```

**Complexity.** O(log n) time, O(1) space.

**Things to say:** why `mid = lo + (hi - lo) / 2` rather than `(lo + hi) / 2` —
integer overflow on large arrays, and Roku is precise about this kind of thing.
Why the loop terminates — the range strictly shrinks each iteration. And why
`hi = mid` rather than `hi = mid - 1` — mid itself may be the peak.

**Follow-up.** *Duplicates* — the guarantee breaks; with plateaus you can be
forced into a linear scan in the worst case. Say that rather than pretending
binary search still works.

---

## 8. ASCII string to integer (atoi)

Deceptively fiddly. They are testing whether you handle overflow correctly, not
whether you can loop over characters.

### Approach

Skip leading whitespace, read an optional sign, consume digits, stop at the
first non-digit. Detect overflow *before* it happens by comparing against
`Integer.MAX_VALUE / 10`, and clamp to MIN or MAX like the C standard does.

```java
public class Atoi {

    public int atoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int i = 0;
        int n = s.length();

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i == n) {
            return 0;
        }

        int sign = 1;
        char c = s.charAt(i);
        if (c == '+' || c == '-') {
            sign = (c == '-') ? -1 : 1;
            i++;
        }

        int result = 0;
        while (i < n) {
            char digit = s.charAt(i);
            if (digit < '0' || digit > '9') {
                break;
            }
            int d = digit - '0';
            // check before multiplying, never after
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && d > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + d;
            i++;
        }
        return sign * result;
    }
}
```

**Complexity.** O(n) time, O(1) space.

**Edge cases to name unprompted:** empty and whitespace-only input, a lone sign
with no digits, leading zeros, `"-2147483648"` (which is why you accumulate as a
positive and clamp rather than negating at the end), and trailing garbage such
as `"42abc"`.

---

## 9. Anagram detection in constant space

### Approach

For a fixed lowercase alphabet, a 26-element count array is constant space.
Increment for the first string, decrement for the second, then check all zeros.
Length mismatch is an instant no.

```java
public class AnagramCheck {

    public boolean isAnagram(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        int[] counts = new int[26];
        for (int i = 0; i < a.length(); i++) {
            counts[a.charAt(i) - 'a']++;
            counts[b.charAt(i) - 'a']--;
        }
        for (int c : counts) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
```

**Complexity.** O(n) time, O(1) space — 26 ints regardless of input size.

**The follow-up is the real question: "how would you scale this?"** Say: for
Unicode, the count array becomes a `HashMap<Integer, Integer>` keyed by code
point, and space is no longer constant. For grouping millions of words into
anagram classes, compute a canonical key per word (sorted characters, or a
frequency signature) and group by it — that is a map-reduce shaped problem,
partitioned by the canonical key. For very long strings, sorting is O(n log n)
and counting is O(n), so counting wins.

---

## 10. Best time to buy and sell stock

### Approach

Single pass. Track the minimum price seen so far; at each step the best sale
today is `price - minSoFar`.

```java
public class StockProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int minSoFar = prices[0];
        int best = 0;
        for (int i = 1; i < prices.length; i++) {
            best = Math.max(best, prices[i] - minSoFar);
            minSoFar = Math.min(minSoFar, prices[i]);
        }
        return best;
    }
}
```

**Complexity.** O(n) time, O(1) space.

**Follow-ups.** Unlimited transactions — sum every positive daily delta. At most
two transactions — four-state DP (`buy1, sell1, buy2, sell2`). With a cooldown
or a fee — same state machine with an extra state or a subtraction. Know the
shape of the state machine; they rarely make you write all of them.

---

## 11. Max Area of Island

### Approach

Grid DFS. For each unvisited land cell, flood-fill and measure. Mutating the
grid to mark visited is O(1) space beyond recursion — but say out loud that you
are destroying the input, and offer a separate visited array if that matters.

```java
public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int best = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    best = Math.max(best, fill(grid, r, c));
                }
            }
        }
        return best;
    }

    private int fill(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 0; // mark visited
        return 1
                + fill(grid, r + 1, c)
                + fill(grid, r - 1, c)
                + fill(grid, r, c + 1)
                + fill(grid, r, c - 1);
    }
}
```

**Complexity.** O(rows × cols) time; recursion depth up to the size of the
largest island.

**Follow-up.** *Very large grid, stack overflow risk* — convert to iterative BFS
with an `ArrayDeque`. *Distributed across machines* — partition the grid into
tiles, compute island fragments per tile, then union-find across tile borders.
That answer lands well because it shows you think past the single-machine case.

---

## 12. Minimum Cost for Tickets

### Approach

Classic one-dimensional DP over days. `dp[d]` is the minimum cost to cover all
travel up to day `d`. If you do not travel on day `d`, the cost is `dp[d - 1]`.
Otherwise take the best of buying a 1-, 7- or 30-day pass ending on that day.

```java
public class MinCostTickets {

    public int mincostTickets(int[] days, int[] costs) {
        if (days == null || days.length == 0) {
            return 0;
        }
        int lastDay = days[days.length - 1];
        boolean[] travel = new boolean[lastDay + 1];
        for (int d : days) {
            travel[d] = true;
        }
        int[] dp = new int[lastDay + 1];
        for (int d = 1; d <= lastDay; d++) {
            if (!travel[d]) {
                dp[d] = dp[d - 1];
                continue;
            }
            dp[d] = Math.min(
                    dp[d - 1] + costs[0],
                    Math.min(
                            dp[Math.max(0, d - 7)] + costs[1],
                            dp[Math.max(0, d - 30)] + costs[2]));
        }
        return dp[lastDay];
    }
}
```

**Complexity.** O(lastDay) time and space.

**Say this:** the `Math.max(0, ...)` handles passes that start before day one.
And if `lastDay` were huge but travel days sparse, you would index the DP by
travel-day rather than calendar-day and binary search for the window start —
O(n log n) in the number of travel days. Offering that shows you noticed the
dependence on the calendar range rather than the input size.

---

## 13. 0/1 Knapsack

### Approach

`dp[w]` is the best value achievable with capacity `w`. Iterate items in the
outer loop and capacity **descending** in the inner loop — descending is what
makes it 0/1 rather than unbounded, because it prevents reusing the same item
twice within one pass.

```java
public class Knapsack {

    /** Each item may be taken at most once. */
    public int maxValue(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || weights.length != values.length) {
            throw new IllegalArgumentException("weights and values must align");
        }
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int w = capacity; w >= weights[i]; w--) { // descending == 0/1
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        return dp[capacity];
    }
}
```

**Complexity.** O(n × capacity) time, O(capacity) space.

**The one thing to say:** flipping the inner loop to ascending turns this into
the unbounded knapsack, where each item can be reused. Explaining *why* is a
strong signal — most candidates memorise the direction without understanding it.

---

## 14. Sort a singly linked list

Asked on a whiteboard with "explain the time complexity".

### Approach

Merge sort. Quicksort on a singly linked list is awkward (no random access, bad
pivots), and merge sort needs no random access at all — split with slow/fast
pointers, sort both halves, merge.

```java
public class LinkedListSort {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = splitInHalf(head);
        ListNode left = sort(head);
        ListNode right = sort(mid);
        return merge(left, right);
    }

    /** Splits the list in two and returns the head of the second half. */
    private ListNode splitInHalf(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // start ahead so slow lands on the left-middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = slow.next;
        slow.next = null; // cut
        return second;
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { // <= keeps the sort stable
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }
}
```

**Complexity.** O(n log n) time. Space is O(log n) for the recursion stack — not
O(n), because unlike array merge sort you relink nodes instead of copying. That
distinction is exactly what the "explain the complexity" part is fishing for.

**Follow-up.** *True O(1) space* — bottom-up merge sort with increasing run
sizes, no recursion. Mention it exists.

---

## 15. Detect a cycle in a linked list

### Approach

Floyd's tortoise and hare. Slow moves one, fast moves two. If they ever meet
there is a cycle; if fast reaches null there is not.

To find the cycle's start: after they meet, reset one pointer to the head and
advance both one step at a time. They meet at the entry point.

```java
public class CycleDetection {

    public static class ListNode {
        int val;
        ListNode next;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /** Returns the node where the cycle begins, or null if there is no cycle. */
    public ListNode cycleStart(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode walker = head;
                while (walker != slow) {
                    walker = walker.next;
                    slow = slow.next;
                }
                return walker;
            }
        }
        return null;
    }
}
```

**Complexity.** O(n) time, O(1) space.

**Be ready to justify the cycle-start trick.** If the tail before the loop has
length `a` and the meeting point is `b` nodes into a loop of length `c`, then the
slow pointer has travelled `a + b` and the fast one `a + b + kc`. Since fast
travelled twice as far, `a + b = kc`, so `a = kc - b` — the distance from the
head to the entry equals the distance from the meeting point to the entry, going
forward. Interviewers love this because most candidates memorise it without
being able to derive it.

---

## 16. Reverse a linked list and a sub-segment

### Approach

Full reversal is the three-pointer walk. Reversing positions `left..right` is the
same walk, done in place, with a dummy node so that `left == 1` needs no special
case.

```java
public class ListReversal {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /** Reverses positions left..right (1-indexed, inclusive). */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left >= right) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode beforeSegment = dummy;
        for (int i = 1; i < left; i++) {
            beforeSegment = beforeSegment.next;
        }

        // head-insertion: repeatedly move the node after `curr` to the front
        ListNode curr = beforeSegment.next;
        for (int i = 0; i < right - left; i++) {
            ListNode moved = curr.next;
            curr.next = moved.next;
            moved.next = beforeSegment.next;
            beforeSegment.next = moved;
        }
        return dummy.next;
    }
}
```

**Complexity.** O(n) time, O(1) space.

**Why the dummy node:** if `left == 1` there is no predecessor to reattach to,
and every candidate who skips the dummy writes a null-pointer bug there. Say
that as you write it.

---

## 17. FIFO ring buffer with IsFIFOFull

Reported as an embedded-flavoured question: implement a character FIFO and the
full check.

### Approach

A circular buffer with head and tail indices. Full and empty look identical when
you only track two indices, so you need a tiebreaker. Two standard options:
keep an explicit `size` counter, or waste one slot so that full means
`(tail + 1) % capacity == head`. The counter version is clearer; mention the
other because embedded interviewers expect you to know it.

```java
public class CharFifo {

    private final char[] buffer;
    private int head; // read index
    private int tail; // write index
    private int size;

    public CharFifo(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.buffer = new char[capacity];
    }

    public boolean isFifoFull() {
        return size == buffer.length;
    }

    public boolean isFifoEmpty() {
        return size == 0;
    }

    /** Returns false if the buffer is full rather than throwing — typical embedded style. */
    public boolean put(char c) {
        if (isFifoFull()) {
            return false;
        }
        buffer[tail] = c;
        tail = (tail + 1) % buffer.length;
        size++;
        return true;
    }

    public char get() {
        if (isFifoEmpty()) {
            throw new java.util.NoSuchElementException("fifo is empty");
        }
        char c = buffer[head];
        head = (head + 1) % buffer.length;
        size--;
        return c;
    }
}
```

**Complexity.** O(1) per operation, fixed O(capacity) memory with no allocation
after construction — the point of a ring buffer in embedded contexts.

**Follow-up: single-producer single-consumer without locks.** Drop the shared
`size` counter (it is written by both sides), use the wasted-slot convention,
and make `head` and `tail` volatile. The producer only writes `tail`, the
consumer only writes `head`, so no lock is needed. Getting this right is a
strong signal — say explicitly that the lock-free property depends on exactly
one writer per variable.

---

## 18. Race condition: debug and fix

They give you broken code and ask you to spot the bug.

### The bug

```java
// BROKEN
public class Counter {
    private int count = 0;

    public void increment() {
        count++; // read, add, write — three operations, not one
    }

    public int get() {
        return count;
    }
}
```

`count++` is a read-modify-write. Two threads can read the same value, both add
one, and both write back the same result — one increment is lost. There is also
a visibility problem: without synchronisation there is no happens-before edge,
so a reader may see a stale value indefinitely.

### Three correct fixes, in increasing order of sophistication

```java
// 1. synchronized — correct, simple, contended
public class SynchronizedCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int get() {
        return count;
    }
}

// 2. AtomicInteger — lock-free CAS, best default for a single counter
public class AtomicCounter {
    private final java.util.concurrent.atomic.AtomicInteger count =
            new java.util.concurrent.atomic.AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }

    public int get() {
        return count.get();
    }
}

// 3. LongAdder — striped counters, far better under heavy write contention
public class AdderCounter {
    private final java.util.concurrent.atomic.LongAdder count =
            new java.util.concurrent.atomic.LongAdder();

    public void increment() {
        count.increment();
    }

    public long get() {
        return count.sum(); // approximate while writes are in flight
    }
}
```

**Points that separate a good answer from a great one.** `volatile` alone does
*not* fix this — it gives visibility but not atomicity, and `count++` is still
three operations. Say that explicitly, because offering `volatile` as the fix is
the classic wrong answer. `AtomicInteger` is the right default. `LongAdder` wins
when many threads increment and reads are rare, which is exactly the ad
frequency-capping counter shape — a nice link to make if the design round
already covered it.

### Related concurrency questions they ask

**Threads vs processes.** Processes have separate address spaces and are
isolated by the OS; threads share heap and file descriptors within one process,
so context switching between them is cheaper but they can corrupt each other's
data. Threads share, processes do not — that is the whole answer, and everything
else follows.

**Preventing deadlock.** Four conditions must all hold: mutual exclusion, hold
and wait, no preemption, circular wait. Break any one. In practice you break
circular wait by imposing a global lock ordering, and you break hold-and-wait
with `tryLock` plus a timeout and backoff.

**Mutex vs semaphore vs spinlock.** A mutex has an owner and provides mutual
exclusion for one thread; only the owner may unlock it. A semaphore is a counter
permitting N holders and has no ownership, so it is really a signalling
primitive. A spinlock busy-waits instead of sleeping — right only when the
critical section is shorter than the cost of a context switch, and typically
used in kernel or interrupt contexts where you cannot sleep at all.

**What happens when a process allocates heap memory.** The allocator (malloc, or
the JVM's allocator) first tries to satisfy the request from memory it already
holds. If it cannot, it asks the kernel for more via `brk`/`sbrk` for small
increments or `mmap` for large ones. The kernel extends the virtual address
mapping but typically does not allocate physical pages yet. The first write to a
page triggers a page fault, and only then does the kernel map a physical frame.
So allocation is lazy, and "allocated" memory is not resident until touched.
