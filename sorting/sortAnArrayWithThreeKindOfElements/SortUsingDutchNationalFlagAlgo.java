package sorting.sortAnArrayWithThreeKindOfElements;

public class SortUsingDutchNationalFlagAlgo {

    public static void main(String args[]){
        int arr[] = {2,2,0,1,2,0,1,1};
        System.out.println("Array after sorting thre kinds of elements is ");
        sortUsingDutchNationalFlagAlgo(arr, arr.length);
    }

    public static void sortUsingDutchNationalFlagAlgo(int arr[], int n){
        int l = 0;
        int m = 0;
        int h = n-1;

        while(m<=h){
            if(arr[m]==0){
                int temp = arr[l];
                arr[l] = arr[m];
                arr[m] = temp;
                ++m; //we increment since when we replaced arr[m] with arr[l], we know that arr[l] should be 1
                ++l;
            }

            else if(arr[m]==1){
                ++m;
            }

            else { //for 2
                int temp = arr[h];
                arr[h] = arr[m];
                arr[m] = temp;
                
                --h; //we are only --h since when we replaced arr[m] with arr[h], we dont know what is there on arr[h]
            }
        }

        //print
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }

    }
    
}
