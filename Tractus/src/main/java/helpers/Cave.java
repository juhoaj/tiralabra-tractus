/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.ArrayList;

/**
 *
 * @author juhojuutilainen
 */
public class Cave implements Comparable<Cave>{
    private int someX;
    private int someY;
    private ArrayList<Node> nodes;

    public Cave(ArrayList<Node> nodes, int someX, int someY) {
        this.nodes =nodes;
        this.someX = someX;
        this.someY = someY;
    }
    
    public int getSize(){
        return this.nodes.size();
    }
    
    public ArrayList<Node> getNodes() {
        return this.nodes;
    }
    
    @Override
    public int compareTo(Cave cave) {
        if(this.nodes.size() > cave.getSize()) {
            return 1;
        } else if (this.nodes.size() < cave.getSize()) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
