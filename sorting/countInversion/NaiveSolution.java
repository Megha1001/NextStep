package sorting.countInversion;

/*
 * Inversion : a pair of (i,j) where arr[i] > arr[j] and i<j;
 * 
 * TIME COMPLEXITY : Theta(N*N)
 * AUXILIARY SPACE : Theta(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {2,4,1,3,5};
        System.out.println("Number of inversion in given array is "+countInversion(arr, arr.length));
    }

    public static int countInversion(int arr[], int n){
        int res = 0;
        if(n==0){
            return res;
        }

        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(arr[i] > arr[j]){
                    ++res;
                }
            }
        }

        return res;

    }
    
}
