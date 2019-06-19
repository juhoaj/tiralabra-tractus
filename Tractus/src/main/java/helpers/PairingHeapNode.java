/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 * Defines interface for node object used by PairingHeap.
 * @author juhojuutilainen
 * @param <E> Class of the implemented object. Hack used to cast returns to right class.
 */
public interface PairingHeapNode<E> extends Comparable<PairingHeapNode>{

    /**
     * Value of node.
     * 
     * @return value
     */
    public int getValue();

    /**
     * Returns child Node.
     * @return leftmost child
     */
    public PairingHeapNode getLeftmostChild();

    /**
     * Sets child Node.
     * @param node leftmost child
     */   
    public void setLeftmostChild(E node);

    /**
     * Returns next sibling Node.
     * @return sibling
     */
    public PairingHeapNode getSibling();

    /**
     * Sets next sibling Node.
     * @param node sibling
     */
    public void setSibling(E node);
}
