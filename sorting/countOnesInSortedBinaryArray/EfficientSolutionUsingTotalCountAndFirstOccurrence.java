package sorting.countOnesInSortedBinaryArray;
/*
 * IDEA : total length of array -  Get the first occurrence
 * 
 * TIME COMPLEXITY : O(logN)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolutionUsingTotalCountAndFirstOccurrence {

    public static void main(String args[]){
        int arr[] = {0,0,0};
        System.out.println("The count of 1's in given binary arrays is : "+countOne(arr, 0, arr.length-1));
    }

    public static int countOne(int arr[], int l, int h){
        int firstOccurrence = findFirstOccurrence(arr, l, h);
        if(firstOccurrence == -1){
            return 0;
        }
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

            else if (arr[m] == 0){
                l = m+1;
            }else {
                h = m-1;
            }
        }
        return -1;
    }
    
}
