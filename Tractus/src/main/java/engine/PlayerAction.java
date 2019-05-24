/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Command;
import domain.Creature;
import domain.World;

/**
 * <h1>PlayerAction</h1>
 * Receives method calls from ui layer (ui.KeyListener to be more specific).
 * Determines the consequence of player input, mainly if the player can move to
 * a given direction.
 */
public class PlayerAction {

    private Creature player;
    private World world;
    private boolean playerTurn;

    /**
     * Constructor for PlayerAction.
     *
     * @param player Player-object that is being controlled.
     * @param world World-object where player moves.
     */
    public PlayerAction(Creature player, World world) {
        this.player = player;
        this.world = world;
        this.playerTurn = false;
    }

    /**
     * Could be used later.
     */
    public boolean getActionHappened() {

        System.out.println("pitäs olla false");
        System.out.println("happened");
        return true;
    }

    /**
     * Used to determine if player input is processed or not.
     *
     * @param playerTurn set true to process user input
     */
    public void setPlayerTurn(boolean playerTurn) {

        this.playerTurn = playerTurn;
    }

    /**
     * Receives calls from ui layer (ui.KeyListener to be more specific) and
     * calls player object's methods if action is possible.
     *
     * @param command utilizes enumeration domain.Command
     */
    public void setAction(Command command) {

        System.out.println(command);
        if (this.playerTurn == true) {
            int newY = this.player.getY();
            int newX = this.player.getX();
            switch (command) {

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
