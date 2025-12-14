package sorting.countOnesInSortedBinaryArray;
/*
 * IDEA : total length of array -  Get the first occurrence 
 */

public class EfficientSolutionUsingTotalCountAndFirstOccurrence {

    public static void main(String args[]){
        int arr[] = {0,0,0,1,1,1,1};
        System.out.println("The count of 1's in given binary arrays is : "+countOne(arr, 0, arr.length-1));
    }

    public static int countOne(int arr[], int l, int h){
        int firstOccurrence = findFirstOccurrence(arr, l, h);
        return arr.length - firstOccurrence;
    }

    public static int findFirstOccurrence(int arr[], int l, int h){
        while(l <= h){
            int m = l +(h-l)/2;

            if(arr[m]==1){
                if(m==0 || arr[m] != arr[m-1]){
                    return m;
                }
                h = m-1;
            }

            else if (arr[m] > 1){
                h = m-1;
            }else {
                l = m+1;
            }
        }
        return -1;
    }
    
}
