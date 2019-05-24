/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Direction;
import domain.Creature;
import domain.World;

/**
 *
 * @author juhojuutilainen
 */
public class PlayerAction {

    private Creature player;
    private World world;
    private boolean playerTurn;

    public PlayerAction(Creature player, World world) {
        this.player = player;
        this.world = world;
        this.playerTurn = false;
    }

    public boolean getActionHappened() {
        System.out.println("pitäs olla false");
        System.out.println("happened");
        return true;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setAction(Direction d) {
        System.out.println(d);
        if (this.playerTurn == true) {
            int newY = this.player.getY();
            int newX = this.player.getX();
            switch (d) {
                
                case NORTH:
                    System.out.println("N");
                    newY--;
                    if (this.world.getTerrain(this.player.getX(), newY) == 0) {
                        this.player.setY(newY);
                    }
                    break;
                case EAST:
                    System.out.println("E");
                    newX++;
                    if (this.world.getTerrain(newX, this.player.getY()) == 0) {
                        this.player.setX(newX);
                    }
                    break;
                case SOUTH:
                    System.out.println("S");
                    newY++;
                    if (this.world.getTerrain(this.player.getX(), newY) == 0) {
                        this.player.setY(newY);
                    }
                    break;
                case WEST:
                    System.out.println("W");
                    newX--;
                    if (this.world.getTerrain(newX, this.player.getY()) == 0) {
                        this.player.setX(newX);
                    }
                    break;
            }
        }
    }
}
