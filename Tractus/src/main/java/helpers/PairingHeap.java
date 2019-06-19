package helpers;

import helpers.CustomArrayList;

/**
 * Offers stack of Node objects. Naive implementation of minimum pairing heap that 
 * cuts corners by not utilizing previous siblings and using CustomArrayList. 
 * Returns null if empty.
 * @author juhojuutilainen
 */
public class PairingHeap<E> {
    
    private PairingHeapNode topNode;
    private int size;

    public PairingHeap() {
        this.size = 0;
    }

    /**
     * Tells if the stack is empty.
     * 
     * @return true if the stack is empty.
     */
    public boolean isEmpty() {
        if (this.topNode == null) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Adds PairingHeapNode into stack, implements insert.
     * @param node added Node
     */
    public void push(PairingHeapNode node) {
        this.topNode = this.merge(this.topNode, node);
        this.size++;
    }
    
 
    /**
     * Returns the smallest PairingHeapNode in the stack. 
     * Does not remove it from the stack.
     * Implements find-min.
     * 
     * @return smallest Node
     */
    public E top() {
        if (this.topNode == null) {
            return null;
        }
        return (E) this.topNode;
    }

    
    /**
     * Retuns the smallest PairingHeapNode and removes it from the stack.
     * Implements find-min && delete-min
     * 
     * @return 
     */
    public E pop() {
        if (this.topNode == null) {
            return null;
        }
        PairingHeapNode returnNode = this.topNode;
        
        if (this.topNode.getLeftmostChild() == null) {
            this.topNode = null;
        } else {
            this.topNode = this.twoPass();
        }
        this.size--;
        return (E) returnNode;
        
    }
    
    /**
     * Size of the Pairing Heap.
     * @return size
     */
    public int size() {
        return this.size;
    }
    
    
    // implements merge    
    private PairingHeapNode merge(PairingHeapNode node1, PairingHeapNode node2) {
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        } else if (node1.getValue() < node2.getValue()) {
            node2.setSibling(node1.getLeftmostChild());
            node1.setLeftmostChild(node2);
            return node1;
        } 
        node1.setSibling(node2.getLeftmostChild());
        node2.setLeftmostChild(node1);
        return node2;
    }
    
    
    private PairingHeapNode twoPass() {
        CustomArrayList<PairingHeapNode> siblings = new CustomArrayList<>();
        this.collectSiblings(this.topNode.getLeftmostChild(), siblings);
        int pairs = siblings.size() -   siblings.size() % 2;
        
        // first pass
        
        CustomArrayList<PairingHeapNode> pairedSiblings = new CustomArrayList<>();
        for (int i = 0 ; i < pairs ; i+=2) {
            pairedSiblings.add( this.merge( siblings.get(i), siblings.get(i+1) ) );
        }
        if (siblings.size() % 2 == 1) {
            pairedSiblings.add(siblings.get(siblings.size()-1));
        }
        
        // second pass
        PairingHeapNode returnedNode = pairedSiblings.get(pairedSiblings.size() -1);
        for (int i = pairedSiblings.size() -2 ; i >= 0 ; i--) {
            returnedNode = this.merge(returnedNode, pairedSiblings.get(i));
        }
        return returnedNode;
    }

    private CustomArrayList<PairingHeapNode> collectSiblings(PairingHeapNode node, CustomArrayList<PairingHeapNode> collectedNodes) {
        collectedNodes.add(node);
        if (node.getSibling() == null) {
            return collectedNodes;
        }
        collectSiblings(node.getSibling(), collectedNodes);
        node.setSibling(null);
        return collectedNodes;
        
    }

    
    
    
    
    
    
    
    
}
