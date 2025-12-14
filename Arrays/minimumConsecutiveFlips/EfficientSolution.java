package Arrays.minimumConsecutiveFlips;

/*
 *  DIFFERENCE CAN BE ATMOST ONCE
 *  
 * There can be two possibilites
 *  1. Difference is 1
 *      -> The first and last element is same
 *  
 *  2. Difference is 0
 *      -> First and fast element is different
 * 
 *  ALWAYS PRINT THE SECOND GROUP
 * 
 * 
 * TIME COMPLEXITY : Theta(N)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {0,0,1,1,0,0,1,1,0,1};
        System.out.println("Flips are :- ");
        findAndPrintFlips(arr, arr.length);
    }

    public static void findAndPrintFlips(int arr[], int n){

        for(int i=1; i<n; i++){
            if(arr[i] != arr[i-1]){
                //start
                if(arr[i] != arr[0]){
                    System.out.print("From "+i+" to ");
                }else { //end
                    System.out.println(i-1);
                }
            }
        }
        //to print last 1  {0,0,1,1,0,0,1,1,0,1}
        if(arr[n-1] != arr[0]){
            System.out.println(n-1);
        }
        
    }
    
}
