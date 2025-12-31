package stack.largestRectangleInHistogram;


import java.util.ArrayDeque;

public class EfficientSolution {

    public static void main(String args[]){
        int arr[]= {2,1,5,6,2,3};
        // int arr[] = {2,5,1};
        System.out.print("The largest area of rectangle in histogram is : "+findLargestArea(arr, arr.length));
    
    }

    public static int findLargestArea(int arr[], int n){
        int res = 0;

        ArrayDeque<Integer> s = new ArrayDeque<>();
        int r[] = new int[n];
        int l[] = new int [n];

        //for right smaller
        for(int i=n-1; i>=0; i--){
            while(s.size()>0 && arr[s.peek()] >= arr[i]){
                s.pop();
            }

            r[i] = s.isEmpty() ? n : s.peek();

            s.push(i);
        }

        while(!s.isEmpty()){
            s.pop();
        }

        //for left smaller
        for(int i=0; i<n; i++){
            while(s.size() >0 && arr[s.peek()] > arr[i]){
                s.pop();
            }

            l[i] = s.isEmpty() ? -1 : s.peek();

            s.push(i);
        }


        for(int i=0; i<n; i++){

            int width = r[i] - l[i] - 1;
            int area = arr[i] * width;
            res = Math.max(res, area);

        }

        return res;
    }
    
}
