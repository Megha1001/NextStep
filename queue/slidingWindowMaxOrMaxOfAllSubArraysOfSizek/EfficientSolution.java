package queue.slidingWindowMaxOrMaxOfAllSubArraysOfSizek;

import java.util.ArrayDeque;

/*
 * TIME COMPLEXITY : O(N)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {1,3,-1,3,5,3,6,7};
        int k = 3;
        System.out.println("Max of subarray of size K is : ");
        findAndPrintMax(arr, arr.length, k);

    }

    public static void findAndPrintMax(int arr[], int n, int k){

        ArrayDeque<Integer> res = new ArrayDeque<>();
        ArrayDeque<Integer> d = new ArrayDeque<>();
        
        //for first K window
        for(int i=0; i<k; i++){
            while(d.size()>0 && arr[d.peekLast()] <= arr[i]){
                d.pollLast();
            }

            d.offerLast(i);
        }


        //for rest
        for(int i=k; i<n; i++){
            //print the last subarray max element
            res.offer(arr[d.peekFirst()]);

            //pop the items that are not in curr window
            while(d.size()>0 && d.peekFirst() <= i-k){
                d.pollFirst();
            }

            //remove elements that are <= arr[i] from back
            while(d.size() > 0 && arr[d.peekLast()] <= arr[i]){
                d.pollLast();
            }

            d.offerLast(i);
        }

        //for last window
        res.offer(arr[d.pollFirst()]);

        for(int e : res){
            System.out.print(e + " ");
        } 

    }
    
}
