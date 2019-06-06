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
    private ArrayList<Creature> monsterlist;
    private GameController gameController;
    private PlayerController playerController;
    private MonsterController monsterController;
    
    private Interface ui;

    /**
     * Game's constructor that parametrizes the game and set's it up. 
     * Most importantly creates monsterList and instances of World, Player, 
     * PlayerController, MonsterController, GameController and Interface.
     */
    public Game() {
        this.gameareaHeight = 200;
        this.gameareaWidth = 200;
        this.viewportHeight = 51;
        this.viewportWidth = 51;
        this.world = new World(gameareaHeight,gameareaWidth);
        this.gameController = new GameController();
        this.player = new Creature();
        this.playerController = new PlayerController(this.player, this.world, this.gameController);
        this.monsterlist = new ArrayList<>();
        this.monsterController = new MonsterController(this.monsterlist, this.world, this.gameController, false);
        System.out.println("launching terminal");
        this.ui = new Interface(this.world, this.playerController, this.monsterController, this.viewportWidth,this.viewportHeight);
        this.gameController.addDependencies(this.world, this.ui, this.playerController, this.monsterController);
        this.gameController.startGame();
    }
       
}

