package Arrays.trappingRainWater;

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {3,0,1,2,5};
        System.out.println("The total water that can be trapped is : "+fingTrappedWater(arr, arr.length));
    }

    public static int fingTrappedWater(int arr[], int n){

        int res = 0;

        for(int i=1; i<n-1; i++){ // no water will remain on first and last bar

            int lMax = arr[i];
            for(int j=0; j<i; j++){
                lMax = Math.max(arr[j], lMax);
            }

            int rMax = arr[i];
            for(int j=i+1; j<n; j++){
                rMax = Math.max(rMax, arr[j]);
            }

            res = res + (Math.min(rMax, lMax) - arr[i]);


        }

        return res;

    }
    
}
