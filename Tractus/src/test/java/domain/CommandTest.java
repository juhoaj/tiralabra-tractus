/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;
import static org.junit.Assert.*;



public class CommandTest {
    


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
