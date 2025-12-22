package hashing.openAddressing;

public class LinearProbingImpl {

    static class MyHash{

        int cap; // what is the limit
        int size; //how many occupied
        int arr[];

        public MyHash(int c){
            this.cap = c;
            this.size = 0;
            this.arr = new int[cap];

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
                i = (i+1)%cap;
                if(i==h){
                    return false;
                }
            }
            return false;
        }

        public boolean erase(int key){
            if(size==0){
                return false; //arr is empty
            }

            int h = hash(key);
            int i = h;
            
            while(arr[i] !=-1){
                if(arr[i] == key){
                    arr[i] = -2;
                    --size;
                    return true;
                }
                i= (i+1)%cap;
                if(i==h){
                    return false;
                }
            }

            return false;

        }

    }
    

    public static void main(String args[]){
        MyHash mh = new MyHash(7);
	    mh.insert(49);
	    mh.insert(56);
	    mh.insert(72);
	    if(mh.search(56)==true)
	        System.out.println("Yes");
	    else
	        System.out.println("No");
	    mh.erase(56);
	    if(mh.search(56)==true)
	        System.out.println("Yes");
	    else
            System.out.println("No");
    }
}
