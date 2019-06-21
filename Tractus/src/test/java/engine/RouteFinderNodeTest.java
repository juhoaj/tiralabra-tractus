/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RouteFinderNodeTest {
    private int x = 1;
    private int y = 2;
    private int g = 3;
    private int h = 4;
    private int f = 5;
    private RouteFinderNode RouteFinderTestNode;
    private RouteFinderNode RouteFinderRootTestNode;
    
    

    @Before
    public void setUp() {
        this.RouteFinderRootTestNode = new RouteFinderNode(this.x, this.y, this.g, this.h, this.f);
        this.RouteFinderTestNode = new RouteFinderNode(this.x, this.y, this.g, this.h, this.f, this.RouteFinderRootTestNode);
    }
    

    @Test
    public void getXWorks() {
        assertEquals(this.x, this.RouteFinderTestNode.getX());
        assertEquals(this.x, this.RouteFinderRootTestNode.getX());
    }
    
    @Test
    public void getYWorks() {
        assertEquals(this.y, this.RouteFinderTestNode.getY());
        assertEquals(this.y, this.RouteFinderRootTestNode.getY());
    }
    
    @Test
    public void getGWorks() {
        assertEquals(this.g, this.RouteFinderTestNode.getG());
        assertEquals(this.g, this.RouteFinderRootTestNode.getG());
    }
    
    
    @Test
    public void getFWorks() {
        assertEquals(this.f, this.RouteFinderTestNode.getF());
        assertEquals(this.f, this.RouteFinderRootTestNode.getF());
    }
    
    @Test
    public void getValueWorks() {
        assertEquals(this.f, this.RouteFinderTestNode.getValue());
    }
    
    
    @Test
    public void getPositionWorks() {
        assertEquals(this.x, this.RouteFinderTestNode.getPosition()[0]);
        assertEquals(this.y, this.RouteFinderRootTestNode.getPosition()[1]);
    }
    
    @Test
    public void getParentWorks() {
        int[] position = {this.x, this.y};
        assertEquals(this.RouteFinderRootTestNode, this.RouteFinderTestNode.getParent());
    }
    
    @Test
    public void compareToWorks() {
        assertEquals(0, this.RouteFinderTestNode.compareTo(this.RouteFinderTestNode));
        RouteFinderNode hugeFNode = new RouteFinderNode(this.x, this.y, this.g, this.h, 10);
        assertEquals(-1, this.RouteFinderTestNode.compareTo(hugeFNode));
        assertEquals(1, hugeFNode.compareTo(this.RouteFinderTestNode));
    }
}
