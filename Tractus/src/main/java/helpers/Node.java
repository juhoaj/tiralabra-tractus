/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 * Node for use with Pairing Heap
 */
public class Node implements Comparable<Node> {
    private int value;
    private Node leftmostChild;
    private Node sibling;
    
    
    /**
     * Constructor requires positive value.
     * @param value of node
     */
    public Node(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive.");
        }
        this.value = value;
        this.leftmostChild = null;
        this.sibling = null;
    }
    
    /**
     * Value of node.
     * @return value
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Returns child Node.
     * @return leftmost child
     */
    
    public Node getLeftmostChild() {
        return this.leftmostChild;
    }
    
    /**
     * Returns next sibling Node.
     * @return sibling
     */
    
    public Node getSibling() {
        return this.sibling;
    }

    /**
     * Sets next sibling Node.
     * @param node sibling
     */
    public void setSibling(Node node) {
        if (node == this) {
            throw new IllegalArgumentException("Cannot set itself as sibling.");
        }
        this.sibling = node;
    }

    /**
     * Sets child Node.
     * @param node leftmost child
     */
    public void setLeftmostChild(Node node) {
        if (node == this) {
            throw new IllegalArgumentException("Cannot set itself as leftmost child.");
        }
        this.leftmostChild = node;
    } 
    
    @Override
    public int compareTo(Node node) {
        if(this.value > node.getValue()) {
            return 1;
        } else if (this.value < node.getValue()) {
            return -1;
        } else {
            return 0;
        }
    } 
}
    
    
    
    
    
