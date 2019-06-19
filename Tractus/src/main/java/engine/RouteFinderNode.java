/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import helpers.PairingHeapNode;

/**
 * Node used by RouteFinder. Implements PairingHeapNode as it is required by 
 * PairingHeap that is used in RouteFinder.
 * 
 * @author juhojuutilainen
 */
public class RouteFinderNode implements PairingHeapNode<RouteFinderNode>  {
    private int x;
    private int y;
    private int g;
    private int h;
    private int f;
    private RouteFinderNode parent;
    private RouteFinderNode leftmostChild;
    private RouteFinderNode sibling;

    /**
     * Constructor for A* algorithm normal node.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @param g distance between the current node and the start node
     * @param h heuristic — estimated distance from the current node to the end
     * @param f total cost of the node
     * @param parent parent Node (node where the algorithm is coming from)
     */
    public RouteFinderNode(int x, int y, int g, int h, int f, RouteFinderNode parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = f;
        this.parent = parent;
        
        this.leftmostChild = null;
        this.sibling = null;
    }

    /**
     * Constructor for A* algorithm root node that has no parent.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @param g distance between the current node and the start node
     * @param h heuristic — estimated distance from the current node to the end
     * @param f total cost of the node
     */
    public RouteFinderNode(int x, int y, int g, int h, int f) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = f;
        this.parent = null;
    }

    
    /**
     * Return node's x-coordinate. Used by RouteFinder.
     *
     * @return node's x-coordinate
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Return node's y-coordinate. Used by RouteFinder.
     * 
     * @return node's y-coordinate
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Return node's position. Used by RouteFinder.
     * 
     * @return {x-coordinate,y-coordinate}
     */
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = this.x;
        position[1] = this.y;
        return position;
        
    }
    
    /**
     * Get distance from start node. Used by RouteFinder.
     * 
     * @return distance between the current node and the start node
     */
    public int getG() {
        return this.g;
    }
    
    /**
     * Get total cost (distance from start node and heuristic to end node). Used by RouteFinder.
     * 
     * @return total cost of the node
     */
    public int getF() {
        return this.f;
    }
    
    /**
     * Get parent node. Used by RouteFinder.
     * 
     * @return parent node.
     */
    public RouteFinderNode getParent() {
        return this.parent;
    }

    /**
     * Value of node. Used by PairingHeap.
     * 
     * @return value
     */
    @Override
    public int getValue() {
        return this.f;
    }
    
    /**
     * Returns child Node. Used by PairingHeap.
     * 
     * @return leftmost child
     */
    @Override
    public RouteFinderNode getLeftmostChild() {
        return this.leftmostChild;
    }
    
    /**
     * Returns next sibling Node. Used by PairingHeap.
     * 
     * @return sibling
     */
    @Override
    public RouteFinderNode getSibling() {
        return this.sibling;
    }

    /**
     * Sets next sibling Node. Used by PairingHeap.
     * 
     * @param node sibling
     */
    @Override
    public void setSibling(RouteFinderNode node) {
        if (node == this) {
            throw new IllegalArgumentException("Cannot set itself as sibling.");
        }
        this.sibling = node;
    }
    
    /**
     * Sets child Node. Used by PairingHeap.
     * 
     * @param node leftmost child
     */   
    @Override
    public void setLeftmostChild(RouteFinderNode node) {
        if (node == this) {
            throw new IllegalArgumentException("Cannot set itself as leftmost child.");
        }
        this.leftmostChild = node;
    } 

    /**
     * Sets child Node. Used by PairingHeap.
     * 
     * @param node leftmost child
     */   
    @Override
    public int compareTo(PairingHeapNode node) {
        if(this.f > node.getValue()) {
            return 1;
        } else if (this.f < node.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
    
    
    
    
    
    
    
}
