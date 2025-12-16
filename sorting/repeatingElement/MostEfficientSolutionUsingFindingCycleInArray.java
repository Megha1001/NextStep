package sorting.repeatingElement;

/*
 * Condition : Dont modify the array and dont use any extra space
 * 
 * TIME COMPLEXITY : O(N)
 * AUXLIARY SPACE : O(1)
 */

public class MostEfficientSolutionUsingFindingCycleInArray {

    public static void main(String args[]){
        int arr[] = {1,3,2,4,6,5,7,3};
        System.out.println("The repeating element in the given array is "+findRepeatingElement(arr));
    }

    public static int findRepeatingElement(int arr[]){
        int slow = arr[0];
        int fast = arr[0];

        do{
            slow = arr[slow];
            fast = arr[arr[fast]];
        }while(slow != fast);

        slow = arr[0];
        while(slow != fast){
            slow = arr[slow];
            fast = arr[fast];
        }

        return slow;

    }
    
}
