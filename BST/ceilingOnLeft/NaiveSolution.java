package BST.ceilingOnLeft;

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {2,8,30,15,25,12};
        System.out.println("Ceiling on left side for every element is : ");
        printCeilOnLeft(arr, arr.length);
    }

    public static void printCeilOnLeft(int arr[], int n){
        for(int i=0; i<n; i++){
            int res = -1;
            for(int j=0; j<i; j++){
                if(arr[j]>arr[i] && (res == -1 || res > arr[j])){
                    res = arr[j];
                }
            }

            System.out.print(res + " ");
        }
    }
    
}
