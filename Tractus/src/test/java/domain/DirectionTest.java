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
public class DirectionTest {
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testNorth() {
         Command north = Command.NORTH;
         assertEquals(north.valueOf("NORTH"), north);
    }
     @Test
     public void testEast() {
         Command east = Command.EAST;
         assertEquals(east.valueOf("EAST"), east);
    }
     @Test
     public void testSouth() {
         Command south = Command.SOUTH;
         assertEquals(south.valueOf("SOUTH"), south);
    }
     @Test
     public void testwest() {
         Command west = Command.WEST;
         assertEquals(west.valueOf("WEST"), west);
    }
}
