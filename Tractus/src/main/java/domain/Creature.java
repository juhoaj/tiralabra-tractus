/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 * <h1>Creature</h1>
 * This class is used for all creatures including player(s) and monsters.
 */
public class Creature {

    private int x;
    private int y;

    
    /**
     * Creature constructor without starting coordinates, please note that
     * if coordinates are not set creatures position is -100,-100;
     * */
    public Creature() {
        this.x = -100;
        this.y = -100;
    }
    
    /**
     * Creature constructor with starting coordinates of the creature.
     *
     * @param startX X-coordinate where the creature is placed when created
     * @param startY Y-coordinate where the creature is placed when created
     */
    public Creature(int startX, int startY) {

        this.x = startX;
        this.y = startY;
    }

    /**
     * Get creature's x-coordinate
     *
     * @return x-coordinate of the creature
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get creature's y-coordinate
     *
     * @return y-coordinate of the creature
     */
    public int getY() {
        return this.y;
    }

    /**
     * Used for setting the creatures new x-coordinate
     *
     * @param newX new x-coordinate
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Used for setting the creatures new y-coordinate
     *
     * @param newY new y-coordinate
     */
    public void setY(int newY) {

        this.y = newY;
    }

}
