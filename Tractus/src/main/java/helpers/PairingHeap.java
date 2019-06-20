package helpers;

// import helpers.CustomArrayList;

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
    
    // implements twoPass
    private PairingHeapNode twoPass() {
        
        // collect nodes
        PairingHeapNode[] siblings = this.collectSiblings();
        
        // first pass
        int i = 0;
        while (true) {
            if (siblings[i] == null) {
                break;
            }
            siblings[i] = merge(siblings[i], siblings[i+1]);
            i+=2;
        }
        
        PairingHeapNode returnedNode = null;
        
        // second pass
        while (true) {
            returnedNode = merge(returnedNode, siblings[i]);
            if (i==0) {
                break;
            }
            i-=2;
        }


        return returnedNode;
    }

    // used by twoPass
    private PairingHeapNode[] collectSiblings() {
        PairingHeapNode[] siblings = new PairingHeapNode[24];
        siblings[0] = this.topNode.getLeftmostChild();
        int i = 1;
        while (true) {
            if (i == siblings.length) {
                siblings = this.enlarge(siblings);
            }
            PairingHeapNode nextNode = siblings[i - 1].getSibling();
            if (nextNode == null) {
                break;
            }
            siblings[i] = nextNode;
            i++;
        }
        return siblings;
    }
    
    // enlarges array if it is full, used by twoPass
    private PairingHeapNode[] enlarge(PairingHeapNode[] array) {
        int oldSize = array.length;
        PairingHeapNode[] newArray = new PairingHeapNode[oldSize*2];
        for (int i=0 ; i < oldSize ; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    
    
    
    
    
    
    
    
}
