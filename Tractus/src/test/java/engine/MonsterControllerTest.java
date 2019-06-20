/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Command;
import domain.Creature;
import domain.World;
import helpers.CustomArrayList;
import helpers.Distance;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void classInitializedConstructor1() {
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
    
    @Test
    public void monsterGetsInserted() {
        this.testMonsterControllerWithWorldOne.instertMonster(1, 1);
        Creature monster = this.monsterList.get(0);
        assertEquals(1, monster.getX());
    }

    @Test
    public void monsterActionsSetsMonsterposition() {
        World realWorld = new World(3, 3, false);
        realWorld.initializeEmpty();
        Creature testCreature = new Creature();
        int[] monsterPosition = {2,2};
        testCreature.setPosition(monsterPosition);
        CustomArrayList<Creature> testMonsterList = new CustomArrayList<>();
        testMonsterList.add(testCreature);
        int[] playerPosition = {15,15};
        GameController testGameController = mock(GameController.class);
        when (testGameController.getPlayerPosition()).thenReturn(playerPosition);
        Distance testDistance = mock(Distance.class);
        RouteFinder testRoutefinder = mock(RouteFinder.class);
        when (testRoutefinder.getNextMove(monsterPosition, playerPosition)).thenReturn(playerPosition);
        when (testDistance.getDistance(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(15);
        MonsterController testMonsterController = new MonsterController(testMonsterList, this.worldThatReturnsOneForTerrain, testGameController, testRoutefinder, testDistance);
        assertEquals(2, testCreature.getPosition()[0]);
        testMonsterController.monsterActions();
        assertEquals(15, testCreature.getPosition()[0]);
    }
    
    @Test
    public void monsterActionsCallsWorld() {
        RouteFinder finder = mock(RouteFinder.class);
        MonsterController monsterController= new MonsterController(this.monsterList, this.worldThatReturnsTwoForTerrain, this.gameController, finder, this.distance);
        int[] returnPosition = {1,1};
        when(this.monster.getPosition()).thenReturn(returnPosition); 
        when(this.gameController.getPlayerPosition()).thenReturn(returnPosition);
        when(finder.getNextMove(returnPosition, returnPosition)).thenReturn(returnPosition);

        monsterController.monsterActions();
        verify(this.worldThatReturnsTwoForTerrain).setTerrain(1, 1, 1);
    }
    


    
    
}
