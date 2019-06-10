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
    private GameController gameController;
    private boolean playerTurn;
    private boolean gameRunning;

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
     * Used to determine if player input is resolved or not.
     * Set to false during monster turn.
     *
     * @param playerTurn set true to process user input
     */
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    /**
     * Tell's PlayerController that the game is running and player input
     * should be resolved. Set to true in the beginning of the game and
     * false in the end of the game.
     * 
     * @param gameRunning Set true if game is on!
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
        
        // resolved when the game is not running

        if (this.gameRunning == false && command == command.WEST) {
            this.gameController.startGame();
        }
        
        // resolved when the game is running

        if (this.playerTurn == true && this.gameRunning == true) {
            int newY = this.player.getY();
            int newX = this.player.getX();
            switch (command) {

                case NORTH:
                    newY--;
                    if (this.world.getTerrain(this.player.getX(), newY) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
                case EAST:
                    newX++;
                    if (this.world.getTerrain(newX, this.player.getY()) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
                case SOUTH:
                    newY++;
                    if (this.world.getTerrain(this.player.getX(), newY) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
                case WEST:
                    newX--;
                    if (this.world.getTerrain(newX, this.player.getY()) == 1) {
                        this.moveTo(newX, newY);
                    }
                    break;
            }
            
        }
    }

    /**
     * Moves the player on the map.
     * 
     * @param newX X-coordinate where the player is moved to
     * @param newY Y-coordinate where the player is moved to
     */
    public void moveTo(int newX, int newY) {
        if ( newX < 0 ||
             newY < 0 ||
             newX > this.world.getWidth() -1 ||
             newY > this.world.getHeight()-1 
            ) {
            throw new IllegalArgumentException("Cannot move player outside map.");
        }
        this.player.setX(newX);
        this.player.setY(newY);
        this.playerTurn = false;
        this.gameController.playerActed();
    }

    /**
     * Get player's position
     * 
     * @return {x-coordinate,y-coordinate}
     */
    public int[] getPlayerPosition() {
        return this.player.getPosition();
    }
    


    /**
     * Inserts player on the center of the map.
     * Used in the beginning of the game.
     */
    public boolean insertPlayer() {
        int startPositionX = this.world.getWidth() / 2;
        int startPositionY = this.world.getHeight() / 2;
        while (true) {
            if (this.world.getTerrain(startPositionX, startPositionY) == 1 && this.world.getConnected(startPositionX, startPositionY) == true) {
            
               this.moveTo(startPositionX, startPositionY);
                System.out.println("..player's x,y-terrain "+startPositionX + "," + startPositionY + "-" + this.world.getTerrain(startPositionX, startPositionY));
                break;
            } else if (startPositionX == 0) {
                System.out.println("..suitable starting position was not sound");
                return false;
            }
            else {
                startPositionX--;
            }
        }
        return true;
    }
    
    
    /**
     * Returns true if game is running. Used for testing.
     *  
     */
    public boolean getGameRunning() {
        return this.gameRunning;
    }
    
    /**
     * Returns true if it is player's turn. Used for testing.
     *  
     */
    public boolean getPlayerTurn() {
        return this.playerTurn;
    }
}
