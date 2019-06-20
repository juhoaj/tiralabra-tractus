/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.World;
import helpers.CustomArrayList;
import helpers.Distance;
import helpers.PairingHeap;

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
    private World world;
    private GameController gameController;
    private Distance distance;
    private boolean debugging;
    
    private boolean testPerformance;
    private boolean testPerformanceShowIndiwidualMonsters;
    private boolean testPerformanceShowHeapPerformance;
    private RouteFinderNode[][] openList;
    private CustomArrayList<RouteFinderNode> route;
    private PairingHeap<RouteFinderNode> nodeHeap;
    private int visitedNodes;
    private int routeLength;

    /**
     * Constructor which injects also gameController which is required by 
     * debugging (drawing algorithm's routefinding). Initializes Distance.
     * 
     * @param world contains and controls the map
     * @param gameController controls the game
     * @param debugging set true to draw algorithm's routefinding to terminal
     * @param testPerformance print performance of algorithms to console
     */
    public RouteFinder(World world, GameController gameController, boolean debugging, boolean testPerformance) {
        this.world = world;
        this.gameController = gameController;
        this.debugging = debugging;
        this.distance = new Distance();
        this.testPerformance = testPerformance;
        this.testPerformanceShowIndiwidualMonsters = false; // set true to print each monster's routefindeing performance to the console
        this.testPerformanceShowHeapPerformance = false; // set true to print information on Heap to console
    }
    
    /**
     * Constructor which does not allow for debugging.
     * 
     * @param world contains and controls the map
     * @param monsterList list of all monsters
     */
    public RouteFinder(World world) {
        this.world = world;
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
            // int[] ret = {2,0};
            // return ret;
            return null;
        }

        this.AStar();
        if (this.route.size() == 1) {
            RouteFinderNode lastNode = this.route.get(0);
            return lastNode.getPosition();
        }
        RouteFinderNode secondLastNode = this.route.get(this.route.size()-2);
        return secondLastNode.getPosition();

    }
    
    /**
     * A* routefinding algorithm which returns the route as ArrayList
     * containing positions in arrays {x-coordinate, y-coordinate}.
     */
    
    private void AStar() {
        
        this.visitedNodes = 0;
        this.routeLength = 0;
        RouteFinderNode[][] nextList = new RouteFinderNode[this.world.getWidth()][this.world.getHeight()];
        this.openList = nextList;
        PairingHeap nextNodeHeap = new PairingHeap<>();
        this.nodeHeap = nextNodeHeap;
        
        // create first node 
        int startNodeHeuristic = this.getHeuristic(this.startX, this.startY);
        RouteFinderNode startNode = new RouteFinderNode(this.startX, this.startY, 0, startNodeHeuristic, 0);
        nodeHeap.push(startNode);
        this.openList[this.startX][this.startY] = startNode;
        
        this.traverseNodes();
        
        this.getRoute();
        if (this.testPerformance == true && this.testPerformanceShowIndiwidualMonsters == true) {
            System.out.println("visited nodes: ");
            System.out.println(this.visitedNodes);
            System.out.println("route length");
            System.out.println(this.routeLength);
        }

        return;
    }
    
    
    /**
     * Potential nodes on route are inserted in nodeHeap and most promising
     * node is retrieved until heap is empty or end node found
     */
    
    private void traverseNodes() {
        int maxHeapSize = 1;
        int nodesAdded = 1;
        while (!nodeHeap.isEmpty() ) {
            
            if (this.testPerformanceShowHeapPerformance && nodeHeap.size() > maxHeapSize) {
                maxHeapSize = nodeHeap.size();
            }
            this.visitedNodes++;

            // check if target found
            RouteFinderNode currentNode = nodeHeap.pop();
            if (currentNode.getX() == this.endX && currentNode.getY() == this.endY) {
                break;
            }
            
            
            // Check adjacent Nodes from World and create new Nodes if nessecary
            CustomArrayList<int[]> childrenPositions = this.world.getNeighborPositions(currentNode.getPosition());

            for (int i = 0; i < childrenPositions.size() ; i++ ) {
                int ChildX = childrenPositions.get(i)[0];
                int ChildY = childrenPositions.get(i)[1];
                int h = this.getHeuristic(ChildX, ChildY);
                int g = currentNode.getG() + this.world.getTerrain(ChildX, ChildY);
                if (this.openList[ChildX][ChildY] == null || openList[ChildX][ChildY].getG() > g) {
                    RouteFinderNode childNode = new RouteFinderNode(ChildX, ChildY, g, h, g + h, currentNode);
                    nodeHeap.push(childNode);
                    nodesAdded++;
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
        
        if (this.testPerformance == true && testPerformanceShowHeapPerformance == true) {
            System.out.println("nodes added:");
            System.out.println(nodesAdded);
            System.out.println("max heap size:");
            System.out.println(maxHeapSize);
            System.out.println("nodes in heap at the end:");
            System.out.println(this.nodeHeap.size());
        }
    }

    /**
     * traverse route from starting from end node (node at endX and endY).
     * Add end node to ArrayList and add every node's parent on ArrayList until
     * start node is in the ArrayList
     */
    
    private void getRoute() {
        CustomArrayList<RouteFinderNode> nextRoute = new CustomArrayList<>();
        this.route = nextRoute;
        RouteFinderNode nextAddedNode = this.openList[this.endX][this.endY];
        this.route.add(nextAddedNode);
        while (true) {
            this.routeLength++;
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
    
    /**
     * Returns heuristic required by A*.
     */
    private int getHeuristic(int fromX, int fromY) {
        int distance = this.distance.getDistance(fromX, fromY, this.endX, this.endY);
        return distance * distance ;
    }

}
