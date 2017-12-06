package State;


import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;
import Entity.Player;
import MapCreate.MapTiles;
import Util.Res;

public class PlayState extends State {
    
    private MapTiles mapTiles;
    //private BoundCamera cam;
    private Player player;
    //private Patch[][] farm;
    
    
    public PlayState(GSM gsm) {
        super(gsm);
        
        mapTiles = new MapTiles(32);
        
       // player = new Player(mapTiles);
        //player.setPosition(mapTiles.getWidth() / 2, mapTiles.getHeight() / 2 - 64);
        
        //player.setFarm(farm);
    }
    
    @Override
    public void update(float dt) {
      /*
        player.setLeft(Gdx.input.isKeyJustPressed(Keys.LEFT));
        player.setRight(Gdx.input.isKeyJustPressed(Keys.RIGHT));
        player.setUp(Gdx.input.isKeyJustPressed(Keys.UP));
        player.setDown(Gdx.input.isKeyJustPressed(Keys.DOWN));
        player.update(dt);
        
        cam.position.set(player.getx(), player.gety(), 0);
        */
        
        /*
        for (int row = 0; row < farm.length; row++) {
            for (int col = 0; col < farm[0].length; col++) {
                farm[row][col].update(dt);
            }
        }
        */
        
    }
    
    @Override
    public void render(SpriteBatch sb) {
       // sb.setProjectionMatrix(cam.combined);
       // sb.begin();
       // mapTiles.render(sb, cam);
        
        /*
         for (int row = 0; row < farm.length; row++) {
            for (int col = 0; col < farm[0].length; col++) {
                farm[row][col].render(sb);
            }
        }
                */
         //player.render(sb);
         //sb.setProjectionMatrix(super.cam.combined);
         //sb.end();
    }
    
}