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
 * <h1>Game</h1>
 * Responsible for setting up, running and ending game.
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
        this.gameareaHeight = 100;
        this.gameareaWidth = 100;
        this.viewportHeight = 33;
        this.viewportWidth = 33;
        this.world = new World(gameareaHeight,gameareaWidth);
        this.world.initialize();
        this.generatePlayer();
        
        System.out.println("launching terminal");
        this.playerAction = new PlayerAction(this.player, this.world);
        this.ui = new Interface(this.world, this.player, this.playerAction,this.viewportWidth,this.viewportHeight);

        
        while(true) {
            this.playerAction.setPlayerTurn(true);
            this.ui.refresh();
            this.playerAction.setPlayerTurn(false);
        }
        /*
        startGame();
        playGame();
        endGame();
        */
        
    }
    
    private void generatePlayer() {
        int startPositionX = gameareaWidth / 2;
        int startPositionY = gameareaHeight / 2;
        System.out.println( startPositionX + "," + startPositionY + "-" + this.world.getTerrain(startPositionX, startPositionY));
        while (true) {
            if (this.world.getTerrain(startPositionX, startPositionY) == 0) {
                this.player = new Creature(startPositionX,startPositionY);
                break;
            } else {
                startPositionX--;
            }
        }        
    }

    private  void playGame() {
        System.out.println("playgame");
    }
    
    private  void startGame() {
        
    }
    
    private  void endGame() {
    
    }

    
    
}

