package Arrays.frequencyInSortedArray;

public class FindFreqInSortedArray {


    public static void main(String args[]){
        int arr[] = {10,10,10,25,30,30};
        System.out.println("Frequency of element are :- ");
        findFreq(arr, arr.length);
    }


    public static void findFreq(int arr[], int n){
        int freq = 1;

        if(n==0){
            return;
        }

        if(n==1){
            System.out.println(" "+arr[0]+" : "+freq);
            return;
        }

        //this wont print for last element - {10,10,10,25,30,30}
        /*
         * 10 : 3
         * 25 : 1
         */
        for(int i = 1; i < n; i++){
            if(arr[i]==arr[i-1]){
                ++freq;
            }
            else{
                System.out.println(" "+arr[i-1]+" : "+freq);
                freq=1;
            }
        }


        //for last element
        System.out.println(" "+arr[n-1]+" : "+freq);
    }
    
}
