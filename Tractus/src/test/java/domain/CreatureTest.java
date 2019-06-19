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
        this.testCreature.setY(0);
        assertEquals(0, testCreature.getY());
    }
    
    @Test
    public void cannotSetXNegative() {
        try {
            this.testCreature.setX(-1);
            fail("Map has no negative coordinates");
        } catch (IllegalArgumentException e) {
        }
    }
    
        @Test
    public void cannotSetYNegative() {
        try {
            this.testCreature.setY(-1);
            fail("Map has no negative coordinates");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void cannotCreateWithNegativeCoordinates() {
        try {
            Creature creature = new Creature(-1, 1);
            fail("Map has no negative coordinates");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void returnsMinusHundredIfCoordinatesNotSet() {
        Creature positionlessCreaure = new Creature();
        assertEquals(-100, positionlessCreaure.getY());
    }

    @Test
    public void throesErrorIfPositionIncorrect() {
        try {
            int[] newPosition = {1};
            this.testCreature.setPosition(newPosition);
            fail("Array should have only x and y coordinates");
        } catch (IllegalArgumentException e) { }

        try {
            int[] newPosition2 = {1, 2, 3};
            this.testCreature.setPosition(newPosition2);
            fail("Array should have only x and y coordinates");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void getPositionWorks() {
        this.testCreature.setX(10);
        this.testCreature.setY(10);
        assertEquals(10, testCreature.getPosition()[0]);
        assertEquals(10, testCreature.getPosition()[1]);
    }
    
        @Test
    public void setPositionWorks() {
        int[] newPosition = {20,20};
        this.testCreature.setPosition(newPosition);
        assertEquals(20, testCreature.getPosition()[0]);
        assertEquals(20, testCreature.getPosition()[1]);
    }

}
