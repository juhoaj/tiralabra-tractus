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
 * Responsible for receiving player's commands and determing outcome.
 * Receives method calls from ui layer (ui.KeyListener to be more specific).
 * Determines the consequence of player input, mainly if the player can move to
 * a given direction.
 */
public class PlayerController {

    private Creature player;
    private World world;
    private boolean playerTurn;
    private boolean gameRunning;
    private GameController gameController;

    /**
     * Constructor for PlayerAction.
     *
     * @param player object that is being controlled by the player
     * @param world contains and controls the map
     * @param gameController controls the game
     */
    public PlayerController(Creature player, World world, GameController gameController) {
        this.player = player;
        this.world = world;
        this.playerTurn = false;
        this.gameRunning = true;
        this.gameController = gameController;
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
     *
     * @param gameRunning
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Receives calls from ui layer (ui.KeyListener to be more specific) and
     * calls player object's methods if action is possible.
     *
     * @param command utilizes enumeration domain.Command
     */
    public void setAction(Command command) {
        System.out.println(command);

        if (this.gameRunning == false && command == command.WEST) {
            this.gameController.startGame();
        }

        if (this.playerTurn == true && this.gameRunning == true) {
            int newY = this.player.getY();
            int newX = this.player.getX();
            switch (command) {

                case NORTH:
                    System.out.println("N");
                    newY--;
                    if (this.world.getTerrain(this.player.getX(), newY) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
                case EAST:
                    System.out.println("E");
                    newX++;
                    if (this.world.getTerrain(newX, this.player.getY()) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
                case SOUTH:
                    System.out.println("S");
                    newY++;
                    if (this.world.getTerrain(this.player.getX(), newY) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
                case WEST:
                    System.out.println("W");
                    newX--;
                    if (this.world.getTerrain(newX, this.player.getY()) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
            }
            
        }
    }

    /**
     *
     * @param newX
     * @param newY
     */
    public void moveTo(int newX, int newY) {
        System.out.println("vastaanotettu movessa");
        this.player.setX(newX);
        this.player.setY(newY);
        System.out.println("player x,y:" + newX + "," + newY);
        this.playerTurn = false;
        this.gameController.playerActed();
    }

    /**
     *
     * @return {x-coordinate,y-coordinate}
     */
    public int[] getPlayerPosition() {
        return this.player.getPosition();
    }

    /**
     *
     */
    public void insertPlayer() {
        int startPositionX = this.world.getWidth() / 2;
        int startPositionY = this.world.getHeight() / 2;
        System.out.println(startPositionX + "," + startPositionY + "-" + this.world.getTerrain(startPositionX, startPositionY));
        while (true) {
            if (this.world.getTerrain(startPositionX, startPositionY) == 1) {
                this.moveTo(startPositionX, startPositionY);
                break;
            } else {
                startPositionX--;
            }
        }
    }
}
