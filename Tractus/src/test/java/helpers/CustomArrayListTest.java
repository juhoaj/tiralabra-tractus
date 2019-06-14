/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import domain.Creature;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author juhojuutilainen
 */
public class CustomArrayListTest {
    private CustomArrayList intArrayList;
    
    
    
    @Before
    public void setUp() {
        this.intArrayList = new CustomArrayList<Integer>();
    }

    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //.ö-
    @Test
    public void classInitialized() {
        assertNotNull(this.intArrayList);
    }
    
    @Test
    public void addIntGetInt() {
        this.intArrayList.add(1);
        assertEquals(1, this.intArrayList.get(0));
    }

    @Test
    public void addCreatureGetCreature() {
        Creature testCreature = mock(Creature.class);
        CustomArrayList<Creature> testArrayList = new CustomArrayList<>();
        testArrayList.add(testCreature);
        assertEquals(testCreature, testArrayList.get(0));
    }  
    @Test
    public void outOfBoundsIsOutOfBonds() {
        this.intArrayList.add(1);
        try {
            this.intArrayList.get(1);
            fail("Out of bounds.");
        } catch (IllegalArgumentException e) { }
        try {
            this.intArrayList.get(-1);
            fail("Out of bounds.");
        } catch (IllegalArgumentException e) { }
    }
    
    @Test
    public void addSecondAndGetLast() {
        this.intArrayList.add(1);
        this.intArrayList.add(2);
        assertEquals(2, this.intArrayList.get(this.intArrayList.size()-1));
    }
    
    @Test
    public void getSizeWorks() {
        this.intArrayList.add(1);
        this.intArrayList.add(1);
        this.intArrayList.add(1);
        this.intArrayList.add(1);
        this.intArrayList.add(1);
        this.intArrayList.add(1); 
        assertEquals(6, this.intArrayList.size());
    }
    
    @Test
    public void lenghtenWorks() {
        CustomArrayList testArrayList = new CustomArrayList(2);
        testArrayList.add(1);
        testArrayList.add(2);
        testArrayList.add(3);
        testArrayList.add(4);
        testArrayList.add(5);
        testArrayList.add(6); 
        assertEquals(6, testArrayList.get(testArrayList.size()-1));
    }
    
    @Test
    public void returnsEmpty() {
        assertEquals(true, this.intArrayList.isEmpty());
    }
    
}


