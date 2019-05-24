/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.Direction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import engine.PlayerAction;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import javax.swing.JFrame;

/**
 * 
 * @author juhojuutilainen
 */
public class KeyListenerTest {

    JFrame instance;
    PlayerAction playerAction;
    KeyListener keyListener;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new JFrame();
        playerAction = mock(PlayerAction.class);
        keyListener = new KeyListener(playerAction);
        instance.addKeyListener(keyListener);
        instance.requestFocus();
        
    }

    @After
    public void tearDown() {

    }

    @Test
    public void hello() throws AWTException {

        KeyEvent event = new KeyEvent(instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  'w');
        keyListener.keyPressed(event);
             
        // verify(playerAction, times(1)).setAction(Direction.NORTH);
        verify(playerAction).setAction(eq(Direction.NORTH));
        
    }
}
