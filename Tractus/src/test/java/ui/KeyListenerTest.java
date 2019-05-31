/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.Command;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import engine.PlayerController;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class KeyListenerTest {

    JFrame instance;
    PlayerController playerController;
    KeyListener keyListener;

    @Before
    public void setUp() {
        instance = new JFrame();
        playerController = mock(PlayerController.class);
        keyListener = new KeyListener(playerController);
        instance.addKeyListener(keyListener);
        instance.requestFocus(); 
    }

    @Test
    public void keyWWorksAndCallsSetActionWithNORTH() {
        KeyEvent event = new KeyEvent(instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  'w');
        keyListener.keyPressed(event);
        verify(playerController).setAction(eq(Command.NORTH));
    }
    
    @Test
    public void keyDWorksAndCallsSetActionWithEAST() {
        KeyEvent event = new KeyEvent(instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  'd');
        keyListener.keyPressed(event);
        verify(playerController).setAction(eq(Command.EAST));
    }
    
    @Test
    public void keySWorksAndCallsSetActionWithSOUTH() {
        KeyEvent event = new KeyEvent(instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  's');
        keyListener.keyPressed(event);
        verify(playerController).setAction(eq(Command.SOUTH));
    }
    
    @Test
    public void keyAWorksAndCallsSetActionWithWEST() {
        KeyEvent event = new KeyEvent(instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  'a');
        keyListener.keyPressed(event);
        verify(playerController).setAction(eq(Command.WEST));
    }
    
    
}
