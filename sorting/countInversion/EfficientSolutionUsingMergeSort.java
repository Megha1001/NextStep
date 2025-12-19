package sorting.countInversion;

/*
 * TIME COMPLEXITY : O(nLogN)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientSolutionUsingMergeSort {


    public static void main(String args[]){
        int arr[] = {2,4,1,3,5};
        System.out.println("Number of inversion in given array is "+countInversion(arr, 0,  arr.length-1));
    }

    public static int countInversion(int arr[], int l, int r){
        int res = 0;
        if(l < r){
            int m = l + (r-l)/2;

            res += countInversion(arr, l, m);
            res += countInversion(arr, m+1, r);
            res += countAndMerge(arr, l, m, r);

        }
        return res;
    }


    public static int countAndMerge(int arr[], int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r-m;

        int left[] = new int[n1];
        int right[] = new int[n2];


        for(int i=0; i<n1; i++){
            left [i] = arr[l + i];
        }

        for(int j=0; j<n2; j++){
            right[j] = arr[m+1+j];
        }


        int i=0;
        int j=0;
        int k=l;
        int res=0;
        
        while(i<n1 && j<n2){
            if(left[i] <= right[j]){
                arr[k++] = left[i++];
            }

            else if(left[i] > right[j]){
                arr[k++] = right[j++];
                res += (n1-i);
            }
        }


        while(i<n1){
            arr[k++] = left[i++];
        }

        while(j<n2){
            arr[k++] = right[j++];
        }

        return res;

    }

    
}
