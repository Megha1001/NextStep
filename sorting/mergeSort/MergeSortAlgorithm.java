package sorting.mergeSort;

public class MergeSortAlgorithm {

    public static void main(String args[]){
        int arr[] = {3,1,8,0,4,2};
        
        mergeSort(arr, 0, arr.length-1);

        System.out.println("Array after sorting from merge sort is : ");
        printArray(arr, arr.length);
    }


    public static void mergeSort(int arr[], int l, int r){

        if (l < r){ // atleast two element

            int m = l + (r-l)/2;

            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);

        }
        
    }

    public static void merge(int arr[], int l, int m, int r){

        int n1 = m-l+1;
        int n2 = r-m;
        int left[] = new int[n1];
        int right[] = new int[n2];

        for(int i=0; i<n1; i++){
            left[i] = arr[l+i];
        }

        for(int j=0; j<n2; j++){
            right[j] = arr[m+1+j];
        }


        int i=0;
        int j=0;
        int k = l;

        while(i<n1 && j <n2){

            if(left[i] <= right[j]){
                arr[k++] = left[i++];
            }else {
                arr[k++] = right[j++];
            }
            
        }

        while(i<n1){
            arr[k++] = left[i++];
        }

        while(j<n2){
            arr[k++] = right[j++];
        }

    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
    }
    
}
