package hashing.countDistinctElementsInEveryWindow;

/*
 * IDEA
 * 1. Maintain a frequency map of first k elements
 * 2. for(i=k ; i<n; i++)
 *      a. decrease the freq of a[i-k]
 *      b. if the freq of a[i-k] ==0, remove from map
 *      c. if arr[i] doesn't exist in the map add it else increase the frequency
 *      d. print the size of map
 *
 */

public class EfficientSolutionUsingHashMap {
    
}
