package stack.stockSpanVariations.nextGreaterElement;

/*
 * IDEA : 
 * 1. Reverse the original array
 * 2. find the previous greater element and store in res[]
 * 3. print res[] in reverse order
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

import java.util.ArrayDeque;

public class EfficientSolutionUsingStack {
    
    public static void main(String args[]){
        int arr[] = {5,15,10,8,6,12,9,18};
        System.out.println("Next greater element in given arrays are :-");
        nextGreatestElement(arr, arr.length);
    }

    public static void nextGreatestElement(int arr[], int n){
        int low = 0;
        int high = n-1;
        while(low < high){
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            
            ++low;
            --high;
        }

        int res [] = findPreviousGreater(arr, n);
        
        for(int i=n-1; i>=0; i--){
            System.out.print(res[i] + " ");
        }
    }

    public static int[] findPreviousGreater(int arr[], int n){
        int res[] = new int[n];

        ArrayDeque<Integer> s = new ArrayDeque<>();

        for(int i=0; i<n; i++){
            while(s.size()>0 && arr[i] >= s.peek()){
                s.pop();
            }

            if(s.isEmpty()){
                res[i] = -1;
            }else{
                res[i] = s.peek();
            }

            s.push(arr[i]);
        }

        return res;
    }

}
