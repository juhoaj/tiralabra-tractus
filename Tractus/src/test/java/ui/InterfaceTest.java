/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.World;
import engine.MonsterController;
import engine.PlayerController;
import org.junit.Before;
import org.junit.Test;
import helpers.Distance;
import asciiPanel.AsciiPanel;
import java.io.IOException;
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
    private AsciiPanel terminal;
    private Distance distance;
    private int[] playerPosition = {5,5};
    
    
    @Before
    public void setUp() throws IOException {        
        
        this.world = mock(World.class);
        this.playerController = mock(PlayerController.class);
        this.playerPosition = new int[2];
        when(this.playerController.getPlayerPosition()).thenReturn(this.playerPosition);
        this.monsterController = mock(MonsterController.class);
        this.terminal = mock(AsciiPanel.class);
        this.distance = mock(Distance.class);
        this.testInterface = new Interface(this.world, this.playerController, this.monsterController, 10, 10, false);
    
    
    }
    
    
    @Test
    public void InterfaceInitialized() {
        assertNotNull(this.testInterface);
    }
    
    @Test
    public void throwsErrorIfTooLargeInterface() {
        try {
            Interface i = new Interface(this.world, this.playerController, this.monsterController, 4, 10, false);
            fail("viewport size incompatible with ui");
        } catch (IllegalArgumentException e) { }
        try {
            Interface i = new Interface(this.world, this.playerController, this.monsterController, 10, 4, false);
            fail("viewport size incompatible with ui");
        } catch (IllegalArgumentException e) { }
        try {
            Interface i = new Interface(this.world, this.playerController, this.monsterController, 53, 10, false);
            fail("viewport size incompatible with ui");
        } catch (IllegalArgumentException e) { }
        try {
            Interface i = new Interface(this.world, this.playerController, this.monsterController, 10, 53, false);
            fail("viewport size incompatible with ui");
        } catch (IllegalArgumentException e) { }
    }
    
    
    @Test
    public void characterDrawn() {
        this.testInterface.drawCharacter('x', 10, 10);
        // verify(this.terminal).write("x",1,1);
    }
    
    
}
