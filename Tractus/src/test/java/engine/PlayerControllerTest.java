/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Command;
import domain.Creature;
import domain.World;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author juhojuutilainen
 */
public class PlayerControllerTest {
    private World worldThatReturnsOneForTerrain;
    private World worldThatReturnsTwoForTerrain;
    private PlayerController testPlayerControllerWithWorldOne;
    private PlayerController testPlayerControllerWithWorldTwo;
    private Creature player;
    private GameController gameController;

    
    
    @Before
    public void setUp() {

        this.player = mock(Creature.class);
        when(this.player.getX()).thenReturn(1);
        when(this.player.getY()).thenReturn(1);   
        
        this.gameController = mock(GameController.class);

        // Creates two instances of World and PlayerController for testing unobstucted and obstucted movement
        
        this.worldThatReturnsOneForTerrain = mock(World.class);
        when (this.worldThatReturnsOneForTerrain.getTerrain(anyInt(), anyInt())).thenReturn(1);
        when (this.worldThatReturnsOneForTerrain.getWidth()).thenReturn(3);
        when (this.worldThatReturnsOneForTerrain.getHeight()).thenReturn(3);        
        this.worldThatReturnsTwoForTerrain = mock(World.class);
        when (this.worldThatReturnsTwoForTerrain.getTerrain(anyInt(), anyInt())).thenReturn(2);
        when (this.worldThatReturnsTwoForTerrain.getWidth()).thenReturn(3);
        when (this.worldThatReturnsTwoForTerrain.getHeight()).thenReturn(3);        
        this.testPlayerControllerWithWorldOne = new PlayerController(this.player, this.worldThatReturnsOneForTerrain, this.gameController);
        this.testPlayerControllerWithWorldTwo = new PlayerController(this.player, this.worldThatReturnsTwoForTerrain, this.gameController);
        this.testPlayerControllerWithWorldOne.setGameRunning(true);
        this.testPlayerControllerWithWorldOne.setPlayerTurn(true);


    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void classInitialized() {
        assertNotNull(this.testPlayerControllerWithWorldOne);
    }


    @Test
    public void checkGetGameRunning() {
        assertEquals(true, this.testPlayerControllerWithWorldOne.getGameRunning());
    }
    
    @Test
    public void checkPlayerTurn() {
        assertEquals(true, this.testPlayerControllerWithWorldOne.getPlayerTurn());
    }
    
    @Test
    public void setActionStartsGame() {
        this.testPlayerControllerWithWorldOne.setGameRunning(false);
        this.testPlayerControllerWithWorldOne.setAction(Command.WEST);
        verify(this.gameController).startGame();
    }
    
    @Test
    public void setActionDoesNotStartwithWrongCommand() {
        this.testPlayerControllerWithWorldOne.setGameRunning(false);
        this.testPlayerControllerWithWorldOne.setAction(Command.NORTH);
        verify(this.gameController, never()).startGame();
    }
    
    @Test
    public void startGameNotCalledIfGameRunning() {
        this.testPlayerControllerWithWorldOne.setAction(Command.WEST);
        verify(this.gameController, never()).startGame();

    }
   
    @Test
    public void setActionMoveNORTHCommandsWorkIfNoObstacle() {
     
        this.testPlayerControllerWithWorldOne.setAction(Command.NORTH);
        verify(this.player).setX(1);
        verify(this.player).setY(0);
    }
    
    @Test
    public void setActionMoveEASTCommandsWorkIfNoObstacle() {
        
        this.testPlayerControllerWithWorldOne.setAction(Command.EAST);
        verify(this.player).setX(2);
        verify(this.player).setY(1);
    }
    
    @Test
    public void setActionMoveSOUTHCommandsWorkIfNoObstacle() {
        this.testPlayerControllerWithWorldOne.setAction(Command.SOUTH);
        verify(this.player).setX(1);
        verify(this.player).setY(2);
    }
    
    @Test
    public void setActionMoveWESTCommandsWorkIfNoObstacle() {
        this.testPlayerControllerWithWorldOne.setAction(Command.WEST);
        verify(this.player).setX(0);
        verify(this.player).setY(1);
    }
    
    @Test
    public void setActionMoveDoesntWorkIfObstacle() {
        this.testPlayerControllerWithWorldTwo.setAction(Command.WEST);
        this.testPlayerControllerWithWorldTwo.setAction(Command.SOUTH);
        this.testPlayerControllerWithWorldTwo.setAction(Command.NORTH);
        this.testPlayerControllerWithWorldTwo.setAction(Command.EAST);
        verify(this.player, never()).setX(anyInt());
    }
    
    
    
    @Test
    public void moveToDoesNotMoveOutsideMap() {
        try {
            this.testPlayerControllerWithWorldOne.moveTo(-1,0);
            fail("Cannot move player outside map.");
        } catch (IllegalArgumentException e) { }
        try {
            this.testPlayerControllerWithWorldOne.moveTo(0,-1);
            fail("Cannot move player outside map.");
        } catch (IllegalArgumentException e) { }
        try {
            this.testPlayerControllerWithWorldOne.moveTo(3,0);
            fail("Cannot move player outside map.");
        } catch (IllegalArgumentException e) { }
        try {
            this.testPlayerControllerWithWorldOne.moveTo(0,3);
            fail("Cannot move player outside map.");
        } catch (IllegalArgumentException e) { }
        
    }
    
    @Test
    public void getPlayerPositionWorks() {
        int[] returnedPosition = {1,1};
        when (this.player.getPosition()).thenReturn(returnedPosition);
        assertEquals(1, this.testPlayerControllerWithWorldOne.getPlayerPosition()[0]);
        assertEquals(1, this.testPlayerControllerWithWorldOne.getPlayerPosition()[1]);
    }
    
    @Test 
    public void playerGetsInsertedIfTerrainEmptyAndConnected() {
        when (this.worldThatReturnsOneForTerrain.getConnected(anyInt(), anyInt())).thenReturn(true);
        assertEquals(true, this.testPlayerControllerWithWorldOne.insertPlayer());
    }
    
    @Test 
    public void playerDoesNotGetInsertedIfTerrainEmptyButNotConnected() {
        when (this.worldThatReturnsOneForTerrain.getConnected(anyInt(), anyInt())).thenReturn(true);
        assertEquals(true, this.testPlayerControllerWithWorldOne.insertPlayer());
    }
    
    
    @Test 
    public void playerDoesNotGetInsertedIfTerrainNotEmpty() {
        assertEquals(false, this.testPlayerControllerWithWorldTwo.insertPlayer());
    }
    
    
}
