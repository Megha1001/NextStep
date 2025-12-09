package Arrays.secondLargestElement;

/*
 * Time Complexity : Theta(N)
 * One Traversal
 */

public class EfficientWithOneTraversalSolution {


    public static void main(String args[]){
        int arr[] = {20,10,20,8,12};
        System.out.println("The index of second largest element in given array is :"+findSecondLargestElementIdx(arr));
    }
    

    public static int findSecondLargestElementIdx(int arr[]){

        int flIdx = 0;
        int slIdx = -1;
        int slVal = -1;

        for(int i = 1; i < arr.length; i++){
            if(arr[flIdx] < arr[i]){
                slIdx = flIdx;
                flIdx = i;
            }

            else if (slVal < arr[i] && arr[i] != arr[flIdx]){
                slIdx = i;
            }
        }

        return slIdx;

    }
}
