package queue.design;

public class ImplementQueueUsingArray {

    static class Queue{
        int arr[];
        int cap;
        int size;
        int front;
        int rear;

        Queue(int cap){
            this.cap = cap;
            this.arr = new int[cap];
            this.size = 0;
            this.front = -1;
            this.rear = -1;
        }


        void enqueue(int x){
            if(isFull()){
                System.out.println("Overflow");
                return;
            }
            arr[size] = x;
            ++size;
        }


        //TIME COMPLEXITY : O(N)
        int dequeue(){
            if(isEmpty()){
                System.out.println("Underflow");
                return Integer.MAX_VALUE;
            }

            int e = arr[0];
            for(int i=0; i<size-1; i++){
                arr[i] = arr[i+1];
            }
            --size;
            return e;
        }

        int getFront(){
            if(isEmpty()){
                return -1;
            }
            return 0;
        }

        int getRear(){
            if(isEmpty()){
                return -1;
            }
            return size-1;
        }

        boolean isEmpty(){
            return size==0;
        }

        boolean isFull(){
            return size == cap;
        }

        int size(){
            return size;
        }

    }

    public static void main(String args[]){
        Queue q = new Queue(5);
        q.enqueue(3);
        System.out.println(q.dequeue());
        q.enqueue(5);
        q.enqueue(1);
        q.enqueue(4);
        q.enqueue(0);
        System.out.println("size : " + q.size);
        for(int i=0; i<q.size; i++){
            System.out.print(q.arr[i] + " ");
        }
        System.out.println();
        System.out.println("isFull : "+q.isFull()+ "\n isEmpty() : "+q.isEmpty()+ " Front : " +q.getFront()+ " rear : "+q.getRear());

    }
    
}
