package hashing.unionOfTwoUnsortedArrays;

import java.util.HashSet;
import java.util.Arrays;

public class FindUnionOfTwoUnsortedArrayAndCountDisinct {
    

    public static void main(String args[]){
        Integer a1 [] = {15,20,5,15};
        Integer a2 [] = {15,15,15,20,10};

        System.out.println("The distinct elments count is : "+findUnion(a1, a2, a1.length, a2.length));

    }

    public static int findUnion(Integer a1[], Integer a2[], int n1, int n2){

        HashSet<Integer> set = new HashSet<>();
        set.addAll(Arrays.asList(a1));
        set.addAll(Arrays.asList(a2));


        return set.size();

    }



}
