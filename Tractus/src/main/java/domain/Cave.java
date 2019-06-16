package domain;

import helpers.CustomArrayList;

/**
 * Cave object is used by World to list caves and tiles in each cave
 */
public class Cave implements Comparable<Cave>{
    private CustomArrayList<Tile> tiles;

    /**
     * Constructor requires CustomArrayList of tiles in the cave
     * @param tiles
     */
    public Cave(CustomArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    
    /**
     * Return the size of the cave (amount of tiles).
     * @return amount of tiles
     */
    public int getSize(){
        return this.tiles.size();
    }
    
    /**
     * Returns the tiles in the cave.
     * @return CustomArrayList of tiles in the cave
     */
    public CustomArrayList<Tile> getTiles() {
        return this.tiles;
    }
    
    @Override
    public int compareTo(Cave cave) {
        if(this.tiles.size() > cave.getSize()) {
            return 1;
        } else if (this.tiles.size() < cave.getSize()) {
            return -1;
        } else {
            return 0;
        }
    }
}
