package ui;

import asciiPanel.AsciiPanel;
import asciiPanel.AsciiFont;
import engine.PlayerController;
import domain.Creature;
import domain.Command;
import domain.World;
import engine.MonsterController;

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

    /**
     * Constructor that receives parameters and objects used for output
     * and receiving player actions.
     *
     * @param world contains and controls the map
     * @param playerController receives imput from ui and controls player-object
     * @param monsterController controls monsters
     * @param viewportWidth width of viewport (characters)
     * @param viewportHeight height of viewport (characters)
     */
    public Interface(World world, PlayerController playerController, MonsterController monsterController, int viewportWidth, int viewportHeight) {
        if (viewportWidth <= 3 || viewportHeight <= 3 || viewportWidth > 52 || viewportHeight > 52) {
            System.out.println("viewport size incompatible with ui");
            return;
        }
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.keylistener = new KeyListener(playerController);
        this.world = world;
        this.monsterController = monsterController;
        this.playerController = playerController;

        this.terminal = new AsciiPanel(this.viewportWidth, this.viewportHeight, AsciiFont.DRAKE_10x10);

        super.add(terminal);
        super.addKeyListener(keylistener);
        super.setSize(this.viewportWidth * 10, this.viewportHeight * 10);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                char c = (char) 177;
                if (this.world.getTerrain(x + startX, y + startY) == 1) {
                    c = (char) 250;
                }
                this.terminal.write(c, x, y);
            }
        }
    }

    private void drawCreatures(int startX, int startY) {
        this.terminal.write("@", this.playerController.getPlayerPosition()[0] - startX, this.playerController.getPlayerPosition()[1] - startY);

        int[][] monsterPositions = this.monsterController.getMonsterPositions();
        for (int i = 0; i < monsterPositions.length; i++) {
            int monsterX = monsterPositions[i][0] - startX;
            int monsterY = monsterPositions[i][1] - startY;
            if (monsterX > 0 && monsterX < viewportWidth && monsterY > 0 && monsterY < viewportHeight) {
                this.terminal.write("*", monsterX, monsterY);
            }
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
     * @param message message content
     */
    public void message(String message) {
        this.terminal.write(message, 1, 1);
    }
}
