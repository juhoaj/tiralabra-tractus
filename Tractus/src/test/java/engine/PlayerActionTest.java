/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Command;
import domain.Creature;
import domain.World;

/**
 *
 * @author juhojuutilainen
 */
public class PlayerActionTest {
    
    World world;
    Creature creature;
    PlayerAction playerAction;
    
    public PlayerActionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        world = new World(3,3);
        world.initializeEmpty();
        creature = new Creature(1,1);
        playerAction = new PlayerAction(creature, world);
    }
    
    @After
    public void tearDown() {
        creature.setX(1);
        creature.setY(1);
    }

    @Test
    public void canMoveNorth() {
        playerAction.setPlayerTurn(true);
        playerAction.setAction(Command.NORTH);
        assertEquals(0, creature.getY());
    }
    
    @Test
    public void canMoveEast() {
        playerAction.setPlayerTurn(true);
        playerAction.setAction(Command.EAST);
        assertEquals(2, creature.getX());
    }
    
    @Test
    public void canMoveSouth() {
        playerAction.setPlayerTurn(true);
        playerAction.setAction(Command.SOUTH);
        assertEquals(2, creature.getY());
    }
    
    @Test
    public void canMoveWest() {
        playerAction.setPlayerTurn(true);
        playerAction.setAction(Command.WEST);
        assertEquals(0, creature.getX());
    }
    
    @Test
    public void cantMoveThroughWalls() {
        playerAction.setPlayerTurn(true);
        playerAction.setAction(Command.NORTH);
        playerAction.setAction(Command.NORTH);
        assertEquals(0, creature.getY());
    }
    
}
