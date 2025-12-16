package sorting.repeatingElement;

/*
 * We can't modify the array
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {0,2,1,3,2,2};
        System.out.println("Repeating element in given array is : "+findRepeatingElement(arr, arr.length));
    }

    public static int findRepeatingElement(int arr[], int n){

        boolean visited[] = new boolean[n];

        for(int i=0; i<n; i++){
            if(visited[arr[i]]){
                return arr[i];
            }
            visited[arr[i]] = true;
        }
        
        return -1;
    }
    
}
