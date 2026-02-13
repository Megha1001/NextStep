1. Problem 1: Sum of Uppercase and Lowercase Characters

You are given a string consisting of alphabets and other characters.
The task was to calculate the sum of ASCII values of all lowercase and uppercase letters separately.

```

import java.util.*;

class Main {
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        
        int[] res = computeAscii(input);
        
    }
    
    public static int[] computeAscii(String input){
        int lowerCaseSum = 0;
        int upperCaseSum = 0;
        
        for(char ch : input.toCharArray()){
            if(Character.isLowerCase(ch)){
                lowerCaseSum += ch;
            }else if(Character.isUpperCase(ch)){
                upperCaseSum += ch;
            }
        }
        
        return new int[]{lowerCaseSum, upperCaseSum};
        
    }

}
```

2. Easy — Problem 2: The Bird and the Trees
This one was more of a simulation-based problem.

You are given an array of integers, where: Each index represents a tree, and the value at that index represents the number of branches on that tree.

A bird starts from a given position, where the value is always 0.
It first moves right, collecting all branches from the first non-zero tree it encounters, and then returns to the starting position.
Next, it moves left, following the same rule.

The bird alternates between right and left directions, continuing this process until it collects at least 100 branches in total.
You need to determine how many trips it takes for the bird to reach this target.

This problem tested array traversal, directional iteration, and simulation logic.