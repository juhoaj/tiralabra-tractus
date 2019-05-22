package ui;

import asciiPanel.AsciiPanel;
import asciiPanel.AsciiFont; 
import engine.PlayerAction;
import domain.Creature;
import domain.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Interface extends JFrame  {

	
	private AsciiPanel terminal;
	private KeyListener keylistener;
	private int viewportWidth;
	private int viewportHeight;
        private Creature player;
        private World world;

	
    public Interface(World world, Creature player, PlayerAction playerAction, int viewportWidth, int viewportHeight) {
    	
    	this.viewportWidth = viewportWidth;
    	this.viewportHeight = viewportHeight;
        this.keylistener = new KeyListener(playerAction);
        this.world = world;
        this.player = player;

    	this.terminal = new AsciiPanel(this.viewportWidth, this.viewportHeight, AsciiFont.DRAKE_10x10);
    	
        super.add(terminal);
        super.addKeyListener(keylistener);
        super.setSize(this.viewportWidth*10, this.viewportHeight*10);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.repaint();
        this.terminal.write("You are having fun.", 1, 1);
    }

    public void refresh() {
    	// terminal.repaint();
        int startX = this.player.getX() - this.viewportWidth / 2;
        int startY = this.player.getY() - this.viewportHeight / 2;
        for (int x = 0 ; x < viewportWidth ; x++) {
            for (int y = 0 ; y < viewportHeight ; y++) {
                char c = (char) 177;
                if (this.world.getTerrain(x+startX, y+startY) == 0) {
                    c = (char) 250;
                }
                this.terminal.write( c, x, y);
                System.out.println(x+","+y+"-"+this.world.getTerrain(x, y));
                
            }
        }
        this.terminal.write("@", this.viewportWidth / 2 + 1, this.viewportHeight / 2 + 1 );
        
    }
    
}
