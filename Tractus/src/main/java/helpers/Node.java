/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 * <h1>Node</h1>
 * Nodes can be used by route finding algorithms.
 * 
 * @author juhojuutilainen
 */
public class Node implements Comparable<Node>{
    private int x;
    private int y;
    private int g;
    private int h;
    private int f;
    private Node parent;

    /**
     * Constructor for normal node.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @param g distance between the current node and the start node
     * @param h heuristic — estimated distance from the current node to the end
     * @param f total cost of the node
     * @param parent parent Node (node where the algorithm is coming from)
     */
    public Node(int x, int y, int g, int h, int f, Node parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = f;
        this.parent = parent;
    }

    /**
     * Constructor for root node that has no parent.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @param g distance between the current node and the start node
     * @param h heuristic — estimated distance from the current node to the end
     * @param f total cost of the node
     */
    public Node(int x, int y, int g, int h, int f) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = f;
        this.parent = null;
    }
    
    /**
     * Return node's x-coordinate.
     *
     * @return node's x-coordinate
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Return node's y-coordinate.
     * 
     * @return node's y-coordinate
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Return node's position.
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
     * Get distance from start node. 
     * 
     * @return distance between the current node and the start node
     */
    public int getG() {
        return this.g;
    }
    
    /**
     * Get total cost (distance from start node and heuristic to end node)
     * 
     * @return total cost of the node
     */
    public int getF() {
        return this.f;
    }
    
    /**
     * Get parent node.
     * 
     * @return parent node.
     */
    public Node getParent() {
        return this.parent;
    }

    @Override
    public int compareTo(Node node) {
        if(this.f > node.getF()) {
            return 1;
        } else if (this.f < node.getF()) {
            return -1;
        } else {
            return 0;
        }
    }
    

}