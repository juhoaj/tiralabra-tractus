/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import helpers.CustomArrayList;
import java.util.ArrayList;
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
public class CaveTest {
    Tile tile;
    Cave testCaveOne;
    Cave testCaveTwo;
    private Cave testCaveNull;
    
    @Before
    public void setUp() {
        CustomArrayList<Tile> twoTiles = new CustomArrayList<>();
        twoTiles.add(mock(Tile.class));
        twoTiles.add(mock(Tile.class));
        CustomArrayList<Tile> oneTile = new CustomArrayList<>();
        oneTile.add(mock(Tile.class));
        this.testCaveOne = new Cave(oneTile);
        this.testCaveTwo = new Cave(twoTiles);
    }

    @Test
    public void caveInitialized() {
        assertNotNull(this.testCaveOne);
    }
    
    
    
    @Test
    public void getSizeWorks() {
        assertEquals(1, this.testCaveOne.getSize());
    }
    

    
    @Test
    public void getTilesWorks() {
        CustomArrayList<Tile> testArrayList = testCaveTwo.getTiles();
        assertEquals(2, testArrayList.size());
    }
    
    @Test
    public void comparatorWorks() {
    
    }
}
