/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.World;
import domain.Node;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author juhojuutilainen
 */
public class RouteFinder {

    int[] startPosition;
    int[] endPosition;
    World world;

    /**
     *
     * @param world
     */
    public RouteFinder(World world) {
        this.world = world;
    }

    /**
     * Called to determine next step on route between two positions. Returns
     * null if either position is outside the map.
     *
     * @param startPosition
     * @param endPosition
     * @return
     */
    public int[] getNextMove(int[] startPosition, int[] endPosition) {
        int startX = startPosition[0];
        int startY = startPosition[1];
        int endX = endPosition[0];
        int endY = endPosition[1];

        if (startX < 0
                || startY < 0
                || endX < 0
                || endY < 0
                || startX >= this.world.getWidth()
                || startY >= this.world.getHeight()
                || endX >= this.world.getWidth()
                || endY >= this.world.getHeight()) {
            return null;
        }

        ArrayList<Node> route = this.AStar(endX, endY, startX, startY);
        System.out.println(route);
        System.out.println(route.size());
        Node secondLastNode = route.get(1);
        System.out.println("monsteri x,y" + secondLastNode.getX() + "," + secondLastNode.getY());
        return secondLastNode.getPosition();

    }

    private ArrayList<Node> AStar(int startX, int startY, int endX, int endY) {
        Node[][] openList = new Node[this.world.getWidth()][this.world.getHeight()];
        // Node[][] closedList = new Node[this.world.getWidth()][this.world.getHeight()];
        PriorityQueue<Node> nodeHeap = new PriorityQueue<>();

        int startNodeHeuristic = this.getHeuristic(startX, startY, endX, endY);
        Node startNode = new Node(startX, startY, 0, startNodeHeuristic, 0);
        nodeHeap.add(startNode);
        openList[startX][startY] = startNode;
        
        while ( !nodeHeap.isEmpty() ) {
            Node currentNode = nodeHeap.poll();
            // closedList[currentNode.getX()][currentNode.getY()] = currentNode;
            
            if (endX == currentNode.getX() && endY == currentNode.getY()) {
                System.out.println("löyty");
                ArrayList<Node> route = new ArrayList<>();
                Node nextAddedNode = openList[endX][endY];
                route.add(nextAddedNode);
                while (true) {
                    if (nextAddedNode.getX() == startX && nextAddedNode.getY() == startY) {
                        return route;
                    }
                    nextAddedNode = nextAddedNode.getParent();
                    route.add(nextAddedNode);
                    System.out.println("reitti" + nextAddedNode.getX() + "," + nextAddedNode.getY());
                }
                
            }

            ArrayList<int[]> childrenPositions = this.world.getNeighborPositions(currentNode.getPosition());
            
            for ( int[] childPosition : childrenPositions ) {
                int ChildX = childPosition[0];
                int ChildY = childPosition[1];
                /*
                if (closedList[ChildX][ChildY] != null) {
                    break;
                }
                */
                int h = this.getHeuristic(ChildX, ChildY, endX, endY);
                int g = currentNode.getG() + this.world.getTerrain(ChildX, ChildY);
                Node childNode = new Node(ChildX, ChildY, g, h, g+h, currentNode);
                if (openList[ChildX][ChildX]==null || openList[ChildX][ChildX].getG()>g) {
                    nodeHeap.add(childNode);
                    openList[ChildX][ChildY] = childNode;
                }
            }
            
        }
        
        return null;
    }

    private int getHeuristic(int startX, int startY, int endX, int endY) {
        int width = Math.max(startX, endX) - Math.min(startX, endX);
        int height = Math.max(startY, endY) - Math.min(startY, endY);
        return (int) Math.sqrt((width * width) + (height * height));

    }

    private void setSearchArea(int increment) {
        int smallerX = Math.min(startPosition[0], endPosition[0]);
        int largerX = Math.max(startPosition[0], endPosition[0]);
        int smallerY = Math.min(startPosition[1], endPosition[1]);
        int largerY = Math.max(startPosition[1], endPosition[1]);
        int startX = Math.max(smallerX - increment, 0);
        int endX = Math.min(smallerX + increment, this.world.getWidth());
        int startY = Math.max(smallerY - increment, 0);
        int endY = Math.min(smallerX + increment, this.world.getHeight());
    }

}
