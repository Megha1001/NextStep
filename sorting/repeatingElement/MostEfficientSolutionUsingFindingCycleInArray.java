package sorting.repeatingElement;

/*
 * Condition : Dont modify the array and dont use any extra space
 */

public class MostEfficientSolutionUsingFindingCycleInArray {

    public static void main(String args[]){
        int arr[] = {1,3,2,4,6,5,7,3};
        System.out.println("The repeating element in the given array is "+findRepeatingElement(arr, arr.length));
    }
    
}
