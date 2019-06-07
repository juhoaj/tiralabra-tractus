/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.*;
import java.awt.event.*;

import engine.PlayerController;
import domain.Command;

/**
 * <h1>KeyListener</h1>
 * Listens for keystrokes and calls engine.PlayerAction when relevant key is
 * pressed (W,A,S,D).
 */
class KeyListener extends KeyAdapter {

    private PlayerController playerController;

    /**
     * Constructor that injects the PlayerController for relaying commands. 
     */
    public KeyListener(PlayerController playerController) {

        this.playerController = playerController;
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
                playerController.setAction(Command.NORTH);
                break;
            case 'd':
                playerController.setAction(Command.EAST);
                break;
            case 's':
                playerController.setAction(Command.SOUTH);
                break;
            case 'a':
                playerController.setAction(Command.WEST);
                break;
        }

    }

}
