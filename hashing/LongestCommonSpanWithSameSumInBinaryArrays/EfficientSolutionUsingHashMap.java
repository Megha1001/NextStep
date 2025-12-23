package hashing.LongestCommonSpanWithSameSumInBinaryArrays;

/*
 * IDEA : 
 * 1. Compute a difference array
 *  a1 = {0,1,0,0,0,0}
 *  a2 = {1,0,1,0,0,1}
 * diff = {-1,1,-1,0,0,-1}
 *            ---------
 * 2. Return the length of longest subarray with sum = 0;
 *   - We get 0 when values are same in both
 *   - we get 1 when a1[i] = 1 and a2[i] = 0;
 *   - we get -1 when a2[i] = 0 and a2[i] = 1;
 * 
 * 2nd and third will balance each other in case of common subarray. Hence, we need to find subarray with sum = 0
 */

public class EfficientSolutionUsingHashMap {
    
}
