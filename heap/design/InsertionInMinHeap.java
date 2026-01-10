package heap.design;

/*
 * TIME COMPLEXITY : O(Height) = O(logN) 
 */

public class InsertionInMinHeap {

    static class Heap{
        int arr[];
        int cap;
        int size;

        Heap(int cap){
            this.cap = cap;
            this.arr = new int[cap];
            this.size=0;
        }

        int left(int i){
            return 2*i-1;
        }

        int right(int i){
            return 2*i+1;
        }

        int parent(int i){
            return (i-1)/2;
        }

        public void insert(int x){
            if(size == cap){
                System.out.println("Overflow");
                return;
            }

            ++size;
            arr[size-1] = x;

            for(int i=size-1; i!=0 && arr[parent(i)] > arr[i];){
                int temp = arr[i];
                arr[i] = arr[parent(i)];
                arr[parent(i)] = temp;

                //update curr
                i = parent(i);
            }
        }
    }

    public static void main(String args[]){
        int arr[] = {10,20,15,40,50,100,25,45};
        int x=12;
        Heap heap = new Heap(10);

        for(int i : arr){
            heap.insert(i);
        }

        heap.insert(x);

        System.out.println("Array elements after insertion is : ");

        for(int i: heap.arr){
            System.out.print(i + " ");
        }
    }
    
}
