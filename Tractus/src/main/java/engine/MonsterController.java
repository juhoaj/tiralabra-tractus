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
 *
 * @author juhojuutilainen
 */
public class MonsterController {
    
    private ArrayList<Creature> monsterlist;
    private World world;
    private GameController gameController;
    
    Random random = new Random();

    public MonsterController(ArrayList<Creature> monsterlist, World world, GameController gameController) {
        this.monsterlist = monsterlist;
        this.world = world;
        this.gameController = gameController;
    }
    
    public void moveTo(int newX, int newY, Creature creature) {
        creature.setX(newX);
        creature.setY(newY);
    }
    
    public void createMonsters(int amount) {

        for (int i = 0 ; i < amount ; i++) {

            Creature creature = new Creature();
            while (true) {
                int startPositionX = this.random.nextInt(this.world.getWidth() / 2);
                int startPositionY = this.random.nextInt(this.world.getHeight() / 2);
                if ( this.world.getTerrain(startPositionX, startPositionY) == 0 ) {
                    this.moveTo(startPositionX, startPositionY, creature);
                    this.monsterlist.add(creature);
                    System.out.println( "monsteri " + startPositionX + "," + startPositionY );
                    break;
                }
            }
        }        
    }
    
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
