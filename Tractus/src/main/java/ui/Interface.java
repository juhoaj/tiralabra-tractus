package ui;

import asciiPanel.AsciiPanel;
import asciiPanel.AsciiFont;
import engine.PlayerAction;
import domain.Creature;
import domain.Command;
import domain.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private Creature player;
    private World world;

    /**
     * Constructor that receives parameters and objects used in rendering
     * PlayerAction for calling it from ui.KeyListener.
     * @param world World object
     * @param playerAction PlayerAction object
     * @param player Creature object
     * @param viewportWidth width of viewport (characters)
     * @param viewportHeight height of viewport (characters)
     */
    public Interface(World world, Creature player, PlayerAction playerAction, int viewportWidth, int viewportHeight) {

        if (viewportWidth <= 3 || viewportHeight <= 3 || viewportWidth > 52 || viewportHeight > 52) {
            System.out.println("viewport size incompatible with ui");
            return;
        }

        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.keylistener = new KeyListener(playerAction);
        this.world = world;
        this.player = player;

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

        //terminal.clear();
        int startX = this.player.getX() - this.viewportWidth / 2;
        int startY = this.player.getY() - this.viewportHeight / 2;
        for (int x = 0; x < viewportWidth; x++) {
            for (int y = 0; y < viewportHeight; y++) {
                char c = (char) 177;
                if (this.world.getTerrain(x + startX, y + startY) == 0) {
                    c = (char) 250;
                }
                this.terminal.write(c, x, y);
            }
        }

        this.terminal.write(Integer.toString(this.player.getY()), 1, 1);
        this.terminal.write("@", this.player.getX() - startX, this.player.getY() - startY);
        terminal.repaint();
    }

    /**
     * Clears the view on AsciiPanel object.
     */
    public void clear() {

        terminal.clear();

    }

}
