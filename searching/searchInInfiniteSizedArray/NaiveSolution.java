package searching.searchInInfiniteSizedArray;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {
    
    public static void main(String args[]){

        int arr[] = {1, 2, 3, 40, 50}; // assume it to be infinite array
        int x = 4;

        System.out.println("The required element is present at idx : "+searchElement(arr, x));
    }

    public static int searchElement(int arr[], int x){
        int i=0;
        while(true){
            if(arr[i] == x){return i;}
            if(arr[i] < x){return -1;}
            ++i;
        }
    }
    
}
