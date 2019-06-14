/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Creature;
import domain.World;
import helpers.CustomArrayList;
import helpers.Distance;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author juhojuutilainen
 */
public class MonsterControllerTest {
    private World worldThatReturnsOneForTerrain;
    private World worldThatReturnsTwoForTerrain;
    private MonsterController testMonsterControllerWithWorldOne;
    private MonsterController testMonsterControllerWithWorldTwo;
    private Creature monster;
    private CustomArrayList<Creature> monsterList;
    private GameController gameController;
    private RouteFinder routeFinder;
    private Distance distance;
    
    @Before
    public void setUp() {
        this.monster = mock(Creature.class);
        when(this.monster.getX()).thenReturn(1);
        when(this.monster.getY()).thenReturn(1); 
        this.monsterList = new CustomArrayList<>();
        this.monsterList.add(this.monster);
        
        this.gameController = mock(GameController.class);
        
        this.routeFinder = new RouteFinder(worldThatReturnsOneForTerrain);
        this.distance = new Distance();

        // Creates two instances of World and PlayerController for testing unobstucted and obstucted movement
        
        this.worldThatReturnsOneForTerrain = mock(World.class);
        when (this.worldThatReturnsOneForTerrain.getTerrain(anyInt(), anyInt())).thenReturn(1);
        when (this.worldThatReturnsOneForTerrain.getWidth()).thenReturn(3);
        when (this.worldThatReturnsOneForTerrain.getHeight()).thenReturn(3);        
        this.worldThatReturnsTwoForTerrain = mock(World.class);
        when (this.worldThatReturnsTwoForTerrain.getTerrain(anyInt(), anyInt())).thenReturn(2);
        when (this.worldThatReturnsTwoForTerrain.getWidth()).thenReturn(3);
        when (this.worldThatReturnsTwoForTerrain.getHeight()).thenReturn(3);  
      
        
        this.testMonsterControllerWithWorldOne = new MonsterController(this.monsterList, this.worldThatReturnsOneForTerrain, this.gameController, this.routeFinder, this.distance);
        this.testMonsterControllerWithWorldOne = new MonsterController(this.monsterList, this.worldThatReturnsTwoForTerrain, this.gameController, this.routeFinder, this.distance);

    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void classInitialized() {
        assertNotNull(this.testMonsterControllerWithWorldOne);
    }
    
    @Test
    public void moveToCallsMonster() {
        this.testMonsterControllerWithWorldOne.moveTo(1,1,this.monster);
        verify(this.monster).setX(1);
        verify(this.monster).setY(1);
    }
    
    @Test 
    public void getMonsterPositionsReturnsNullIfEmpty() {
        CustomArrayList<Creature> testMonsterList = new CustomArrayList<>();
        MonsterController testMonsterController = new MonsterController(testMonsterList, mock(World.class), mock(GameController.class), mock(RouteFinder.class), mock(Distance.class));
        assertNull(testMonsterController.getMonsterPositions());
    }
    
    @Test
    public void monstersGetCreated() {
        
        World testWorld = mock(World.class);
        when (testWorld.getWidth()).thenReturn(30);
        when (testWorld.getHeight()).thenReturn(30); 
        Creature testCreature = new Creature();
        CustomArrayList<Creature> testMonsterList = new CustomArrayList<>();
        testMonsterList.add(testCreature);
        int[] playerPosition = {15,15};
        GameController testGameController = mock(GameController.class);
        when (testGameController.getPlayerPosition()).thenReturn(playerPosition);
        Distance testDistance = mock(Distance.class);
        when (testDistance.getDistance(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(15);
        MonsterController testMonsterController = new MonsterController(testMonsterList, this.worldThatReturnsOneForTerrain, testGameController, mock(RouteFinder.class), testDistance);
        
        testMonsterController.createMonsters(1);
        int positions[][] = this.testMonsterControllerWithWorldOne.getMonsterPositions();
        assertEquals(1, positions.length);
    }
    

}
