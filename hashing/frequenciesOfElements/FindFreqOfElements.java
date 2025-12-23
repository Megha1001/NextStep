package hashing.frequenciesOfElements;

import java.util.HashMap;
import java.util.Map;

public class FindFreqOfElements {

    public static void main(String args[]){
        int arr[] = {10,12,10,15,10,20,12,12};
        System.out.println("Frequency of elements are : ");
        findFrequency(arr, arr.length);
    }
    
    public static void findFrequency(int arr[], int n){
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for(int i=0; i<n; i++){
            hashmap.put(arr[i], hashmap.getOrDefault(arr[i], 1) +1);
        }

        //print
        for(Map.Entry<Integer, Integer> entry : hashmap.entrySet()){
            System.out.println(entry.getKey()+ " : "+ entry.getValue());
        }
    }
}
