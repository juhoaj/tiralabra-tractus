/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 * Implementation of minimum pairing heap for objects that implement Node object.
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
    
    
    public Node peek() {
        if (this.topNode == null) {
            return null;
        }
        return this.topNode;
    }
        
    private void merge(Node node1, Node node2) {
        
        if (heap1 == null=
          return heap2
        elsif heap2 is Empty
          return heap1
        elsif heap1.elem < heap2.elem
          return Heap(heap1.elem, heap2 :: heap1.subheaps)
        else
          return Heap(heap2.elem, heap1 :: heap2.subheaps)
    }
    
    function insert(elem: Elem, heap: PairingHeap[Elem]) -> PairingHeap[Elem]
  return merge(Heap(elem, []), heap)
    }
    
    
    
    
    
    
    
}
