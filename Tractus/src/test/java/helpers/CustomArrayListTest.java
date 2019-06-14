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
    private CustomArrayList arrayList;
    
    
    
    @Before
    public void setUp() {
        this.arrayList = new CustomArrayList();
    }

    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //.ö-
    @Test
    public void classInitialized() {
        assertNotNull(this.arrayList);
    }
    
    @Test
    public void addIntGetInt() {
        this.arrayList.add(1);
        assertEquals(1, this.arrayList.get(0));
    }

    @Test
    public void addCreatureGetCreature() {
        Creature testCreature = mock(Creature.class);
        this.arrayList.add(testCreature);
        assertEquals(testCreature, this.arrayList.get(0));
    }
    
    @Test
    public void addIntCantAddCreature() {
        Creature testCreature = mock(Creature.class);
        this.arrayList.add(1);
        try {
            this.arrayList.add(testCreature);
            fail("Inserted object of wrong class.");
        } catch (IllegalArgumentException e) { }
    }
    

    
    @Test
    public void outOfBoundsIsOutOfBonds() {
        this.arrayList.add(1);
        try {
            this.arrayList.get(1);
            fail("Out of bounds.");
        } catch (IllegalArgumentException e) { }
        try {
            this.arrayList.get(-1);
            fail("Out of bounds.");
        } catch (IllegalArgumentException e) { }
    }
    
    @Test
    public void addSecondAndGetLast() {
        this.arrayList.add(1);
        this.arrayList.add(2);
        assertEquals(2, this.arrayList.get(this.arrayList.size()-1));
    }
    
    @Test
    public void getSizeWorks() {
        this.arrayList.add("j");
        this.arrayList.add("j");
        this.arrayList.add("j");
        this.arrayList.add("j");
        this.arrayList.add("j");
        this.arrayList.add("j"); 
        assertEquals(6, this.arrayList.size());
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
    
}


