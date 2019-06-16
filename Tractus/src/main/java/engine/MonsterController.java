/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Creature;
import domain.World;
import helpers.CustomArrayList;
import helpers.Distance;

import java.util.Random;

/**
 * Responsible for making monsters' actions.
 */
public class MonsterController {
    
    private CustomArrayList<Creature> monsterlist;
    private World world;
    private GameController gameController;
    private RouteFinder routeFinder;
    private Random random = new Random();
    private Distance distance;
    private boolean testPerformance;

    /**
     * Constructor which also creates a instance of RouteFinder that is used
     * to determine monster's next move.
     * 
     * @param monsterlist list of all monsters
     * @param world contains and controls the map
     * @param gameController controls the game
     * @param debugging print debugging information to terminal
     * @param testPerformance print performance of algorithms to console
     */ 
    public MonsterController(CustomArrayList<Creature> monsterlist, World world, GameController gameController, boolean debugging, boolean testPerformance) {
        this.testPerformance = testPerformance;
        this.monsterlist = monsterlist;
        this.world = world;
        this.gameController = gameController;
        if (debugging == true || this.testPerformance == true ) {
            this.routeFinder = new RouteFinder(this.world, this.gameController, debugging, this.testPerformance);
        } else {
            this.routeFinder = new RouteFinder(this.world);
        }
        this.distance = new Distance();

    }

    /**
     * Set's injected monster's position.
     * 
     * @param newX X-coordinate where the monster is moved to
     * @param newY Y-coordinate where the monster is moved to
     * @param monster monster that is moved
     */
    public void moveTo(int newX, int newY, Creature monster) {
        monster.setX(newX);
        monster.setY(newY);
    }
    
    /**
     * Creates 'amount' number of monsters and places those on the map.
     * @param amount of monsters
     */
    public void createMonsters(int amount) {
        int playerX = this.gameController.getPlayerPosition()[0];
        int playerY = this.gameController.getPlayerPosition()[1];
        int smallerWorldDimension = Math.min(this.world.getWidth(), this.world.getHeight());
        int minimumDistance = Math.min(smallerWorldDimension * 10 / 3, 100);

        for (int i = 0 ; i < amount ; i++) {
            Creature creature = new Creature();
            while (true) {
                int startPositionX = this.random.nextInt(this.world.getWidth() );
                int startPositionY = this.random.nextInt(this.world.getHeight() );
                if ( this.world.getTerrain(startPositionX, startPositionY) == 1 &&
                     distance.getDistance(playerX, playerY, startPositionX, startPositionY) > minimumDistance
                    ) {
                    this.moveTo(startPositionX, startPositionY, creature);
                    this.monsterlist.add(creature);
                    break;
                }
            }
        }        
    }
    /**
     * Adds monster to the world.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void instertMonster(int x, int y) {
        Creature monster = new Creature();
        monster.setX(x);
        monster.setY(y);
        this.monsterlist.add(monster);
    }
    
    /**
     * Removes all monsters. Used to remove all monsters before new game.
     */
    public void dumpMonsters() {
        this.monsterlist.clear();
    }
    
    /**
     * Loops monsters. Calls RouteFinder for each monster to get their action.
     * Sets new monster position if it's terrain is corridor. Sets terrain to
     * corridor if it is a wall.
     * 
     * @return true after ready
     */
    public boolean monsterActions() {
        for ( int i = 0 ; i < this.monsterlist.size() ; i++) {
            int[] monsterPosition = monsterlist.get(i).getPosition();
            int[] playerPosition = this.gameController.getPlayerPosition();
            int newPosition[] = this.routeFinder.getNextMove(monsterPosition, playerPosition);
            if (this.world.getTerrain(newPosition[0], newPosition[1]) == 1) {
                monsterlist.get(i).setPosition(newPosition);
            } else {
                this.world.setTerrain(newPosition[0], newPosition[1], 1);
            }
            
        }
        return true;
    }
    
    /**
     * Get all monster positions. Return format is array that contains array
     * for each monster's position. Returns null if no monsters present.
     * 
     * @return {{x-coordinate,y-coordinate},{x-coordinate,y-coordinate}}
     */
    public int[][] getMonsterPositions() {
        if ( this.monsterlist.isEmpty()) {
            return null;
        }
        int[][] positions = new int[this.monsterlist.size()][2];
        for ( int i = 0 ; i < this.monsterlist.size() ; i++ ) {
            positions[i][0]=this.monsterlist.get(i).getX();
            positions[i][1]=this.monsterlist.get(i).getY();
        }
        return positions;
    }
    

    /**
     * Constructor which is only used in testing
     */ 
    public MonsterController(CustomArrayList<Creature> monsterlist, World world, GameController gameController, RouteFinder routeFinder, Distance distance) {
        this.monsterlist = monsterlist;
        this.world = world;
        this.gameController = gameController;
        this.routeFinder = routeFinder;
        this.distance = distance;
    }
    
    
}
