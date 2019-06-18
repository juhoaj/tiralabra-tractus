/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhojuutilainen
 */
public class NodeTest {
    private Node node0;
    private Node node1;
    private Node node2;
    

    
    @Before
    public void setUp() {
        this.node0 = new Node(0);
        this.node1 = new Node(1);
        this.node2 = new Node(2);
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
    public void vantSetItselfLeftChild() {
        try {
            this.node0.setLeftmostChild(node0);
            fail("Cannot set itself as leftmost child.");
        } catch (IllegalArgumentException e) { }
    }
    
    @Test
    public void vantSetItselfSibling() {
        try {
            this.node0.setLeftmostChild(node0);
            fail("Cannot set itself as sibling.");
        } catch (IllegalArgumentException e) { }
    }
    
    
    
}
