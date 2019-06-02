/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Creature;
import domain.World;
import helpers.Node;
import helpers.Distance;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Routefinder that utilizes A* algorithm for fast route resolving.
 * A* algorithm processes the route as Node's which are generated based on
 * information received from World.
 */
public class RouteFinder {

    int[] startPosition;
    int[] endPosition;
    World world;
    GameController gameController;
    boolean debugging;
    ArrayList<Creature> monsterList;
    Distance distance;

    /**
     * Constructor which injects also gameController which is required by 
     * debugging (drawing algorithm's routefinding). Initializes Distance.
     * 
     * @param world contains and controls the map
     * @param monsterList list of all monsters
     * @param gameController controls the game
     * @param debugging set true to draw algorithm's routefinding
     */
    public RouteFinder(World world, ArrayList<Creature> monsterList, GameController gameController, boolean debugging) {
        this.world = world;
        this.gameController = gameController;
        this.debugging = debugging;
        this.monsterList = monsterList;
        this.distance = new Distance();
    }
    
    /**
     * Constructor which does not allow for debugging.
     * 
     * @param world contains and controls the map
     * @param monsterList list of all monsters
     */
    public RouteFinder(World world, ArrayList<Creature> monsterList) {
        this.world = world;
        this.monsterList = monsterList;
        this.distance = new Distance();
    }

    /**
     * Called to determine next step on route between two positions. 
     * Determines the step by getting the route from player to monster from
     * the actual algorithm. After this it returns the position of the second 
     * last node (next step on monster's route). Returns null if either 
     * position is outside the map.
     *
     * @param startPosition Start position {x-coordinate,y-coordinate}
     * @param endPosition End position {x-coordinate,y-coordinate}
     * @return {x-coordinate,y-coordinate} or null if out of bounds
     */
    public int[] getNextMove(int[] startPosition, int[] endPosition) {
        int startX = startPosition[0];
        int startY = startPosition[1];
        int endX = endPosition[0];
        int endY = endPosition[1];

        if (startX < 0 || 
            startY < 0 || 
            endX < 0 || 
            endY < 0 || 
            startX >= this.world.getWidth() || 
            startY >= this.world.getHeight() || 
            endX >= this.world.getWidth() || 
            endY >= this.world.getHeight()
        ) {
            return null;
        }

        ArrayList<Node> route = this.AStar(endX, endY, startX, startY);
        Node secondLastNode = route.get(1);
        return secondLastNode.getPosition();

    }
    
    private ArrayList<Node> AStar(int startX, int startY, int endX, int endY) {
        Node[][] openList = new Node[this.world.getWidth()][this.world.getHeight()];
        PriorityQueue<Node> nodeHeap = new PriorityQueue<>();

        // create first node 
        int startNodeHeuristic = this.distance.getDistance(startX, startY, endX, endY);
        Node startNode = new Node(startX, startY, 0, startNodeHeuristic, 0);
        nodeHeap.add(startNode);
        openList[startX][startY] = startNode;
        
        // potential nodes on route are inserted in nodeHeap and most promising
        // node is retrieved until heap is empty or end node 
        while (!nodeHeap.isEmpty() ) {
            
            // check if target found
            Node currentNode = nodeHeap.poll();
            if (currentNode.getX() == endX && currentNode.getY() == endY) {
                break;
            }
            
            // Check adjacent Nodes from World and create new Nodes if nessecary
            ArrayList<int[]> childrenPositions = this.world.getNeighborPositions(currentNode.getPosition());

            for (int[] childPosition : childrenPositions) {
                int ChildX = childPosition[0];
                int ChildY = childPosition[1];
                int h =this.distance.getDistance(ChildX, ChildY, endX, endY);
                int g = currentNode.getG() + this.world.getTerrain(ChildX, ChildY);
                if (openList[ChildX][ChildY] == null || openList[ChildX][ChildY].getG() > g) {
                    Node childNode = new Node(ChildX, ChildY, g, h, g + h, currentNode);
                    nodeHeap.add(childNode);
                    openList[ChildX][ChildY] = childNode;
                    if (debugging == true) {
                        this.gameController.drawCharacter('/', ChildX, ChildY);
                    }
                }
            }
            
            if (debugging == true) {
                this.gameController.drawCharacter('X', currentNode.getX(), currentNode.getY());
            }
        }

        // traverse route and create ArrayList of the route 
        // (nodes along the route)
        
        ArrayList<Node> route = new ArrayList<>();
 
        // get the node on end x&y
        Node nextAddedNode = openList[endX][endY];
        route.add(nextAddedNode);
        while (true) {
            //end if node if start node
            if (nextAddedNode.getX() == startX && nextAddedNode.getY() == startY) {
                break;
            }
            // add parent Node
            nextAddedNode = nextAddedNode.getParent();
            if (debugging == true) {
                this.gameController.drawCharacter('R', nextAddedNode.getX(), nextAddedNode.getY());
            }
            route.add(nextAddedNode);
        }

        return route;
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
