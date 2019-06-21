/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class WorldTest {

    World oneWorld;
    World nineWorld;
    World fullOneWorld;


    @Before
    public void setUp() {
        this.oneWorld = new World(1, 1, false);
        this.oneWorld.initializeEmpty();
        this.nineWorld = new World(3, 3, false);
        this.nineWorld.initializeEmpty();
        this.fullOneWorld = new World(1, 1, false);
        this.fullOneWorld.initializeFull();
        
    }
    
    @Test
    public void worldInitialized() {
        assertNotNull(this.oneWorld);
    }

    @Test
    public void cannotInitializeWithNegativeCoordinates() {
        try {
            World world = new World(-1, 1, false);
            fail("Map size has to be positive.");
        } catch (IllegalArgumentException e) { }
    }
    
    @Test 
        public void initializedMapHasTerrain() {
        World world = new World(100, 100, false);
        world.initializeCaves();
        assertNotNull(oneWorld.getTerrain(0, 0));
    }
    
    
    @Test
    public void getTerrainWorks() {
        assertEquals(1, oneWorld.getTerrain(0, 0));
        assertEquals(2, fullOneWorld.getTerrain(0, 0));
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
    
    @Test
    public void setTerrainReturnsFalse() {
        assertEquals(false,this.oneWorld.setTerrain(-1,1,1));
        assertEquals(false,this.oneWorld.setTerrain(1,-1,1));
        assertEquals(false,this.oneWorld.setTerrain(1,1,0));
        assertEquals(false,this.oneWorld.setTerrain(1,1,1));
        assertEquals(false,this.oneWorld.setTerrain(1,1,1));
        assertEquals(false,this.oneWorld.setTerrain(1,1,3));
        
    }
    
    @Test
    public void setTerrainReturnsTrue() {
        assertEquals(true,this.oneWorld.setTerrain(0,0,1));
        assertEquals(true,this.oneWorld.setTerrain(0,0,2));

        
    }
    
    @Test
    public void getConnectedReturnsFalsOutOfMap() {
        assertEquals(false,this.oneWorld.getConnected(-1,1));
        assertEquals(false,this.oneWorld.getConnected(1,-1));
        assertEquals(false,this.oneWorld.getConnected(3,1));
        assertEquals(false,this.oneWorld.getConnected(1,3));
    }
    

}
