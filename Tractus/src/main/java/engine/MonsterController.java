/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import domain.Command;
import domain.Creature;
import domain.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Responsible for determing monsters action. Called by GameController.
 * 
 */
public class MonsterController {
    
    private ArrayList<Creature> monsterlist;
    private World world;
    private GameController gameController;
    private RouteFinder routeFinder;
    private Random random = new Random();

    /**
     * Constructor which also creates a instance of RouteFinder.
     * @param monsterlist list of all monsters
     * @param world contains and controls the map
     * @param gameController controls the game
     */ 
    public MonsterController(ArrayList<Creature> monsterlist, World world, GameController gameController) {
        this.monsterlist = monsterlist;
        this.world = world;
        this.gameController = gameController;
        this.routeFinder = new RouteFinder(this.world);
    }
    
    /**
     *
     * @param newX
     * @param newY
     * @param creature
     */
    public void moveTo(int newX, int newY, Creature creature) {
        creature.setX(newX);
        creature.setY(newY);
    }
    
    /**
     * Creates 'amount' number of monsters and places those on the map.
     * @param amount
     */
    public void createMonsters(int amount) {

        for (int i = 0 ; i < amount ; i++) {
            Creature creature = new Creature();
            while (true) {
                int startPositionX = this.random.nextInt(this.world.getWidth() / 2);
                int startPositionY = this.random.nextInt(this.world.getHeight() / 2);
                if ( this.world.getTerrain(startPositionX, startPositionY) == 1 ) {
                    this.moveTo(startPositionX, startPositionY, creature);
                    this.monsterlist.add(creature);
                    break;
                }
            }
        }        
    }
    
    /**
     * Removes all monsters.
     */
    public void dumpMonsters() {
        this.monsterlist.clear();
    }
    
    /**
     * Loops monsters. Calls RouteFinder for each monster to get their action.
     * Sets new monster position if it's terrain is corridor. Sets terrain to
     * corridor if it is a wall.
     */
    public void monsterActions() {
        for ( Creature monster : this.monsterlist ) {
            int newPosition[] = this.routeFinder.getNextMove(monster.getPosition(), this.gameController.getPlayerPosition());
            if (this.world.getTerrain(newPosition[0], newPosition[1]) == 1) {
                monster.setPosition(newPosition);
            } else {
                this.world.setTerrain(newPosition[0], newPosition[1], 1);
            }
        }
    }
    
    /**
     * Get all monster positions. Return format is array that contains array
     * for each monster's position.
     * 
     * @return {{x-coordinate,y-coordinate},{x-coordinate,y-coordinate}}
     */
    public int[][] getMonsterPositions() {
        if ( this.monsterlist.isEmpty()) {
            return new int[0][0];
        }
        int[][] positions = new int[this.monsterlist.size()][2];
        for ( int i = 0 ; i < this.monsterlist.size() ; i++ ) {
            positions[i][0]=this.monsterlist.get(i).getX();
            positions[i][1]=this.monsterlist.get(i).getY();
        }
        return positions;
    }
    
}
