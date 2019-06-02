/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 * Distance related helpers.
 * 
 */
public class Distance {
    
    /**
     * Returns the distance between two x,y coordinates
     * 
     * @param startX Start x-coordinate
     * @param startY Start y-coordinate
     * @param endX End x-coordinate
     * @param endY End y-coordinate
     * @return distance
     */
    
    public int getDistance(int startX, int startY, int endX, int endY) {
        int width = Math.max(startX, endX) - Math.min(startX, endX);
        int height = Math.max(startY, endY) - Math.min(startY, endY);
        return (int) Math.sqrt((width * width) + (height * height));
    }
}
