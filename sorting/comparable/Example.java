package sorting.comparable;

import java.util.Arrays;

public class Example {


    public static class Point implements Comparable<Point>{

        int x,  y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p){
            return this.x - p.x;
        }

    }

    public static void main(String args[]){
        Point arr[] = {new Point(10,10), new Point(10,15), new Point(5,10), new Point(8,15)};

        Arrays.sort(arr);

        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i].x + " : "+ arr[i].y);
        }
    }
    
}
