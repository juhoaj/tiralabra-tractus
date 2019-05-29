
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Creature;
import domain.World;
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
        this.world.initialize();
        this.playerController.insertPlayer();
        this.monsterController.createMonsters(10);
        this.ui.refresh();
        this.playerController.setPlayerTurn(true);
    }
    
    public void playGame() {
        this.playerController.setPlayerTurn(true);        
    }
    
    public void playerActed() {
        // this.checkEndgame();
        System.out.println("playeracted");
        this.ui.refresh();
        this.monsterTurn();
}
    
    private void monsterTurn() {
        //monsterin liikkuu, jokaisen monsterin jälkeen refresh ja // this.checkEndgame();
        
        this.playerController.setPlayerTurn(true);
    }

    private void checkEndgame() {
        
    }
    


    
}
