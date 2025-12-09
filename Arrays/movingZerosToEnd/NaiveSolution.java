package Arrays.movingZerosToEnd;

public class NaiveSolution {


    public static void main(String args[]){
        int arr[] = {8,5,0,10,0,20};
        int n = arr.length;
        System.out.println("Array after moving zeros to end is : ");
        moveZerosToEnd(arr, n);
        printArray(arr, n);
    }


    public static void moveZerosToEnd(int arr[], int n){

        for(int i=0; i<n; i++){
            if(arr[i]==0){
                for(int j=i+1; j<n;j++){
                    if(arr[j]!=0){
                        //swap
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        break; // otherwise it will swap again and generate output as 8 5 20 10 0 0 
                    }
                }
            }
        }

    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+ " ");
        }
    }
    
}
