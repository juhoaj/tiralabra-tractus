/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.World;
import helpers.Distance;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author juhojuutilainen
 */
public class RouteFinderTest {
    private RouteFinder routeFinder;
    private World world;
    private Distance distance;
    private GameController gameController;
    

    @Before
    public void setUp() {
        
        this.world = mock(World.class);
        when(this.world.getTerrain(anyInt(), anyInt())).thenReturn(1);
        when(this.world.getWidth()).thenReturn(4);
        when(this.world.getHeight()).thenReturn(4);
        
        /*
        int[] zeroZero = {0,0};
        int[] zeroOne = {0,1};
        int[] zeroTwo = {0,2};
        int[] oneZero = {1,0};
        int[] oneOne = {1,1};
        int[] oneTwo = {1,2};
        
        CustomArrayList<int[]>  returnZeroTwo = new CustomArrayList<>();
        CustomArrayList<int[]>  returnZeroOne = new CustomArrayList<>();
        CustomArrayList<int[]> returnZeroZero = new CustomArrayList<>();
        CustomArrayList<int[]> returnOneTwo = new CustomArrayList<>();
        CustomArrayList<int[]>  returnOneOne = new CustomArrayList<>();
        CustomArrayList<int[]>  returnOneZero = new CustomArrayList<>();
        
        returnZeroTwo.add(oneTwo);
        returnZeroTwo.add(zeroOne);
        returnZeroOne.add(zeroZero);
        returnZeroOne.add(oneOne);
        returnZeroOne.add(zeroTwo);
        returnZeroZero.add(zeroOne);
        returnZeroZero.add(oneZero);
        returnOneTwo.add(zeroTwo);
        returnOneTwo.add(oneOne);
        returnOneOne.add(zeroOne);
        returnOneOne.add(oneZero);
        returnOneOne.add(oneTwo);
        returnOneZero.add(zeroZero);
        returnOneZero.add(oneOne);
        
        when(this.world.getNeighborPositions(zeroZero)).thenReturn(returnZeroZero);
        when(this.world.getNeighborPositions(zeroOne)).thenReturn(returnZeroOne);
        when(this.world.getNeighborPositions(zeroTwo)).thenReturn(returnZeroTwo);
        when(this.world.getNeighborPositions(oneZero)).thenReturn(returnOneZero);
        when(this.world.getNeighborPositions(oneOne)).thenReturn(returnOneOne);
        when(this.world.getNeighborPositions(oneTwo)).thenReturn(returnOneTwo);
        */
        
        this.world = new World(3, 3, false);
        this.world.initializeEmpty();
        
        this.distance = mock(Distance.class);
        this.gameController = mock(GameController.class);
        this.routeFinder = new RouteFinder(world, this.gameController, true, true);
    }
    
    @Test
    public void objectInitialized() {
        RouteFinder testRouteFinder = new RouteFinder(this.world);
        assertNotNull(testRouteFinder);
    }
    
    @Test
    public void getNextMoveReturnsNullOutOfBounds() {
        int[] ok = {1,1};
        int[] xSmall = {-1,1};
        int[] ySmall = {1,-1};
        int[] xBig = {4,1};
        int[] yBig = {1,4};
        assertNull(this.routeFinder.getNextMove(xSmall, ok));
        assertNull(this.routeFinder.getNextMove(ySmall, ok));
        assertNull(this.routeFinder.getNextMove(xBig, ok));
        assertNull(this.routeFinder.getNextMove(yBig, ok));
        assertNull(this.routeFinder.getNextMove(ok, xSmall));
        assertNull(this.routeFinder.getNextMove(ok, ySmall));
        assertNull(this.routeFinder.getNextMove(ok, xBig));
        assertNull(this.routeFinder.getNextMove(ok, yBig));
    }
    
    @Test
    public void smartMoveGetsReturned() {
        int[] start = {0,0};
        int[] end = {2,0};
        int[] returnedValue = this.routeFinder.getNextMove(start, end);
        assertEquals(1, returnedValue[0]);
        assertEquals(0, returnedValue[1]);
    }
    
    @Test
    public void lastMoveGetsReturned() {
        int[] start = {0,0};
        int[] end = {0,0};
        int[] returnedValue = this.routeFinder.getNextMove(start, end);
        assertEquals(0, returnedValue[0]);
        assertEquals(0, returnedValue[1]);
    }
    
    
}