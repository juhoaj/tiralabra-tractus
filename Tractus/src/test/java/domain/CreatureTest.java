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
public class CreatureTest {

    Creature testCreature;

    
    @Before
    public void setUp() {
        testCreature = new Creature(1, 1);
    }

    @Test
    public void getXWorks() {
        assertEquals(1, testCreature.getX());
    }

    @Test
    public void getYWorks() {
        assertEquals(1, testCreature.getY());
    }
    
    @Test
    public void setXWorks() {
        testCreature.setX(0);
        assertEquals(0, testCreature.getX());
    }
    
    @Test
    public void setYWorks() {
        testCreature.setY(0);
        assertEquals(0, testCreature.getY());
    }
    

}
