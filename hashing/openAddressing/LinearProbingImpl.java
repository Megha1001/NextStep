package hashing.openAddressing;

public class LinearProbingImpl {

    class MyHash{

        int cap; // what is the limit
        int size; //how many occupied
        int arr[];

        MyHash(int c){
            this.cap = c;
            this.size = 0;

            for(int i=0; i<cap; i++){
                arr[i] = -1; // all are empty
            }
        }

        public int hash(int key){
            return key%cap;
        }

        public boolean insert(int key){

            if(size == cap){
                return false;
            }

            int h = hash(key);
            int i = h;
            
            while(arr[i]!=-1 && arr[i]!=-2 && arr[i]!=key){
                //search for next spot
                i = (i+1)%cap ;//linear probing
            }

            if(arr[i]==key){
                return false;
            }else {
                arr[i] = key;
                size++;
                return true;
            }
        }

        public boolean search(int key){
            if(size==0){
                return false; //arr is empty
            }

            int h = hash(key);
            int i=h;
            while(arr[i] !=-1){
                if(arr[i] == key){
                    return true;
                }
                i = (i=1)%cap;
                if(i==h){
                    return false;
                }
            }
            return false;
        }

    }
    
}
