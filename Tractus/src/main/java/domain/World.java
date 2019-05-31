/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 * <h1>World</h1>
 * This class provides the 2D tiled map that is used in the game. Currently the
 * map has only corridors and walls.
 */
public class World {

    int width;
    int height;
    int[][] map;

    /**
     * Constructor requires the size of the map.
     *
     * @param width width of the map.
     * @param height height of the map. Please note that the constructor does
     * not initialize the map.
     */
    public World(int width, int height) {

        this.width = width;
        this.height = height;
        this.map = new int[this.width][this.height];
    }

    /**
     * Creates random caves to the map.
     */
    public void initialize() {
        this.randomize();
        this.smooth(8);
    }

    /**
     * Creates empty map / empties the map.
     */
    public void initializeEmpty() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.map[x][y] = 1;
            }
        }
    }

    private void randomize() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                map[x][y] = Math.random() < 0.5 ? 1 : 2;
            }
        }
    }

    private void smooth(int times) {
        int[][] map2 = new int[width][height];
        for (int time = 0; time < times; time++) {

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int floors = 0;
                    int rocks = 0;

                    for (int ox = -1; ox < 2; ox++) {
                        for (int oy = -1; oy < 2; oy++) {
                            if (x + ox < 0 || x + ox >= width || y + oy < 0
                                    || y + oy >= height) {
                                continue;
                            }

                            if (map[x + ox][y + oy] == 1) {
                                floors++;
                            } else {
                                rocks++;
                            }
                        }
                    }
                    map2[x][y] = floors >= rocks ? 1 : 2;
                }
            }
            map = map2;
        }
    }

    /**
     * Returns the terrain of given map coordinate
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return terrain: 1=corridor, 2=wall
     */
    public int getTerrain(int x, int y) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.width) {
            return 2;
        }
        return this.map[x][y];
    }
    
    public ArrayList<int[]> getNeighborPositions(int[] position) {
        ArrayList<int[]> neighborPositions = new ArrayList<>(4);
        int parentX = position[0];
        int parentY = position[1];
        
        if (parentX > 0) {
            neighborPositions.add(this.getPosition(parentX-1, parentY));
        }
        if (parentX < this.width-1) {
            neighborPositions.add(this.getPosition(parentX+1, parentY));
        }
        if (parentY > 0) {
            neighborPositions.add(this.getPosition(parentX, parentY-1));
        }
        if (parentY < this.width-1) {
            neighborPositions.add(this.getPosition(parentX, parentY+1));
        }
        return neighborPositions;
        
    }
    
    private int[] getPosition(int childX, int childY){
        int[] position = new int[2];
        position[0]=childX;
        position[1]=childY;
        return position;
    }
    
    /**
     * Sets terrain on given coordinate
     * 
     * @param x X-coordinate
     * @param y y-coordinate
     * @param terrain 1=corridor, 2=wall
     */
    public void setTerrain(int x, int y, int terrain) {
        if (x < 0 || y < 0 || x >= this.width || y>= this.width || terrain < 1 || terrain > 2) {
            return;
        }
        this.map[x][y] = terrain;
    }
    
    /**
     * Returns the width of the map
     * 
     * @return map width
     */
    public int getWidth(){
        return this.width;
    }
    
    /**
     * Returns the height of the map
     * 
     * @return map height
     */
    public int getHeight() {
        return this.height;
    }

}
