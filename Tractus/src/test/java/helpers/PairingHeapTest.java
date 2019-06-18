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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    
    @Before
    public void setUp() {
        this.pairingHeap  = new PairingHeap();
        this.node1 = mock(Node.class);
        this.node2 = mock(Node.class);
        this.node3 = mock(Node.class);
        this.node4 = mock(Node.class);
        this.node5 = mock(Node.class);
        this.node6 = mock(Node.class);
        when(this.node1.getValue()).thenReturn(1);
        when(this.node2.getValue()).thenReturn(2);
        when(this.node3.getValue()).thenReturn(3);
        when(this.node4.getValue()).thenReturn(4);
        when(this.node5.getValue()).thenReturn(5);
        when(this.node6.getValue()).thenReturn(6);
        
    }

    @Test
    public void returnsIsEmptyTrue() {
        assertEquals(true, this.pairingHeap.isEmpty());
    }
    
    @Test
    public void inserAddsNodeAndIsEmptyFalse() {
        this.pairingHeap.push(node1);
        assertEquals(false, this.pairingHeap.isEmpty());
    }
    
    @Test
    public void insertionTest1() {
        this.pairingHeap.push(node1);
        this.pairingHeap.push(node2);
        assertEquals(node1, this.pairingHeap.pop());
    }
    
    @Test
    public void insertionTest2() {
        this.pairingHeap.push(node2);
        this.pairingHeap.push(node1);
        this.pairingHeap.push(node3);
        assertEquals(node1, this.pairingHeap.pop());
    }
    
    @Test
    public void insertionTest3() {
        this.pairingHeap.push(node2);
        this.pairingHeap.push(node1);
        this.pairingHeap.push(node3);
        this.pairingHeap.push(node5);
        assertEquals(node1, this.pairingHeap.pop());
    }
    
    @Test
    public void popTest1() {
        this.pairingHeap.push(node2);
        this.pairingHeap.push(node1);
        this.pairingHeap.push(node3);
        assertEquals(node1, this.pairingHeap.pop());
        assertEquals(node2, this.pairingHeap.pop());
        assertEquals(node3, this.pairingHeap.pop());
    }


    
}
