package heap.design;

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
    }
    
}
