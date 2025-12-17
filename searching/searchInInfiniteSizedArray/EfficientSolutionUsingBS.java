package searching.searchInInfiniteSizedArray;

/*
 * TIME COMPLEXITY : O(log(Pos))
 * 5->8
 * 9->16
 * to reach 2*pos => log(Pos)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolutionUsingBS {
    
    public static void main(String args[]){

        int arr[] = {1, 2, 3, 40, 50}; // assume it to be infinite array
        int x = 40;

        System.out.println("The required element is present at idx : "+searchElement(arr, x));
    }

    public static int searchElement(int arr[], int x){
       
        //check for 0th index solely
        if(arr[0] == x){
            return 0;
        }

        int i=1;
        while(arr[i] < x){ //find upper bound
            i = i*2; //O(log(Pos))
        }

        //either = or > x
        if(arr[i] == x){
            return i;
        }

        return binarySearch(arr, (i/2)+1, i-1, x); //O(log(Pos)) // (i/2)+1, i-1 elements b/w them must be less than pos otherwise it would have come in i*2
    }

    public static int binarySearch(int arr[], int start, int end, int x){

        while(start <= end){
            int mid = start + (end-start)/2;

            if(arr[mid]==x){
                return mid;
            }

            else if (arr[mid] > x){
                end = mid-1;
            }else {
                start = mid+1;
            }
        }

        return -1;
    }
    
}
