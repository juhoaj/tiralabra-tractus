package domain;

/**
 * Tile is used to represent a single x&y -coordinate in cave or map
 */
public class Tile {
    private int tileX;
    private int tileY;

    /**
     * Constructor for tile.
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
     * Get tile's x-coordinate
     * @return x-coordinate
     */
    public int getX() {
        return tileX;
    }

    /**
     * Get tile's y-coordinate 
     * @return y-coordinate
     */
    public int getY() {
        return tileY;
    }
    
    
}
