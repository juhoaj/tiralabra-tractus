/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.*;
import java.awt.event.*;

import engine.PlayerAction;
import domain.Direction;

/**
 *
 * @author juhojuutilainen
 */
    
    
class KeyListener extends KeyAdapter {
    
    private PlayerAction playerAction;
    private Direction direction;

    public KeyListener(PlayerAction playerAction) {
        this.playerAction = playerAction;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        char c = event.getKeyChar();
        System.out.println("rekisterlity keylistenerissä: " + c);
        
        switch (c) {
            case 'w':
                playerAction.setAction(Direction.NORTH);
                // this.direction = Direction.NORTH;
                
        }
          
    }

   
            

}
