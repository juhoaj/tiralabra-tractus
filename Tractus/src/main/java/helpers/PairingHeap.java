/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 * Implementation of minimum pairing heap for stack of Node objects.
 * Returns null if empty.
 * @author juhojuutilainen
 */
public class PairingHeap {
    
    private Node topNode;
    
    
    public boolean isEmpty() {
        if (this.topNode == null) {
            return true;
        }
        return false;
    }
    
    
    // find-min
    public Node top() {
        if (this.topNode == null) {
            return null;
        }
        return this.topNode;
    }
        
    private void merge(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        } else if (node1.getValue() < node2.getValue()) {
            // lisätään node 2 node 1 vasemmaksi
            // korjataan lapsien relaatiot
            // palautetaan node 1
            
        } else {
            // ylläoleva päinvastoin
        }
    }
    
    // find-min && delete-min
    public Node pop() {
        if (this.topNode == null) {
            return null;
        }
        Node returnNode = this.topNode;
        
        if (this.topNode.getLeftmostChild() == null) {
            this.topNode = null;
        } else {
            // this.topNode = mergePairs(jotenkin taiotaan mergePairs:lle topNoden lapset);
        }
        
        return returnNode;
        
    }
    
    private Node mergePairs(Node[] nodes) {
        if (nodes.length == 0) {
            return null;
        } else if (nodes.length == 1) {
            return nodes[0];
        } else {
          return merge(merge(nodes[0], nodes[1]), merge-pairs(nodes[2.. ]))
        }
    }
    
    // insert
    public void push(Node node) {
        this.topNode = this.merge(this.topNode, node);
    }
    
    
    
    
    
    
    
    
}
