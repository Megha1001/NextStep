package BST.ceilingOnLeft;

import java.util.TreeSet;

/*
 * IDEA : Use TreeSet that uses Red black tree(self balancing)
 * TIME COMPLEXITY : O(Nlog N)
 * AUXILIARY SPACE : O(N)
 */

public class CelingOnLeftUsingTreeSet {

    public static void main(String args[]){
        int arr[] = {2,8,30,15,25,12};
        System.out.println("Ceiling on left side for every element is : ");
        printCeilOnLeft(arr, arr.length);
    }

    public static void printCeilOnLeft(int arr[], int n){
        System.out.print(-1+" ");
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(arr[0]);
        
        for(int i=1; i<n; i++){
            if(treeSet.ceiling(arr[i])!=null){
                System.out.print(treeSet.ceiling(arr[i])+" ");
            }else{
                System.out.print(-1+" ");
            }
            treeSet.add(arr[i]);
        }
    }

}
