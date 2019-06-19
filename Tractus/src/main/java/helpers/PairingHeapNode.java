/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author juhojuutilainen
 */
public interface PairingHeapNode<E> extends Comparable<PairingHeapNode>{
    public int getValue();
    public PairingHeapNode getLeftmostChild();
    public void setLeftmostChild(E node);
    public PairingHeapNode getSibling();
    public void setSibling(E node);
}
