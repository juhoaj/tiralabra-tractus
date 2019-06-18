package helpers;

import helpers.CustomArrayList;

/**
 * Offers stack of Node objects. Naive implementation of minimum pairing heap that 
 * cuts corners by not utilizing previous siblings and using CustomArrayList. 
 * Returns null if empty.
 * @author juhojuutilainen
 */
public class PairingHeap {
    
    private Node topNode;
    
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
     * Adds Node into stack, implements insert.
     * @param node added Node
     */
    public void push(Node node) {
        this.topNode = this.merge(this.topNode, node);
    }
    
 
    /**
     * Returns the smallest Node in the stack. Does not remove it from the stack.
     * Implements find-min.
     * 
     * @return smallest Node
     */
    public Node top() {
        if (this.topNode == null) {
            return null;
        }
        return this.topNode;
    }

    
    /**
     * Retuns the smallest Node and removes it from the stack.
     * Implements find-min && delete-min
     * 
     * @return 
     */
    public Node pop() {
        if (this.topNode == null) {
            return null;
        }
        Node returnNode = this.topNode;
        
        if (this.topNode.getLeftmostChild() == null) {
            this.topNode = null;
        } else {
            this.topNode = this.twoPass();
        }
        return returnNode;
        
    }
    
    
    // implements merge    
    private Node merge(Node node1, Node node2) {
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
    
    
    private Node twoPass() {
        CustomArrayList<Node> siblings = new CustomArrayList<>();
        this.collectSiblings(this.topNode.getLeftmostChild(), siblings);
        int pairs = siblings.size() -   siblings.size() % 2;
        
        // first pass
        
        CustomArrayList<Node> pairedSiblings = new CustomArrayList<>();
        for (int i = 0 ; i < pairs ; i+=2) {
            pairedSiblings.add( this.merge( siblings.get(i), siblings.get(i+1) ) );
        }
        if (siblings.size() % 2 == 1) {
            pairedSiblings.add(siblings.get(siblings.size()-1));
        }
        
        // second pass
        Node returnedNode = pairedSiblings.get(pairedSiblings.size() -1);
        for (int i = pairedSiblings.size() -2 ; i >= 0 ; i--) {
            this.merge(returnedNode, pairedSiblings.get(i));
        }
        return returnedNode;
    }

    private CustomArrayList<Node> collectSiblings(Node node, CustomArrayList<Node> collectedNodes) {
        collectedNodes.add(node);
        if (node.getSibling() == null) {
            return collectedNodes;
        }
        collectSiblings(node.getSibling(), collectedNodes);
        node.setSibling(null);
        return collectedNodes;
        
    }
    /*
    private Node mergePairs(Node[] nodes) {
        if (nodes.length == 0) {
            return null;
        } else if (nodes.length == 1) {
            return nodes[0];
        } else {
          return merge(merge(nodes[0], nodes[1]), merge-pairs(nodes[2.. ]))
        }
    }
    */

    
    
    
    
    
    
    
    
}
