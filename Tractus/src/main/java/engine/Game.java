/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import ui.Interface;
import domain.World;
import domain.Creature;

/**
 *
 * @author juhojuutilainen
 */
public class Game {
    
    private int gameareaWidth;
    private int gameareaHeight;
    private int viewportWidth;
    private int viewportHeight;
    private World world;
    private Creature player;
    
    private Interface ui;
    
    private PlayerAction playerAction;

    public Game() {
        this.gameareaHeight = 9;
        this.gameareaWidth = 9;
        this.viewportHeight = 33;
        this.viewportWidth = 33;
        this.world = new World(gameareaHeight,gameareaWidth);
        this.player = new Creature(4,4);
        
        System.out.println("launching terminal");
        this.playerAction = new PlayerAction(this.player, this.world);
        this.ui = new Interface(this.world, this.player, this.playerAction,this.viewportWidth,this.viewportHeight);

        this.world.initialize();
        while(true) {
            // this.playerAction.getActionHappened();
            this.ui.refresh();
        }
        /*
        startGame();
        playGame();
        endGame();
        */
        
    }
    


    private  void playGame() {
        System.out.println("playgame");
        
    }
    
    private  void startGame() {
        
    }
    
    private  void endGame() {
    
    }

    
    
}

