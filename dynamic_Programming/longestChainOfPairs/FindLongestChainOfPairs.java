package dynamic_Programming.longestChainOfPairs;

/* 
IDEA : sort the array on the basis of first element and since (a<b) & (c<d) so a<c they can never be equal so no need of second element
Find LIS such that [a, b] [c,d] => pair(i).first > pair(j).second
*/

import java.util.Arrays;

public class FindLongestChainOfPairs {

    public static void main(String args[]){
        int [][] pairs = {{1,2}, {7,8},{4,5}};

        System.out.println("Longest chain is : "+findLongestChain(pairs, pairs.length));
    }
    
    public static int findLongestChain(int[][]pairs, int n){
        Arrays.sort(pairs, (a,b)-> a[0]-b[0]);

        int lis[] = new int[n];
        
        //LIS
        for(int i = 0; i < n; i++){
            lis[i] = 1;
            for(int j = 0; j < i; j++){
                if(pairs[i][0] > pairs[j][1]){
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }

        return Arrays.stream(lis).max().getAsInt();
    }


    //GREEDY APPROACH
    /*
     consider meeting that ends early so there will be more room for meetings
    */
    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, (a, b)-> a[1] - b[1]); //sort on second element of each pair

        int count = 0;
        int currEnd = Integer.MIN_VALUE; ; //end value of last considered pair

        for(int []p : pairs){
            int start = p[0];
            int end = p[1];

            if(start > currEnd){
                // we can add this pair
                ++count;
                currEnd = end;
            }
        }

        return count;

    }
}
