
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

         
    /**
     *
     * @param world contains and controls the map
     * @param ui game's user interface, responsible for output and input
     * @param playerController receives imput from ui and controls player-object
     * @param monsterController controls monsters 
     */
    public void addDependencies(World world, Interface ui, PlayerController playerController, MonsterController monsterController) {
        System.out.println("dependencies received");
        this.playerController = playerController;
        this.ui = ui;
        this.world = world;
        this.monsterController = monsterController;
    }

    /**
     *
     */
    public void startGame() {
        System.out.println("game started");
        this.playerController.setGameRunning(true);
        // this.world.initialize();
        this.world.initializeEmpty();
        System.out.println("world initialized");
        this.playerController.insertPlayer();
        System.out.println("player inserted");
        this.monsterController.dumpMonsters();
        this.monsterController.createMonsters(1);
        System.out.println("monsters created");
        this.ui.refresh();
        this.ui.message("Use W,A,S&D for movement!");
        this.playerController.setPlayerTurn(true);
    }

    /**
     *
     */
    public void endGame() {
        this.ui.message("Press 'a' for a new game.");
        this.playerController.setGameRunning(false);
    }

    /**
     *
     */
    public void playerActed() {
        System.out.println("playeracted");
        this.ui.refresh();
        this.checkEndgame();
        this.monsterTurn();
    }

    private void monsterTurn() {
        //monsterin liikkuu, liikkeen jälkeen refresh ja // this.checkEndgame();
        this.monsterController.monsterActions();
        this.ui.refresh();
        this.checkEndgame();
        this.playerController.setPlayerTurn(true);
    }

    private boolean checkEndgame() {
        int[][] monsterpositions = this.monsterController.getMonsterPositions();
        for (int i = 0; i < monsterpositions.length; i++) {
            if (Arrays.equals(monsterpositions[i], this.playerController.getPlayerPosition())) {
                System.out.println("endgame");
                this.endGame();
            }
        }
        return false;
    }

    /**
     * Get player's position in array 
     * 
     * @return {x-coordinate,y-coordinate}
     */
    public int[] getPlayerPosition() {
        return this.playerController.getPlayerPosition();
    }

}
