/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.*;
import java.awt.event.*;

import engine.PlayerAction;

/**
 *
 * @author juhojuutilainen
 */
    
    
class KeyListener extends KeyAdapter {
    
    private PlayerAction playerAction;

    public KeyListener(PlayerAction playerAction) {
        this.playerAction = playerAction;
    }

    
    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println("painettu");
        playerAction.setAction(event.getKeyChar());
    }

   
            

}
