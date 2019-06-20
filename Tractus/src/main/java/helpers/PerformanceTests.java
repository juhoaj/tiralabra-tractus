/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import helpers.CustomArrayList;
import helpers.Node;
import helpers.PairingHeap;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * This class is used only to test performance of helpers.
 */
public class PerformanceTests {
    
    private int ArrayListTestSize = 1000;
    private int HeapTestSize = 10000;
    

    public boolean runTests() {
        if (
            this.testArraylist() == true &&
            this.testCustomArraylist() == true &&
            this.testPriorityQueAscending() == true &&
            this.testPriorityQueDescending() == true &&
            this.testPairingHeapAscending() == true &&
            this.testPairingHeapDescending() == true
        ) {
            return true;
        }
        return false;
    }
    /**
     * Runs tests.
     * @return true if all tests run
     */
    private boolean testArraylist() {
        long before = System.currentTimeMillis();
        ArrayList<Integer> testArrayList = new ArrayList<>();
        for (Integer i = 0 ; i < ArrayListTestSize ; i++) {
            testArrayList.add(i);
        }
        for (Integer i = 0 ; i < ArrayListTestSize ; i++) {
            int testInt = testArrayList.get(i);
        }
        long time = System.currentTimeMillis() - before;
        System.out.println("ArrayList: " + time);
        return true;
    }

    private boolean testCustomArraylist() {
        long before = System.currentTimeMillis();
        CustomArrayList<Integer> testArrayList = new CustomArrayList<>();
        for (Integer i = 0 ; i < ArrayListTestSize ; i++) {
            testArrayList.add(i);
        }
        for (Integer i = 0 ; i < ArrayListTestSize ; i++) {
            int testInt = testArrayList.get(i);
        }
        long time = System.currentTimeMillis() - before;
        System.out.println("CustomArrayList: " + time);
        return true;
    }
    
    private boolean testPriorityQueAscending() {
        long before = System.currentTimeMillis();
        PriorityQueue<Node> testPriorityQue = new PriorityQueue<>();
        for (Integer i = 0 ; i < HeapTestSize ; i++) {
            testPriorityQue.add(new Node(i));
        }
        for (Integer i = 0 ; i < HeapTestSize / 2 ; i++) {
            Node testNode = testPriorityQue.poll();
        }
        long time = System.currentTimeMillis() - before;
        System.out.println("PriorityQueAscending " + time);
        return true;
    }
    
    private boolean testPriorityQueDescending() {
        long before = System.currentTimeMillis();
        PriorityQueue<Node> testPriorityQue = new PriorityQueue<>();
        int j = HeapTestSize;
        for (Integer i = 0 ; i < HeapTestSize ; i++) {
            testPriorityQue.add(new Node(j--));
        }
        for (Integer i = 0 ; i < HeapTestSize / 2 ; i++) {
            Node testNode = testPriorityQue.poll();
        }
        long time = System.currentTimeMillis() - before;
        System.out.println("PriorityQueDescending: " + time);
        return true;
    }
    
    private boolean testPairingHeapAscending() {
        long before = System.currentTimeMillis();
        PairingHeap<Node> testPairingHeap = new PairingHeap<>();
        for (Integer i = 0 ; i < HeapTestSize ; i++) {
            testPairingHeap.push(new Node(i));
        }
        for (Integer i = 0 ; i < HeapTestSize / 2 ; i++) {
            Node testNode = testPairingHeap.pop();
            
        }
        long time = System.currentTimeMillis() - before;
        System.out.println("PairingHeapAscending: " + time);
        return true;
    }
    
    private boolean testPairingHeapDescending() {
        long before = System.currentTimeMillis();
        PairingHeap<Node> testPairingHeap = new PairingHeap<>();
        int j = HeapTestSize;
        for (Integer i = 0 ; i < HeapTestSize ; i++) {
            testPairingHeap.push(new Node(j--));
        }
        for (Integer i = 0 ; i < HeapTestSize / 2 ; i++) {
            Node testNode = testPairingHeap.pop();
        }
        long time = System.currentTimeMillis() - before;
        System.out.println("PairingHeapDescending: " + time);
        return true;
    }
    
    
}
