package Arrays.maxConsecutiveOneInBinaryArray;

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {0,1,1,0,1,0};
        // int arr[] = {1,1,1,1};
        // int arr[] = {0,0,0};
        // int arr[] = {1,0,1,1,1,1,0,1,1};
        int n = arr.length;
        System.out.println("Max consecutive 1's in given binary array is : "+findMaxOne(arr, n));
    }


    public static int findMaxOne(int arr[], int n){

        int res = 0, curr = 0;

        for(int i=0; i<n; i++){

            if(arr[i]==0){
                curr=0;
            }else{
                ++curr;
                res = Math.max(res, curr);
            }

        }

        return res;

    }


    
}
