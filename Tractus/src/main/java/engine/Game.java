/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import ui.Interface;
import domain.World;
import domain.Creature;
import java.util.ArrayList;

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
    private ArrayList<Creature> monsters;
    private GameController gameController;
    
    private Interface ui;
    
    private PlayerController playerController;

    public Game() {
        this.gameareaHeight = 100;
        this.gameareaWidth = 100;
        this.viewportHeight = 33;
        this.viewportWidth = 33;
        this.world = new World(gameareaHeight,gameareaWidth);
        this.gameController = new GameController();
        this.player = new Creature();
        this.playerController = new PlayerController(this.player, this.world, this.gameController);
        System.out.println("launching terminal");
        this.ui = new Interface(this.world, this.player, this.playerController, this.viewportWidth,this.viewportHeight);
        this.gameController.addDependencies(this.world, this.ui, this.playerController);
        this.gameController.startGame();
    }
       
}

