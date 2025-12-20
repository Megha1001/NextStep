package sorting.segregateTwoTypesOfElements;

/*
 * TIME COMPLEXITY : Theta(N)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {6,-5,4,1,-1,7,8};
        System.out.println("Array after segregating -ve and +ve is : ");
        segregateNegAndPos(arr, arr.length);
    }


    //use partition function of quicksort
    public static void segregateNegAndPos(int arr[], int n){
        int i = -1;
        for(int j=0; j<n; j++){
            if(arr[j] < 0){
                ++i;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        //print 
        for(int k=0; k<n; k++){
            System.out.print(arr[k] + " ");
        }
    }
    
}
