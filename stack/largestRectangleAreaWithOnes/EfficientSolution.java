package stack.largestRectangleAreaWithOnes;

/*
 * IDEA : 
 * {
 *  {0,1,1,0},
 *  {1,1,1,1},
 *  {1,1,1,1},
 *  {1,1,0,0}
 * }
 * 
 * Calculate maximum area covered by every row
 * But here we need to modify each row 
 *  if val[i][j] == 1 => val[i][j] + val[i-1][j],
 *  else dont change
* {
 *  {0,1,1,0}, -> [0,1,1,0]
 *  {1,1,1,1}, -> [1,2,2,1]
 *  {1,1,1,1}, -> [2,3,3,2]
 *  {1,1,0,0}  -> [3,4,0,0]
 * }
 */

import java.util.ArrayDeque;

public class EfficientSolution {

    public static void main(String args[]){
        int arr[][] = {
            {0,1,1,0},
            {1,1,1,1},
            {1,1,1,1},
            {1,1,0,0}
        };

        System.out.println("The largest rectangle with 1's in given 2D array is "+findLargestRectangle(arr));
    }

    public static int findLargestRectangle(int arr[][]){
        int r = arr.length;
        int c = arr[0].length;

        int res = largestHistogramArea(arr[0], c);

        for(int i=1 ; i<r; i++){
            for(int j=0; j<c; j++){
                if(arr[i][j] == 1){
                    arr[i][j] += arr[i-1][j]; //change histogram
                }
            }
            res = Math.max(res, largestHistogramArea(arr[i], c));
        }

        return res;

    }

    public static int largestHistogramArea(int arr[], int n){
        int res = 0;
        ArrayDeque<Integer> s = new ArrayDeque<>();
        int r[] = new int[n];
        int l[] = new int[n];

        //for right smaller 
        for(int i=n-1; i>=0; i--){
            while(s.size() > 0 && arr[s.peek()] >= arr[i]){
                s.pop();
            }

            r[i] = s.isEmpty() ? n : s.peek();
            s.push(i);
        }

        while(!s.isEmpty()){
            s.pop();
        }

        //for left smalller
        for(int i=0; i<n; i++){
            while(s.size() > 0 && arr[s.peek()] >= arr[i]){
                s.pop();
            }

            l[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }

        for(int i=0; i<n; i++){
            int length = arr[i];
            int width = r[i] - l[i] -1;
            int area = length* width;
            res = Math.max(res, area);
        }


        return res;
    }
    
}

/*
 * LEETCODE
 * class Solution {
    public int maximalRectangle(char[][] matrix) {

        if(matrix.length == 0){
            return 0;
        }

        int res = 0;

        int r = matrix.length;
        int c = matrix[0].length;

        int hist[] = new int[c];

        for(int i=0; i<r; i++){
            for(int j=0; j<c ; j++){
                if(matrix[i][j] == '1'){ //character comparision
                    hist[j] += 1;
                }else{
                    hist[j] = 0;
                }
            }
            res = Math.max(res, largestHistogramArea(hist, c));
        }

        return res;
    }

    public static int largestHistogramArea(int arr[], int n){
        int res = 0;
        ArrayDeque<Integer> s = new ArrayDeque<>();
        int r[] = new int[n];
        int l[] = new int[n];

        //for right smaller
        for(int i=n-1; i>=0; i--){
            while(s.size()> 0 && arr[s.peek()] >= arr[i]){
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
            while(s.size() > 0 && arr[s.peek()] >= arr[i]){
                s.pop();
            }

            l[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }


        for(int i=0; i<n; i++){
            int width = arr[i];
            int length = r[i] - l[i] - 1;
            int area = length*width;
            res = Math.max(res, area);
        }
        
        return res;

    }
}
 */