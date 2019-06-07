/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhojuutilainen
 */
public class WorldTest {

    World oneWorld;
    World nineWorld;

    public WorldTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.oneWorld = new World(1, 1);
        this.oneWorld.initializeEmpty();
        this.nineWorld = new World(3, 3);
        this.nineWorld.initializeEmpty();
        
    }
    
    @Test
    public void worldInitialized() {
        assertNotNull(this.oneWorld);
    }

    @Test
    public void cannotInitializeWithNegativeCoordinates() {
        try {
            World world = new World(-1, 1);
            fail("Map size has to be positive.");
        } catch (IllegalArgumentException e) { }
    }
    
    @Test 
        public void initializedMapHasTerrain() {
        World world = new World(100, 100);
        world.initializeCaves();
        assertNotNull(oneWorld.getTerrain(0, 0));
    }
    
    
    @Test
    public void getTerrainWorks() {
        assertEquals(1, oneWorld.getTerrain(0, 0));
    }

    @Test
    public void setTerrainWorks() {
        oneWorld.setTerrain(0, 0, 2);
        assertEquals(2, oneWorld.getTerrain(0, 0));
    }

    @Test
    public void outOfBoundsReturnsTwo() {
        assertEquals(2, oneWorld.getTerrain(0, 1));
        assertEquals(2, oneWorld.getTerrain(1, 0));
        assertEquals(2, oneWorld.getTerrain(0, -1));
        assertEquals(2, oneWorld.getTerrain(-1, 0));
    }

    @Test
    public void noNeighborsReturnedIfNoneExist() {
        int[] position = {0, 0};
        assertEquals(0, oneWorld.getNeighborPositions(position).size());
    }

    @Test
    public void neighborsReturned() {
        int[] position = {1, 1};
        assertEquals(4, nineWorld.getNeighborPositions(position).size());
    }
    

}
