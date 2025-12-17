package searching.medianofTwoSortedArrays;

/*
 * TIME COMPLEXITY : O(N1+N2)
 * AUXILIARY SPACE : Theta(N1+N2)
 */

public class FindMedianUsingMergeSort {

    public static void main(String args[]){
        // float a1 [] = {10,20,30,40,50};
        // float a2 [] = {5,15,25,35,45};
        float a1 [] = {1,2,3,4,5,6};
        float a2 [] = {10,20,30,40,50};
        System.out.println("Median of two sorted arrays is : "+findMedian(a1, a2, a1.length, a2.length));
    }

    public static float findMedian(float a1[], float [] a2, int n1, int n2){

        float temp[] = new float [n1+n2];

        int i = 0; //to keep track on a1
        int j = 0; // to keep track on a2
        int k = 0; // to keep track on temp

        while(i< n1 && j < n2){

            if(a1[i] <= a2[j]){
                temp[k++] = a1[i++];
            }

            else if (a1[i] > a2[j]){
                temp[k++] = a2[j++];
            }

        }

        while(i<n1){
            temp[k++] = a1[i++];
        }

        while(j<n2){
            temp[k++] = a2[j++];
        }


        // to find median
    
        if((n1+n2)%2 ==0){
            //even
            return (temp[(k-1)/2] + (temp[((k-1)/2 + 1)]))/2;
        }

        return temp[(k)/2];
    }
    
}
