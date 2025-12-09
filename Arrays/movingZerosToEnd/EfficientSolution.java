package Arrays.movingZerosToEnd;

public class EfficientSolution {


    public static void main(String args[]){
        int arr[] = {8,5,0,10,0,20};
        int n = arr.length;
        System.out.println("Array after moving zeros to end is : ");
        moveZerosToEnd(arr, n);
        printArray(arr, n);
    }


    public static void moveZerosToEnd(int arr[], int n){

        int count = 0; //maintain non zero elements idx

        for(int i = 0; i<n; i++){
            if(arr[i]!=0){
                arr[count++] = arr[i];
            }
        }

        //fill rest with zeros
        while(count < n){
            arr[count++] = 0;
        }
    }


    public static void printArray(int arr[], int n){
        for(int i=0; i< n; i++){
            System.out.print(arr[i]+" ");
        }

    }

    
}
