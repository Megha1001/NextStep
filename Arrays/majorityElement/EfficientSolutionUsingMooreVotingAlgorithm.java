package Arrays.majorityElement;


/*
 *  Use Moore's voting algorithm
 * 1. Find candidate of majority element
 * 2. Validate Candidate of majority Element
 */

public class EfficientSolutionUsingMooreVotingAlgorithm {


    public static void main(String args[]){
        int arr[] = {8,3,4,8,8};
        System.out.println("The index of majority of element appears in given array is : "+getIndexOfMajorityElement(arr, arr.length));
    }


    public static int getIndexOfMajorityElement(int arr[], int n){

        int possibleMajorityElementIdx = findMajorityElementIdx(arr, n);

        return validateAndGetMajorityElementIdx(arr, n, possibleMajorityElementIdx);
    }
    

    public static int findMajorityElementIdx(int arr[], int n){
        
        int res = 0; //first element idx
        int count = 1;

        for(int i=1; i<n; i++){
            if(arr[i] == arr[res]){
                ++count;
            }else {
                --count;
            }

            if(count == 0){
                res = i;
                count = 1;
            }
        }

        return res;

    }

    public static int validateAndGetMajorityElementIdx(int arr[], int n , int possibleMajorityElementIdx){

        int count = 0;
        for(int i=0; i<n; i++){
            if(arr[possibleMajorityElementIdx] == arr[i]){
                ++count;
            }
        }

        if(count <= n/2){
            return -1;
        }

        return possibleMajorityElementIdx;


    }

}
