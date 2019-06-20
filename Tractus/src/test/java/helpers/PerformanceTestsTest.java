/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import helpers.PerformanceTests;
import org.junit.Test;
import static org.junit.Assert.*;

public class PerformanceTestsTest {
    
    
    @Test
    public void testThatTestsWork() {
        PerformanceTests test = new PerformanceTests();
        assertTrue(test.runTests());
    }
    
}
