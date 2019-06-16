package domain;

/**
 *
 * @author juhojuutilainen
 */
public class Tile {
    private int tileX;
    private int tileY;

    /**
     *
     * @param tileX
     * @param tileY
     */
    public Tile(int tileX, int tileY) {
        if (tileX < 0 || tileY < 0) {
            throw new IllegalArgumentException("Map has no negative coordinates");
        }
        this.tileX = tileX;
        this.tileY = tileY;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return tileX;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return tileY;
    }
    
    
}
