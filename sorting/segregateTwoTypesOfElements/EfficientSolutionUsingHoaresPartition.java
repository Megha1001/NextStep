package sorting.segregateTwoTypesOfElements;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientSolutionUsingHoaresPartition {

    public static void main(String args[]){
        int arr[] = {6,-5,4,1,-1,7,8};
        System.out.println("Array after segregating -ve and +ve is : ");
        segregateNegAndPos(arr, arr.length);

        //print
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] +" ");
        }

    }

    public static void segregateNegAndPos(int arr[], int n){
        int i=-1;
        int j = n;

        do{
            ++i;
        }while(arr[i] < 0);


        do{
            --j;
        }while(arr[j] >=0);

        if(i>=j){
            return;
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
