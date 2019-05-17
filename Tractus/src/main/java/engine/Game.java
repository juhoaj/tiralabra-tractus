/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import ui.Interface;

/**
 *
 * @author juhojuutilainen
 */
public class Game {
    
    private Interface ui;
    
    private PlayerAction playerAction;

    public Game() {
        System.out.println("launching terminal");
        
        this.playerAction = new PlayerAction();
        this.ui = new Interface(this.playerAction,32,32);

        startGame();
        playGame();
        endGame();
        
    }
    


    private  void playGame() {

    }
    
    private  void startGame() {
        
    }
    
    private  void endGame() {
    
    }
    
}

