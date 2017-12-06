/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import org.junit.Test; 
import static org.junit.Assert.*;
import org.junit.Before;

import State.PlayState;
import MapCreate.MapTiles;
/**
 *
 * @author Andrew
 */
public class Tests {
    PlayState ps;
    MapTiles mt;
    
    
    @Before
    
    
    @Test
    public void checkMapTilesFromPlayState() {
        //mt.getTileSize();
        assertEquals("Should be 32", 32, mt.getTileSize());
    }
}
