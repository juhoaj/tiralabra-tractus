/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 * Provides ArrayList data structure and method's for utilizing it.
 * The ArrayList "locks" the class of the accepted objects
 * when inserting the first object.
 * 
 * @param <E>
 */
public class CustomArrayList<E> {
    private Object[] array;
    private int listSize;
    private Class objectClass;
    private boolean hasBeenInitialized;
    private int startSize = 64;

    /**
     * Constructor.
     */
    public CustomArrayList(){
        this.array = new Object[startSize];
        this.listSize = 0;
        
    }
     
    /**
     * Constructor with option for setting starting length for array.
     * @param arrayLength
     */
    public CustomArrayList(int arrayLength) {
        this.hasBeenInitialized = false;
        this.array = new Object[arrayLength];
        this.listSize = 0;
    }
    
    /**
     * List size.
     * @return size of the list.
     */
    public int size() {
        return this.listSize;
    }
    
    /**
     * Returns the object at index i on the CustomArrayList.
     * 
     * @param i # of the item in the CustomArrayList.
     * @return item at i;
     */
    public E get(int i) {
        if (i > this.listSize -1 || i < 0) {
            throw new IllegalArgumentException("CustomArrayList out of bounds.");
        }
        return (E) this.array[i];
    }
    
    /**
     * Adds object to the list.
     * 
     * @param e object to be added.
     */
    public void add(E e) {
  
        if (this.listSize == this.array.length) {
            this.lenghten();
        }
        this.array[this.listSize] = e;
        this.listSize++;
    }
    

    // makes the array longer if it fills up.
    private void lenghten() {
        Object[] newArray = new Object[this.array.length*2];
        for (int i = 0; i < this.array.length ; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }
    
    /**
     * Returns true if the CustomArrayList is empty.
     * 
     * @return is empty?
     */
    public boolean isEmpty() {
        if ( this.listSize == 0 ) {
            return true;
        }
        return false;
    }
    
    /**
     * Clears the CustomArrayList and removes all objects in it.
     */
    public void clear() {
        this.array = new Object[startSize];
        this.listSize = 0;
    }


}
