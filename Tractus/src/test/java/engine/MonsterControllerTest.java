/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Creature;
import domain.World;
import helpers.Distance;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
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
    private ArrayList<Creature> monsterList;
    private GameController gameController;
    private RouteFinder routeFinder;
    private Distance distance;
    
    @Before
    public void setUp() {
        this.monster = mock(Creature.class);
        when(this.monster.getX()).thenReturn(1);
        when(this.monster.getY()).thenReturn(1); 
        this.monsterList = new ArrayList<>();
        this.monsterList.add(this.monster);
        
        this.gameController = mock(GameController.class);
        
        this.routeFinder = new RouteFinder(worldThatReturnsOneForTerrain, monsterList);
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
    
}
