package hashing.pairWithGivenSumInUnsortedArray;

import java.util.HashSet;

/*
 * WE CAN'T INSERT ALL THEN TRAVERSE otherwise for this example     int arr[] = {8,3,2,5}; int sum = 6; it will give true
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientSolutionUsingHashSet {

    public static void main(String args[]){
        // int arr[] = {8,3,4,2,5};
        int arr[] = {8,3,2,5};
        int sum = 6;
        System.out.println("is the pair exist in given array with given sum : ? "+isPair(arr, arr.length, sum));
    }

    public static boolean isPair(int arr[], int n, int sum){
        HashSet<Integer> set = new HashSet<>();

        for(int x : arr){
            if(set.contains(sum-x)){
                return true;
            }else{
                set.add(x);
            }
        }

        return false;
    }
    
}
