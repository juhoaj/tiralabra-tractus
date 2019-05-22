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
public class Creature {
    private int x;
    private int y;

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordinate getCoordinate() {
        return new Coordinate(this.x, this.y);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
}
