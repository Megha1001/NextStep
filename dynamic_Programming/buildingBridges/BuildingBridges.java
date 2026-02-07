package dynamic_Programming.buildingBridges;

/*
IDEA : Sort the pairs by first element if first element is same sort by second
Find LIS on second element
*/

import java.util.Arrays;

public class BuildingBridges {

    public static void main(String args[]){
        int pairs[][] = {{6,2},{4,3},{2,6},{1,5}};
        System.out.println("Maximum bridge that can be made without crossing is :"+calMaxBridge(pairs, pairs.length));
    }

    public static int calMaxBridge(int [][]pairs, int n){
        Arrays.sort(pairs, (a,b)-> { // two pairs - {a[0], a[1]}, {b[0], b[1]}
            if(a[0] != b[0]){
                return a[0] - b[0];
            }
            return a[1] - b[1]; //compare second if first is same
        });

        //LIS
        int [] lis = new int[n];

        for(int i = 0; i < n; i++){ //consider every element as pivot -> ending of LIS
            lis[i] = 1;
            for(int j = 0; j < i; j++){
                if(pairs[j][1] < pairs[i][1]){
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }

        // int res = lis[0];
        // for(int i = 1; i < n; i++){
        //     res = Math.max(res, lis[i]);
        // }

        // return res;

        return Arrays.stream(lis).max().getAsInt();
    }
    
}
