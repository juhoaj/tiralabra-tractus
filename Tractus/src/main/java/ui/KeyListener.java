/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.*;
import java.awt.event.*;

import engine.PlayerAction;
import domain.Command;

/**
 * <h1>KeyListener</h1>
 * Listens for keystrokes and calls engine.PlayerAction when relevant key is
 * pressed (W,A,S,D).
 */
class KeyListener extends KeyAdapter {

    private PlayerAction playerAction;
    private Command direction;

    /**
     * Constructor that stores the engine.PlayerAction to be called.
     */
    public KeyListener(PlayerAction playerAction) {

        this.playerAction = playerAction;
    }

    /**
     * Method that override's java.awt.event.KeyPressed and calls
     * engine.PlayerAction when relevant key is pressed (W,A,S,D).
     */
    @Override
    public void keyPressed(KeyEvent event) {

        char c = event.getKeyChar();
        System.out.println("rekisterlity keylistenerissä: " + c);

        switch (c) {
            case 'w':
                playerAction.setAction(Command.NORTH);
                break;
            case 'd':
                playerAction.setAction(Command.EAST);
                break;
            case 's':
                playerAction.setAction(Command.SOUTH);
                break;
            case 'a':
                playerAction.setAction(Command.WEST);
                break;
        }

    }

}
