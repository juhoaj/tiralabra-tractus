/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class DistanceTest {
    Distance distance;
   
    
    @Before
    public void setUp() {
        this.distance = new Distance();
    }
    
    @Test
    public void getDistanceWorks() {
        assertEquals(11, this.distance.getDistance(0, 0, 10, 5));
    }
}
