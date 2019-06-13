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
    private World world;
    private Creature player;
    private ArrayList<Creature> monsterlist;
    private GameController gameController;
    private PlayerController playerController;
    private MonsterController monsterController;
    
    private Interface ui;

    /**
     * Game's constructor that first parametrizes the game then and set's it up. 
     * Most importantly this creates monsterList and instances of World, Player, 
     * PlayerController, MonsterController, GameController and Interface.
     * 
     */
    public Game() {
        
        boolean testPerformance = true; // set true to print algorithm performance to console
        boolean debugging = false; // set true to print debugging info to terminal
        int gameareaHeight = 500;
        int gameareaWidth = 500;
        int monstersAtStart = 50;
        int monsterSearchRadius = 1000;
        int viewportHeight = 51;
        int viewportWidth = 51;
        
        this.world = new World(gameareaHeight,gameareaWidth, testPerformance);
        this.gameController = new GameController();
        this.player = new Creature();
        this.playerController = new PlayerController(this.player, this.world, this.gameController);
        this.monsterlist = new ArrayList<>();
        this.monsterController = new MonsterController(this.monsterlist, this.world, this.gameController, monsterSearchRadius, debugging, testPerformance);
        this.ui = new Interface(this.world, this.playerController, this.monsterController, viewportWidth, viewportHeight, debugging);
        this.gameController.addDependencies(this.world, this.ui, this.playerController, this.monsterController, monstersAtStart, debugging, testPerformance);
        this.gameController.startGame();
    }    
}

