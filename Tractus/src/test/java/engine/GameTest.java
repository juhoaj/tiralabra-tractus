/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author juhojuutilainen
 */
public class GameTest {
    

    @Test
    public void GameInitialized() {
        Game game = new Game(false,false);
        assertNotNull(game);
    }
}
