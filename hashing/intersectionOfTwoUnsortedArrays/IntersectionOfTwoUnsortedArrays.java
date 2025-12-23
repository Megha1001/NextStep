package hashing.intersectionOfTwoUnsortedArrays;

import java.util.HashSet;

/*
 * TIME COMPLEXITY : Theta(Max(N1, N2))
 * AUXILIARY SPACE :  O(min(n1,n2))
 */

public class IntersectionOfTwoUnsortedArrays {

    public static void main(String args[]){
        int a1 [] = {10,34,9,80,26,8,12,15};
        int b1 [] = {5,0,12,8,15,34};
        int n1 = a1.length;
        int n2 = b1.length;
        System.out.println("Common elements in two given arrays are : ");

        //pass small array so auxiliary space : O(min(n1,n2))
        if(n1 > n2){
            findCommonElements(b1, a1, n2, n1);  
        }else{
            findCommonElements(a1, b1, n1, n2);
        }

    }

    public static void findCommonElements(int a1[], int b1[], int n1, int n2){
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<n1; i++){
            set.add(a1[i]);
        }

        for(int i=0; i<n2; i++){
            if(set.contains(b1[i])){
                System.out.print(b1[i] + " ");
            }
        }

    }
    
}
