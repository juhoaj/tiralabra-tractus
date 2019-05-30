
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
 *
 * @author juhojuutilainen
 */
public class GameController
 {
    
            
    private World world;
    private PlayerController playerController;
    private MonsterController monsterController;
    private Interface ui;
    
    public void addDependencies(World world, Interface ui, PlayerController playerController, MonsterController monsterController) {
        System.out.println("dependencies received");
        this.playerController = playerController;
        this.ui = ui;
        this.world=world;
        this.monsterController=monsterController;
    }
    
    public void startGame() {
        System.out.println("game started");
        this.playerController.setGameRunning(true);
        this.world.initialize();
        this.playerController.insertPlayer();
        this.monsterController.dumpMonsters();
        this.monsterController.createMonsters(10);
        this.ui.refresh();
        this.ui.message("Use W,A,S&D for movement!");
        this.playerController.setPlayerTurn(true);
    }
    
    public void playGame() {
        this.playerController.setPlayerTurn(true);        
    }
    
    public void endGame() {
        this.ui.message("Press 'a' for a new game.");
        this.playerController.setGameRunning(false);
    }
    
    public void playerActed() {
        // this.checkEndgame();
        System.out.println("playeracted");
        this.ui.refresh();
        this.checkEndgame();
        this.monsterTurn();
}
    
    private void monsterTurn() {
        //monsterin liikkuu, jokaisen monsterin jälkeen refresh ja // this.checkEndgame();
        
        this.playerController.setPlayerTurn(true);
    }

    private boolean checkEndgame() {
        int[][]monsterpositions = this.monsterController.getMonsterPositions();
        for ( int i = 0 ; i < monsterpositions.length ; i ++ ) {
            if (Arrays.equals(monsterpositions[i], this.playerController.getPlayerPosition())) {
                System.out.println("endgame");
                this.endGame();
            }
        }
        return false;
    }
    


    
}
