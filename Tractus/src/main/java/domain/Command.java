package domain;

/**
 * Enumeration that is used in communication between objects. 
 * For example ui.KeyListener calls engine.PlayerAction with this enumeration.
 */
public enum Command {

    /**
     * Move 'up'
     */
    NORTH,

    /**
     * Move 'right'
     */
    EAST,

    /**
     * Move 'down'
     */
    SOUTH,

    /**
     * Move 'left'
     */
    WEST
}
