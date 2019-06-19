/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhojuutilainen
 */
public class TileTest {
    
    private Tile testTile;
    

    @Before
    public void setUp() {
        this.testTile = new Tile(1,1);
    }

    @Test
    public void TileInitialized() {
        
        assertNotNull(testTile);
    }
    
    @Test
    public void cannotInitializeWithNegativeValue() {
        try {
            Tile tile = new Tile(1,-1);
            fail("Map has no negative coordinates");
        } catch (IllegalArgumentException e) {}
    }
    
    @Test
    public void getYWorks() {
        assertEquals(1, this.testTile.getY());
    }

    @Test
    public void setXWorks() {
        assertEquals(1, this.testTile.getX());
    }
}
