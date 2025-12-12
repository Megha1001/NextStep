package Arrays.trappingRainWater;

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {3,0,1,2,5};
        System.out.println("The total water that can be trapped is : "+fingTrappedWater(arr, arr.length));
    }

    public static int fingTrappedWater(int arr[], int n){

        int res = 0;
        int lMax[] = new int[n];
        int rMax[] = new int[n];

        lMax[0] = arr[0];
        for(int i=1; i<n; i++){
            lMax[i] = Math.max(arr[i], lMax[i-1]);
        }

        rMax[n-1] = arr[n-1];

        for(int i=n-2; i>=0; i--){
            rMax[i] = Math.max(arr[i], rMax[i+1]);
        }

        for(int i=1; i<n-1; i++){
            res += Math.min(lMax[i], rMax[i]) - arr[i];
        }

    
        return res;

    }
    
}
