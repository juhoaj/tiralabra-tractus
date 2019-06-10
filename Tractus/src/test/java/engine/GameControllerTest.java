/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.World;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ui.Interface;

/**
 *
 * @author juhojuutilainen
 */
public class GameControllerTest {
    private GameController testGameController;
    private World world;
    private PlayerController playerController;
    private MonsterController monsterController;
    private Interface ui;   

    @Before
    public void setUp() {
        this.testGameController = new GameController();
        this.world = mock(World.class);
        this.playerController = mock(PlayerController.class);
        this.monsterController = mock(MonsterController.class);
        this.ui = mock(Interface.class);
        this.testGameController.addDependencies(world, ui, playerController, monsterController);
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void startGameWorks() {
        this.testGameController.startGame();
        verify(this.playerController).setGameRunning(true);
        verify(this.world).initializeCaves();
        verify(this.playerController, atLeast(1)).insertPlayer();
        verify(this.monsterController).dumpMonsters();
        verify(this.monsterController).createMonsters(anyInt());
        verify(this.ui).refresh();
        verify(this.ui).message(anyString());
        verify(this.playerController).setPlayerTurn(true);
        assertNotNull(this.testGameController);
    }
    
    @Test
    public void insertPlayerGetsCalledOnlyOnceIfTrue() {
        when(this.playerController.insertPlayer()).thenReturn(true);
        this.testGameController.startGame();
        verify(this.playerController).setPlayerTurn(true);
        
    }
    
    @Test
    public void checkMonsterTurnAndMosterActionsWork() {
        // set returns for methods that check monsters' and player's position
        int[] returnedPlayerPosition = {1,1};
        int[][] returnedMonsterPosition = {{1,2}};
        when(this.monsterController.getMonsterPositions()).thenReturn(returnedMonsterPosition);
        when(this.playerController.getPlayerPosition()).thenReturn(returnedPlayerPosition);
        
        this.testGameController.playerActed();
        
        verify(this.monsterController, atLeast(1)).monsterActions();
        verify(this.ui, atLeast(1)).refresh();
        verify(this.playerController).setPlayerTurn(true);
        
    }
    @Test
    public void checkEndgameWorks() {
        // set returns for methods that check monsters' and player's position
        int[] returnedPlayerPosition = {1,1};
        int[][] returnedMonsterPosition = {{1,1}};
        when(this.monsterController.getMonsterPositions()).thenReturn(returnedMonsterPosition);
        when(this.playerController.getPlayerPosition()).thenReturn(returnedPlayerPosition);

        // monsterTurn
        this.testGameController.playerActed();
        
        // checkEndgame()
        verify(this.monsterController, atLeast(1)).getMonsterPositions();  
        verify(this.playerController, atLeast(1)).getPlayerPosition(); 
        
        // endGame()
        verify(this.ui, atLeast(1)).message("Press 'a' for a new game."); 
        verify(this.playerController, atLeast(1)).setGameRunning(false); 
        
    }
    
    @Test
    public void wontStartWithoutDependencies() {
        GameController gameController = new GameController();
        try {
            gameController.startGame();
            fail("Game not initialized with addDependencies");
        } catch (IllegalArgumentException e) { }
        
    }
    
    @Test
    public void drawCharacterWorks() {
        when(this.world.getWidth()).thenReturn(1);
        when(this.world.getHeight()).thenReturn(1);
        
        try {
            this.testGameController.drawCharacter('x',-1,0);
            fail("Coordinate outside map.");
        } catch (IllegalArgumentException e) { }
        
        try {
            this.testGameController.drawCharacter('x',1,0);
            fail("Coordinate outside map.");
        } catch (IllegalArgumentException e) { }
        
        try {
            this.testGameController.drawCharacter('x',0,-1);
            fail("Coordinate outside map.");
        } catch (IllegalArgumentException e) { }
        
        try {
            this.testGameController.drawCharacter('x',0,1);
            fail("Coordinate outside map.");
        } catch (IllegalArgumentException e) { }
        
        this.testGameController.drawCharacter('x',0,0);
        verify(this.ui).drawCharacter('x',0,0);
    }
}
