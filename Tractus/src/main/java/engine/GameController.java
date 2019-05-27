
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
    private Interface ui;
    
    public void addDependencies(World world, Interface ui, PlayerController playerController) {
        System.out.println("dependencies received");
        this.playerController = playerController;
        this.ui = ui;
        this.world=world;
    }
    
    public void startGame() {
        System.out.println("game started");
        this.world.initialize();
        this.insertPlayer();
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
    
    private void insertPlayer() {
        int startPositionX = this.world.getWidth() / 2;
        int startPositionY = this.world.getHeight() / 2;
        System.out.println( startPositionX + "," + startPositionY + "-" + this.world.getTerrain(startPositionX, startPositionY));
        while (true) {
            if (this.world.getTerrain(startPositionX, startPositionY) == 0) {
                this.playerController.moveTo(startPositionX, startPositionY);
                break;
            } else {
                startPositionX--;
            }
        }        
    }

    
}
