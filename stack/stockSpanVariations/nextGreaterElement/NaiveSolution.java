package stack.stockSpanVariations.nextGreaterElement;

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {5,15,10,8,6,12,9,18}; //15 18 12 12 12 18 18 -1
        // int arr[] = {10,15,20,25}; //15 20 25 -1
        // int arr[] = {25,20,15,10};
        System.out.println("Next greater element in given arrays are :-");
        nextGreatestElement(arr, arr.length);
    }
    

    public static void nextGreatestElement(int arr[], int n){
        for(int i=0; i<n; i++){
            int nextGreater = -1;
            for(int j=i+1; j<n; j++){
                if(arr[j] > arr[i]){
                    nextGreater = arr[j];
                    break;
                }
            }

            System.out.print(nextGreater + " ");
        }
    }

}
