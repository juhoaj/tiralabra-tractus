/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    private Node node0;
    private Node node1;
    

    
    @Before
    public void setUp() {
        this.node0 = new Node(0);
        this.node1 = new Node(1);
    }
    

    @Test
    public void classInitialized() {
        assertNotNull(this.node0);
    } 
    
    @Test
    public void negativeValueThrowsError() {
        try {
            Node node = new Node(-1);
            fail("Value must be positive.");
        } catch (IllegalArgumentException e) { }
    }
        
    @Test
    public void getValueWorks() {
        assertEquals(0, this.node0.getValue());
    }
    
    @Test
    public void setLeftmostChildAndGetLeftmostChildWork() {
        assertEquals(null, this.node0.getLeftmostChild());
        this.node0.setLeftmostChild(node1);
        assertEquals(node1, this.node0.getLeftmostChild());
    }
    
    @Test
    public void setSiblingAndGeSiblingWork() {
        assertEquals(null, this.node0.getSibling());
        this.node0.setSibling(node1);
        assertEquals(node1, this.node0.getSibling());
    }
    
    @Test
    public void cantSetItselfLeftChild() {
        try {
            this.node0.setLeftmostChild(node0);
            fail("Cannot set itself as leftmost child.");
        } catch (IllegalArgumentException e) { }
    }
    
    @Test
    public void vantSetItselfSibling() {
        try {
            this.node0.setSibling(node0);
            fail("Cannot set itself as sibling.");
        } catch (IllegalArgumentException e) { }
    }
    
    @Test
    public void compareToWorks() {
        assertEquals(0, this.node0.compareTo(this.node0));
        assertEquals(-1, this.node0.compareTo(this.node1));
        assertEquals(1, this.node1.compareTo(this.node0));
        
    }
    
}
