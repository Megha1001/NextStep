package sorting.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

public class PrintEvenFirst {

    public static void main(String args[]){
        Integer arr[] = {1,3,4,9,8,4,5};
        System.out.println("Output array is  : ");
        printEvenFirst(arr, arr.length);
    }

    public static void printEvenFirst(Integer arr[], int n){

        Collections.sort(Arrays.asList(arr), new EvenComparator());

        for(int i=0; i<n; i++){
            System.out.print(arr[i] +" ");
        }

    }

    public static class EvenComparator implements Comparator<Integer>{
        
        public int compare(Integer a1, Integer a2){
            return a1%2 - a2%2;
        }
    }
    
}
