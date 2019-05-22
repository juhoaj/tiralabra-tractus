/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author juhojuutilainen
 */
public class World {
    int width;
    int height;
    int[][] map;

    public World(int x, int y) {
        this.width = x;
        this.height = y;
        this.map = new int[x][y];
    }
    
    public void initialize() {
        for (int x = 0 ; x < this.width ; x++) {
            for (int y = 0 ; y < this.height ; y++) {
                this.map[x][y]=0;
            }
        }
        this.map[1][1]=1;
    }
    
    public int getTerrain(int x, int y) {
        if ( x<0 || y<0 || x>this.width || y>this.width ) {
            return 0;
        }
        return map[x][y];
    }
    
    
    
    
}
