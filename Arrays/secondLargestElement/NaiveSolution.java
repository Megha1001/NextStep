package Arrays.secondLargestElement;

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {10,10,10};
        System.out.println("The index of second largest element in given array is :"+findSecondLargestElementIdx(arr));
    }

    public static int findSecondLargestElementIdx(int arr[]){

        int flIdx = 0;
        int slIdx = -1;
        int slValue = -1;


        //find first largest
        for(int i=1; i< arr.length; i++){
            if(arr[flIdx] < arr[i]){
                flIdx = i;
            }
        }

        System.out.println("First largest : "+flIdx);

        //find second largest
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > slValue && arr[i] != arr[flIdx]){
                slIdx = i;
                slValue = arr[i];
            }
        }

        return slIdx;

    }
    
}
