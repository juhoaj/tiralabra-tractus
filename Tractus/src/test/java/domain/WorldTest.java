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
        oneWorld = new World(1,1);
        oneWorld.initializeEmpty();
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
     public void getTerrainWorks() {
         assertEquals(0, oneWorld.getTerrain(0, 0));
     }
     
     @Test
     public void outOfBoundsReturnsOne() {
         assertEquals(1, oneWorld.getTerrain(0, 1));
         assertEquals(1, oneWorld.getTerrain(1, 0));
         assertEquals(1, oneWorld.getTerrain(0, -1));
         assertEquals(1, oneWorld.getTerrain(-1, 0));
     }
     
     
}
