package sorting.segregateTwoTypesOfElements;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {6,-5,4,1,-1,7,8};
        System.out.println("Array after segregating -ve and +ve is : ");
        segregateNegAndPos(arr, arr.length);
    }

    public static void segregateNegAndPos(int arr[], int n){
        int temp [] = new int[n];
        int j=0;

        //copy -ve
        for(int i=0; i<n ; i++){
            if(arr[i] < 0){
                temp[j++] = arr[i];
            }
        }

        //copy +ve
        for(int i=0; i<n;i++){
            if(arr[i] >= 0){
                temp[j++] = arr[i];
            }
        }

        //copy to original array and print
        for(int i=0; i<n; i++){
            arr[i] = temp[i];
            System.out.print(arr[i] + " ");
        }
    }
    
}
