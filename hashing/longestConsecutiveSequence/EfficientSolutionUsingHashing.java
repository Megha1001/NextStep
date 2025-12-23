package hashing.longestConsecutiveSequence;

/*
 * IDEA : 
 * 1. Store the element in HashSet -> distinct
 * 2. Traverse Hashset
 *  if (h.contains(x-1)) -> that x is not the start of subsequence
 *  if (!h.contains(x-1)) -> that x is start of subsequence
 *      then check for x+1, x+2 and so on
 */

import java.util.HashSet;

public class EfficientSolutionUsingHashing {

    public static void main(String args[]){
        int arr[] = {1,3,9,2,8,2};
        System.out.println("The longest consecutive sequence length is : "+findLongestConsequtiveSubSequence(arr, arr.length));
    }


    public static int findLongestConsequtiveSubSequence(int arr[], int n){
        int res = 1;
        HashSet<Integer> h = new HashSet<>();

        //Step-1
        for(int i : arr){
            h.add(i);
        }

        //Step-2
        for(Integer x : h){

            //start of subsequence
            if(!h.contains(x-1)){
                int curr = 1;

                while(h.contains(x+curr)){
                    ++curr;
                }

                res = Math.max(res, curr);

            }

        }

        return res;
    }
    
}
