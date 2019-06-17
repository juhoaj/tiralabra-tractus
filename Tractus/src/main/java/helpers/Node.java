/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author juhojuutilainen
 */
public class Node implements Comparable<Node> {
    private int value;
    private Node leftmostChild;
    private Node sibling;
    
    public Node(int value) {
       this.value = value;
       this.leftmostChild = null;
       this.sibling = null;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public Node getLeftmostChild() {
        return this.leftmostChild;
    }
    
    public Node getSibling() {
        return this.sibling;
    }

    public void setSibling(Node node) {
        this.sibling = node;
    }

    public void setLeftmostChild(Node node) {
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
    
    
    
    
    
