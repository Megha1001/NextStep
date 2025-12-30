package stack.stockSpanVariations.nextGreaterElement;

/*
 * IDEA : Traverse from end and do findNextPreviousElement -> it would end up find next Greatest element
 */

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

public class MostEfficientSolutionUsingStack {


    public static void main(String args[]){
        int arr[] = {5,15,10,8,6,12,9,18};
        //  int arr[] = {10,15,20,25};
        // int arr[] = {25,20,15,10};
        System.out.println("Next greater element in given arrays are :-");
        nextGreatestElement(arr, arr.length);
    }

    public static void nextGreatestElement(int arr[], int n){
        ArrayList<Integer> list = new ArrayList<>();
        ArrayDeque<Integer> s = new ArrayDeque<>();

        //for last element
        s.push(arr[n-1]);
        list.add(-1);

        for(int i=n-2; i>=0; i--){
            while(s.size() > 0 && arr[i] >= s.peek()){
                s.pop();
            }

            if(s.isEmpty()){
                list.add(-1);
            }else{
                list.add(s.peek());
            }

            s.push(arr[i]);
        }

        Collections.reverse(list);

        for(int a : list){
            System.out.print(a + " ");
        }

    }
    
}
