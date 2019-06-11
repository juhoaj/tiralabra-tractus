/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.World;
import engine.MonsterController;
import engine.PlayerController;
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
public class InterfaceTest {

    private World world;
    private PlayerController playerController;
    private MonsterController monsterController;
    private Interface testInterface;
    
    
    @Before
    public void setUp() {
        this.world = mock(World.class);
        this.playerController = mock(PlayerController.class);
        this.monsterController = mock(MonsterController.class);
        this.testInterface = new Interface(this.world, this.playerController, this.monsterController, 10, 10, false);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void InterfaceInitialized() {
        assertNotNull(this.testInterface);
    }
    
}
