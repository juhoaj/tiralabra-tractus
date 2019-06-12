
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Creature;
import domain.World;
import java.util.Arrays;
import ui.Interface;

/**
 * <h1>GameController</h1>
 * 
 * Responsible for starting and running the game. Receives playerActed() method 
 * call from PlayerController after player has acted and calls PlayerController 
 * with setPlayerTurn(true) after monsters have acted. 
 */
public class GameController {

    private World world;
    private PlayerController playerController;
    private MonsterController monsterController;
    private Interface ui;
    private boolean debugging;


    

    
         
    /**
     * Used to recieve dependencies. 
     * 
     * @param world contains and controls the map
     * @param ui game's user interface, responsible for output and input
     * @param playerController receives imput from ui and controls player-object
     * @param monsterController controls monsters 
     */
    public void addDependencies(World world, Interface ui, PlayerController playerController, MonsterController monsterController, boolean debugging) {
        this.playerController = playerController;
        this.ui = ui;
        this.world = world;
        this.monsterController = monsterController;
        this.debugging = debugging;
    }

    /**
     * Start's a new game. Draw's new map, put's player on map and creates
     * monsters.
     */
    public void startGame() {
        if (this.playerController == null) {
            throw new IllegalArgumentException("Game not initialized with addDependencies");
        }
        
        this.playerController.setGameRunning(true);
     
        boolean insertionSuccesfull = false;
        while ( true) {
            this.world.initializeCaves();
            insertionSuccesfull = this.playerController.insertPlayer();
            if (insertionSuccesfull == true) {
                break;
            }
        }      

        this.monsterController.dumpMonsters();
        this.monsterController.createMonsters(20);
        this.ui.refresh();
        this.ui.message("Use W,A,S&D for movement!");
        this.playerController.setPlayerTurn(true);
        
    }

    /**
     * Responsible for game over.
     */
    public void endGame() {
        this.ui.message("Press 'a' for a new game.");
        this.playerController.setGameRunning(false);
    }

    /**
     * Called by PlayerController after valid keyboard input.
     * Give's turn to monsters.
     */
    public void playerActed() {
        this.ui.refresh();
        this.checkEndgame();
        this.monsterTurn();
    }

    /**
     * Resonsible for monster's turn. Give's turn back to player after
     * monsters actions.
     */
    private void monsterTurn() {
        this.monsterController.monsterActions();
        if (this.debugging != true) {
            this.ui.refresh();
        }
        this.checkEndgame();
        this.playerController.setPlayerTurn(true);
    }

     /**
     * Calls endGame() if game is over.
     */
    private boolean checkEndgame() {
        int[][] monsterpositions = this.monsterController.getMonsterPositions();
        for (int i = 0; i < monsterpositions.length; i++) {
            if (Arrays.equals(monsterpositions[i], this.playerController.getPlayerPosition())) {
                this.endGame();
            }
        }
        return false;
    }

    /**
     * Get player's position in array.
     * 
     * @return {x-coordinate,y-coordinate}
     */
    public int[] getPlayerPosition() {
        return this.playerController.getPlayerPosition();
    }
    
    /**
     * Asks Interface to draw a character on output.
     * Useful for debugging.
     * 
     * @param character character to be drawn
     * @param x x-coordinate of the character
     * @param y y-coordinate of the character
     */
    public void drawCharacter(char character, int x, int y) {
        if (x < 0 ||
            y < 0 ||
            x > this.world.getWidth() -1 ||
            y > this.world.getHeight() -1
            ) {
            throw new IllegalArgumentException("Coordinate outside map.");
        }
        this.ui.drawCharacter(character,x,y);
    }

}
