/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Creature;
import domain.World;
import helpers.Distance;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Routefinder that utilizes A* algorithm for fast route resolving.
 * A* algorithm processes the route as Node's which are generated based on
 * information received from World.
 */
public class RouteFinder {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int[] endPosition;
    private World world;
    private GameController gameController;
    private ArrayList<Creature> monsterList;
    private Distance distance;
    private boolean debugging;
    private boolean testPerformance;
    private RouteFinderNode[][] openList;
    private ArrayList<RouteFinderNode> route;
    private PriorityQueue<RouteFinderNode> nodeHeap; 

    /**
     * Constructor which injects also gameController which is required by 
     * debugging (drawing algorithm's routefinding). Initializes Distance.
     * 
     * @param world contains and controls the map
     * @param monsterList list of all monsters
     * @param gameController controls the game
     * @param debugging set true to draw algorithm's routefinding to terminal
     * @param testPerformance print performance of algorithms to console
     */
    public RouteFinder(World world, ArrayList<Creature> monsterList, GameController gameController, boolean debugging, boolean testPerformance) {
        this.world = world;
        this.gameController = gameController;
        this.debugging = debugging;
        this.monsterList = monsterList;
        this.distance = new Distance();
        this.testPerformance = testPerformance;
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
        this.debugging = false;
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
        this.startX = startPosition[0];
        this.startY = startPosition[1];
        this.endX = endPosition[0];
        this.endY = endPosition[1];

        if (this.startX < 0 || 
            this.startY < 0 || 
            this.endX < 0 || 
            this.endY < 0 || 
            this.startX >= this.world.getWidth() || 
            this.startY >= this.world.getHeight() || 
            this.endX >= this.world.getWidth() || 
            this.endY >= this.world.getHeight()
        ) {
            return null;
        }

        this.AStar();
        RouteFinderNode secondLastNode = this.route.get(this.route.size()-2);
        return secondLastNode.getPosition();

    }
    
    /**
     * A* routefinding algorithm which returns the route as ArrayList
     * containing positions in arrays {x-coordinate, y-coordinate}.
     */
    
    private void AStar() {
        RouteFinderNode[][] nextList = new RouteFinderNode[this.world.getWidth()][this.world.getHeight()];
        this.openList = nextList;
        PriorityQueue<RouteFinderNode> nextNodeHeap = new PriorityQueue<>();
        this.nodeHeap = nextNodeHeap;
        
        // create first node 
        int startNodeHeuristic = this.getHeuristic(this.startX, this.startY);
        RouteFinderNode startNode = new RouteFinderNode(this.startX, this.startY, 0, startNodeHeuristic, 0);
        nodeHeap.add(startNode);
        this.openList[this.startX][this.startY] = startNode;
        
        this.traverseNodes();
        
        this.getRoute();

        return;
    }
    
    
    /**
     * potential nodes on route are inserted in nodeHeap and most promising
     * node is retrieved until heap is empty or end node found
     */
    
    private void traverseNodes() {
        while (!nodeHeap.isEmpty() ) {
            
            // check if target found
            RouteFinderNode currentNode = nodeHeap.poll();
            if (currentNode.getX() == this.endX && currentNode.getY() == this.endY) {
                break;
            }
            
            // Check adjacent Nodes from World and create new Nodes if nessecary
            ArrayList<int[]> childrenPositions = this.world.getNeighborPositions(currentNode.getPosition());

            for (int[] childPosition : childrenPositions) {
                int ChildX = childPosition[0];
                int ChildY = childPosition[1];
                int h = this.getHeuristic(ChildX, ChildY);
                int g = currentNode.getG() + this.world.getTerrain(ChildX, ChildY);
                if (this.openList[ChildX][ChildY] == null || openList[ChildX][ChildY].getG() > g) {
                    RouteFinderNode childNode = new RouteFinderNode(ChildX, ChildY, g, h, g + h, currentNode);
                    nodeHeap.add(childNode);
                    this.openList[ChildX][ChildY] = childNode;
                    
                    if (this.debugging == true) {
                        this.gameController.drawCharacter('/', ChildX, ChildY);
                    }
                    
                }
            }
            
            if (this.debugging == true) {
                this.gameController.drawCharacter('X', currentNode.getX(), currentNode.getY());
            }
            
        }
    }
    

    
    /**
     * traverse route from starting from end node (node at endX and endY).
     * Add end node to ArrayList and add every node's parent on ArrayList until
     * start node is in the ArrayList
     */
    
    private void getRoute() {
        ArrayList<RouteFinderNode> nextRoute = new ArrayList<>();
        this.route = nextRoute;
        RouteFinderNode nextAddedNode = this.openList[this.endX][this.endY];
        this.route.add(nextAddedNode);
        while (true) {
            //end if node if arrived as start node
            if (nextAddedNode.getX() == this.startX && nextAddedNode.getY() == this.startY) {
                break;
            }
            // add parent Node
            nextAddedNode = nextAddedNode.getParent();
            if (debugging == true) {
                this.gameController.drawCharacter('R', nextAddedNode.getX(), nextAddedNode.getY());
            }
            this.route.add(nextAddedNode);
        }
    }
    
    
    
    private int getHeuristic(int fromX, int fromY) {
        return this.distance.getDistance(fromX, fromY, this.endX, this.endY)*2;
    }

    private class route {

        public route() {
        }
    }

}
