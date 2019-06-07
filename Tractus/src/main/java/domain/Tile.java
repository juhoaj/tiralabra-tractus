/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author juhojuutilainen
 */
public class Tile {
    private int tileX;
    private int tileY;

    public Tile(int tileX, int tileY) {
        if (tileX < 0 || tileY < 0) {
            throw new IllegalArgumentException("Map has no negative coordinates");
        }
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public int getX() {
        return tileX;
    }

    public int getY() {
        return tileY;
    }
    
    
    
}
