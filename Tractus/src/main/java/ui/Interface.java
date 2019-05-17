package ui;

import asciiPanel.AsciiPanel;
import engine.PlayerAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Interface extends JFrame  {

	
	private AsciiPanel terminal;
	private KeyListener keylistener;
	private int screenWidth;
	private int screenHeight;

	
    public Interface(PlayerAction playerAction, int screenWidth, int screenHeight) {
    	
    	this.screenWidth = screenWidth;
    	this.screenHeight = screenHeight;
        this.keylistener = new KeyListener(playerAction);

    	terminal = new AsciiPanel(screenWidth, screenHeight);
    	
        super.add(terminal);
        super.addKeyListener(keylistener);
        super.setSize(screenWidth*9, screenHeight*16);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.repaint();
        terminal.write("You are having fun.", 1, 1);
    }
    
    public AsciiPanel getTerminal() {
    	return terminal;
    }
    
    
    
    public void refresh() {
    	terminal.repaint();
    }
    
}
