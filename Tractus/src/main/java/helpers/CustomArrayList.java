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
 */
public class CustomArrayList {
    private Object[] array;
    private int listSize;
    private Class objectClass;
    private boolean hasBeenInitialized;

    
    public CustomArrayList(){
        this.array = new Object[64];
        this.listSize = 0;
    }
     
    /**
     * Constructor with option for setting starting length for array
     * @param <T>
     * @param arrayLength 
     */
    public CustomArrayList(int arrayLength) {
        this.hasBeenInitialized = false;
        this.array = new Object[arrayLength];
        this.listSize = 0;
    }
    
    public int size() {
        return this.listSize;
    }
    
    public Object get(int i) {
        if (i > this.listSize -1 || i < 0) {
            throw new IllegalArgumentException("CustomArrayList out of bounds.");
        }
        return this.array[i];
    }
    
    public void add(Object object) {
        if (this.hasBeenInitialized == false) {
            this.objectClass = object.getClass();
            this.hasBeenInitialized = true;
        }
        if (! object.getClass().equals(this.objectClass)) {
            throw new IllegalArgumentException("Inserted object of wrong class.");
        }
        if (this.listSize == this.array.length) {
            this.lenghten();
        }
        this.array[this.listSize] = object;
        this.listSize++;
    }
    

    
    private void lenghten() {
        Object[] newArray = new Object[this.array.length*2];
        for (int i = 0; i < this.array.length ; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }
    
    
    


}
