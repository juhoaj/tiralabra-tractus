/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {
    

    @Test
    public void GameInitialized() {
        Game game = new Game();
        assertNotNull(game);
    }
}
