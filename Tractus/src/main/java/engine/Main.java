package engine;

import helpers.PerformanceTests;





/**
 * <h1>Main</h1>
 * Only creates a new Game.
 */

public class Main {
    
    private Game game;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Game game = new Game();
        PerformanceTests test = new PerformanceTests();
        test.runTests();
        
    }
    

    
}
