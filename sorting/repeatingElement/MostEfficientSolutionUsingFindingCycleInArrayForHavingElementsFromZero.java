package sorting.repeatingElement;

/*
 * Condition : Dont modify the array and dont use any extra space
 * 
 * TIME COMPLEXITY : O(N)
 * AUXLIARY SPACE : O(1)
 */

public class MostEfficientSolutionUsingFindingCycleInArrayForHavingElementsFromZero {

    public static void main(String args[]){
        int arr[] = {0,2,1,3,5,4,6,2};
        System.out.println("The repeating element in the given array is "+findRepeatingElement(arr));
    }

    public static int findRepeatingElement(int arr[]){
        //to avoid self loop -> adding extra 1
        int slow = arr[0] + 1;
        int fast = arr[0] + 1;

        do{
            slow = arr[slow] + 1;
            fast = arr[arr[fast]+1] + 1;
        }while(slow != fast);

        slow = arr[0]+1;
        while(slow != fast){
            slow = arr[slow] + 1;
            fast = arr[fast] + 1;
        }

        return slow -1;
    }
    
}
