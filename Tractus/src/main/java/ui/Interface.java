package ui;

import asciiPanel.AsciiPanel;
import asciiPanel.AsciiFont;
import engine.PlayerController;
import domain.Creature;
import domain.Command;
import domain.World;
import engine.MonsterController;
import helpers.Distance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * <h1>Interface</h1>
 * Responsible for user interface. Mainly setting up JFrame, AsciiPanel,
 * KeyListener (that calls engine.PlayerAction to determine input result) and
 * rendering.
 */
public class Interface extends JFrame {

    private AsciiPanel terminal;
    private KeyListener keylistener;
    private int viewportWidth;
    private int viewportHeight;
    private MonsterController monsterController;
    private PlayerController playerController;
    private World world;
    private Distance distance;
    private int centerX;
    private int centerY;
    private int viewDistance;
    private boolean debugging;

    /**
     * Constructor that receives parameters and objects used for output and
     * receiving player actions.
     *
     * @param world contains and controls the map
     * @param playerController receives imput from ui and controls player-object
     * @param monsterController controls monsters
     * @param viewportWidth width of viewport (characters)
     * @param viewportHeight height of viewport (characters)
     */
    public Interface(World world, PlayerController playerController, MonsterController monsterController, int viewportWidth, int viewportHeight, boolean debugging) {
        this.debugging = debugging;
        if (viewportWidth <= 3 || viewportHeight <= 3 || viewportWidth > 52 || viewportHeight > 52) {
            throw new IllegalArgumentException("viewport size incompatible with ui");
        }
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.keylistener = new KeyListener(playerController);
        this.world = world;
        this.monsterController = monsterController;
        this.playerController = playerController;

        this.terminal = new AsciiPanel(this.viewportWidth, this.viewportHeight, AsciiFont.DRAKE_10x10);
        this.distance = new Distance();
        super.add(terminal);
        super.addKeyListener(keylistener);
        super.setSize(this.viewportWidth * 10, this.viewportHeight * 10);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.centerX = this.viewportWidth / 2;
        this.centerY = this.viewportWidth / 2;
        this.viewDistance = Math.min(this.viewportWidth, this.viewportWidth) / 2 - 2;
        
        
    }

    /**
     * Renders and draws the view around the player on AsciiPanel object.
     */
    public void refresh() {
        terminal.clear();
        int startX = this.playerController.getPlayerPosition()[0] - this.viewportWidth / 2;
        int startY = this.playerController.getPlayerPosition()[1] - this.viewportHeight / 2;
        this.drawMap(startX, startY);
        this.drawCreatures(startX, startY);
        terminal.repaint();
    }

    private void drawMap(int startX, int startY) {
        for (int x = 0; x < viewportWidth; x++) {
            for (int y = 0; y < viewportHeight; y++) {
                if (this.distance.getDistance(this.centerX, this.centerY, x, y) < this.viewDistance) {
                    char c = (char) 177;
                    if (this.world.getTerrain(x + startX, y + startY) == 1) {
                        c = (char) 250;
                    }
                    this.terminal.write(c, x, y);
                }
            }
        }
    }

    

    private void drawCreatures(int startX, int startY) {
        this.terminal.write("@", this.playerController.getPlayerPosition()[0] - startX, this.playerController.getPlayerPosition()[1] - startY);

        int[][] monsterPositions = this.monsterController.getMonsterPositions();
        for (int i = 0; i < monsterPositions.length; i++) {
            int monsterX = monsterPositions[i][0] - startX;
            int monsterY = monsterPositions[i][1] - startY;
            
            if (this.distance.getDistance(this.centerX, this.centerY, monsterX, monsterY) < this.viewDistance) {
                this.terminal.write("*", monsterX, monsterY);
            }
        }
    }

    /**
     * Draw's a single character on AsciiPanel
     *
     * @param character character to be drawn
     * @param x x-coordinate of the character
     * @param y y-coordinate of the character
     */
    public void drawCharacter(char character, int x, int y) {
        int originX = this.viewportWidth / 2 - this.playerController.getPlayerPosition()[0];
        int originY = this.viewportHeight / 2 - this.playerController.getPlayerPosition()[1];
        int drawX = x + originX;
        int drawY = y + originY;
        if (drawX > 0 && drawX < this.viewportWidth && drawY > 0 && drawY < this.viewportHeight) {
            terminal.clear();
            this.terminal.write(character, drawX, drawY);
        }

    }

    /**
     * Clears the view on AsciiPanel object.
     */
    public void clear() {
        terminal.clear();
    }

    /**
     * Shows a short message to player.
     *
     * @param message message content
     */
    public void message(String message) {
        this.terminal.write(message, 0, 0);
    }
}
