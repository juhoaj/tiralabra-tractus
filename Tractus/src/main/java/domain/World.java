/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import helpers.CustomArrayList;
import java.util.Random;
import helpers.Distance;

/**
 * <h1>World</h1>
 * This class provides the 2D tiled map that is used in the game. Currently the
 * map has only corridors and walls.
 */
public class World {

    int width;
    int height;
    int[][] map;
    boolean[][] visited;
    private boolean[][] connected;
    private boolean debugging;
    Random random;
    

    /**
     * Constructor requires the size of the map.
     *
     * @param width width of the world.
     * @param height height of the world. Please note that the constructor does
     * not create the map.
     * @param debugging
     */
    public World(int width, int height, boolean debugging) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Map size has to be positive.");
        }
        this.width = width;
        this.height = height;
        this.map = new int[this.width][this.height];
        this.debugging = debugging;
        this.random = new Random();
        
    }
    
    /**
     * Creates a new map with caves
     * @return 
     */
    
    public boolean initializeCaves() {
        while (true) {
            this.connected = new boolean[this.width][this.height];
            this.randomize();
            this.smooth(5);
            if (this.validateAndFillUnconnectedCaves() == true) {
                return true;
            }
            this.map = new int[this.width][this.height];
            return false;
        }
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
        if (this.debugging == true) {
            Random notRandom = new Random(14);
            this.random = notRandom;
        } 
        
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                map[x][y] = this.random.nextInt()< 0.5 ? 1 : 2;
            }
        }
        System.out.println("j");
    }
 

    
    private void smooth(int times) {
        int[][] map2 = new int[this.width][this.height];
        for (int i = 0; i < times; i++) {

            for (int x = 0; x < this.width ; x++) {
                for (int y = 0; y < this.height ; y++) {
                    int floors = 0;
                    int walls = 0;

                    for (int relativeX = -1; relativeX < 2; relativeX++) {
                        for (int relativeY = -1; relativeY < 2; relativeY++) {
                            if (this.getTerrain(x + relativeX, y + relativeY) == 1) {
                                floors++;
                            } else {
                                walls++;
                            }
                        }
                    }
                    map2[x][y] = 1;
                    if (floors < walls) {
                        map2[x][y] = 2;
                    }
                }
            }
            this.map = map2;
        }
    }
    
    private boolean validateAndFillUnconnectedCaves() {
        int largestCaveSize = 0;
        int largestCaveIndex = -1;
        this.visited = new boolean[this.width][this.height];
        CustomArrayList<Cave> caves = new CustomArrayList<>();
        for (int x = 0 ; x < this.width ; x++) {
            for (int y = 0 ; y < this.height ; y++) {
                if (this.map[x][y] == 1 && this.visited[x][y] != true ) {
                    Cave nextCave = this.getCave(x,y);
                    caves.add(nextCave);
                    if ( nextCave.getSize() > largestCaveSize ) {
                        largestCaveSize = nextCave.getSize();
                        largestCaveIndex = caves.size() -1;
                    }
                }
            }
        }
        if (largestCaveIndex == -1) {
            return false;
        }
        if (largestCaveSize < this.width * this.getHeight() / 10) {
            return false;
        }
        this.connected=new boolean[this.width][this.height];
        CustomArrayList<Tile> connectedTiles = caves.get(largestCaveIndex).getTiles();
        for ( int i = 0 ; i < connectedTiles.size() ; i++ ) {
            int tileX = connectedTiles.get(i).getX();
            int tileY = connectedTiles.get(i).getY();
            this.connected[tileX][tileY] = true;
        }
        
        return true;
        
    }
    
    private Cave getCave(int startX, int startY) {
        CustomArrayList<Tile> tiles = new CustomArrayList<>();
        tiles.add(new Tile(startX, startY));
        int i = 0;
        while (tiles.size() > i) {
            Tile node = tiles.get(i);
            i++;
            int[] nodePosition = {node.getX(),node.getY()};
            CustomArrayList<int[]> neighbors = this.getNeighborPositions(nodePosition);
            for (int j = 0 ; j < neighbors.size() ; j++) {
                int x = neighbors.get(j)[0];
                int y = neighbors.get(j)[1];
                if (this.map[x][y] == 1 && this.visited[x][y] != true) {
                    this.visited[x][y] = true;
                    tiles.add(new Tile(x, y));
                }
            }
        }
        Cave newCave = new Cave(tiles);
        return newCave;
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
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean getConnected(int x, int y) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.width) {
            return false;
        }
        return this.connected[x][y];
    }
    
    /**
     * Return's positions of given coordinates neighbors. Does not return
     * out of bounds positions.
     * 
     * @param position {x-coordinate,y-coordinate}
     * @return position {x-coordinate,y-coordinate}
     */
    public CustomArrayList<int[]> getNeighborPositions(int[] position) {
        CustomArrayList<int[]> neighborPositions = new CustomArrayList<>();
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
     * @return 
     */
    public boolean setTerrain(int x, int y, int terrain) {
        if (x < 0 || y < 0 || x >= this.width || y>= this.width || terrain < 1 || terrain > 2) {
            return false;
        }
        this.map[x][y] = terrain;
        return true;
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
