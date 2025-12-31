package stack.largestRectangleInHistogram;

/*
 * IDEA : Consider all the rectangles. 
 * Traverse the list one by one . for each element keep the track of currMaxArea and currMin 
 * currMin == height
 * width = j-i+1;
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[]= {2,1,5,6,2,3};
        System.out.print("The largest area of rectangle in histogram is : "+findLargestArea(arr, arr.length));
    }

    public static int findLargestArea(int arr[], int n){
        int res = -1;

        for(int i=0; i<n; i++){
            int currMax = arr[i];
            int currMin = arr[i];
            for(int j=i+1; j<n;j++){
                currMin = Math.min(arr[j], currMin);
                int area = currMin * (j-i+1);
                currMax = Math.max(currMax, area);
            }

            res = Math.max(currMax, res);
        }

        return res;
    }
    
}
