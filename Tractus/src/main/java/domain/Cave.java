/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import helpers.CustomArrayList;
//import java.util.ArrayList;

/**
 * 
 */
public class Cave implements Comparable<Cave>{
    private int someX;
    private int someY;
    private CustomArrayList<Tile> tiles;

    public Cave(CustomArrayList<Tile> tiles, int someX, int someY) {
        if (someX < 0 || someY < 0) {
            throw new IllegalArgumentException("Map has no negative coordinates");
        }

        this.tiles = tiles;
        this.someX = someX;
        this.someY = someY;
    }
    
    public int getSize(){
        return this.tiles.size();
    }
    
    public CustomArrayList<Tile> getTiles() {
        return this.tiles;
    }
    
    @Override
    public int compareTo(Cave cave) {
        if(this.tiles.size() > cave.getSize()) {
            return 1;
        } else if (this.tiles.size() < cave.getSize()) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
