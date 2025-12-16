package sorting.medianofTwoSortedArrays;

/*
 * TIME COMPLEXITY : O(logN1)
 */

public class EfficientMethodUsingBinarySearch {

    public static void main(String args[]){
        int a1[] = {10,20,30};
        int a2[] = {5,15,25,35,45};
        int n1 = a1.length;
        int n2 = a2.length;


        if(n1 > n2){
            System.out.println("Median of the two given sorted arrays is : "+findMedian(a2, a1, n2, n1));
        }else {
            System.out.println("Median of the two given sorted arrays is : "+findMedian(a1, a2, n1, n2));
        }

    }


    public static double findMedian (int a1[], int a2[], int n1, int n2){

        int begin = 0;
        int end = n1;

        while(begin <= end){
            
            int i1 = begin + (end-begin)/2; //start of right half of first array
            
            int i2 = (n1+n2+1)/2 - i1; //start of right half of second array

            int max1 = (i1==0) ? Integer.MIN_VALUE : a1[i1-1];
            int min1 = (i1==n1) ? Integer.MAX_VALUE : a1[i1];
            int max2 = (i2==0) ? Integer.MIN_VALUE : a2[i2-1];
            int min2 = (i2==n2) ? Integer.MAX_VALUE : a2[i2];


            if(max1 <= min2 && max2 <= min1){

                if((n1+n2) %2 ==0){
                    //even

                    return (double) ((Math.max(max1, max2)) + (Math.min(min1, min2)))/2;
                }
                else {
                    return Math.max(max1, max2);
                }

            }else if (max1 > min2){
                end = i1-1; //move left
            }
            else {
                //move fight
                begin = i1+1;
            }


        }
        
        return -1;

    }
    
}
