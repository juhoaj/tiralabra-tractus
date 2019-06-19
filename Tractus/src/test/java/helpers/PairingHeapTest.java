/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhojuutilainen
 */
public class PairingHeapTest {
    
    PairingHeap pairingHeap;
    Node node1;
    Node node2;
    Node node3;
    Node node4;
    Node node5;
    Node node6;
    Node node7;
    
    @Before
    public void setUp() {
        this.pairingHeap  = new PairingHeap();
        this.node1 = new Node(1);
        this.node2 = new Node(2);
        this.node3 = new Node(3);
        this.node4 = new Node(4);
        this.node5 = new Node(5);
        this.node6 = new Node(6);
        this.node7 = new Node(7);
        
    }

    @Test
    public void returnsIsEmptyTrue() {
        assertEquals(true, this.pairingHeap.isEmpty());
    }
    
    @Test
    public void inserAddsNodeAndIsEmptyFalse() {
        this.pairingHeap.push(this.node1);
        assertEquals(false, this.pairingHeap.isEmpty());
    }
    
    @Test
    public void insertionTest1() {
        this.pairingHeap.push(this.node1);
        this.pairingHeap.push(this.node2);
        assertEquals(this.node1, this.pairingHeap.pop());
    }
    
    @Test
    public void insertionTest2() {
        this.pairingHeap.push(this.node2);
        this.pairingHeap.push(this.node1);
        this.pairingHeap.push(this.node3);
        assertEquals(this.node1, this.pairingHeap.pop());
    }
    
    @Test
    public void insertionTest3() {
        this.pairingHeap.push(this.node2);
        this.pairingHeap.push(this.node1);
        this.pairingHeap.push(this.node3);
        this.pairingHeap.push(this.node5);
        assertEquals(this.node1, this.pairingHeap.pop());
    }
    
    @Test
    public void popTest1() {
        this.pairingHeap.push(this.node2);
        this.pairingHeap.push(this.node1);
        this.pairingHeap.push(this.node3);
        assertEquals(this.node1, this.pairingHeap.pop());
        assertEquals(this.node2, this.pairingHeap.pop());
        assertEquals(this.node3, this.pairingHeap.pop());
    }
    
    @Test
    public void popTest2() {
        this.pairingHeap.push(this.node7);
        this.pairingHeap.push(this.node6);
        this.pairingHeap.push(this.node5);
        this.pairingHeap.push(this.node4);
        this.pairingHeap.push(this.node3);
        this.pairingHeap.push(this.node2);
        this.pairingHeap.push(this.node1);
        assertEquals(this.node1, this.pairingHeap.pop());
        assertEquals(this.node2, this.pairingHeap.pop());
        assertEquals(this.node3, this.pairingHeap.pop());
        assertEquals(this.node4, this.pairingHeap.pop());
        assertEquals(this.node5, this.pairingHeap.pop());
        assertEquals(this.node6, this.pairingHeap.pop());
        assertEquals(this.node7, this.pairingHeap.pop());
    }

    
}
