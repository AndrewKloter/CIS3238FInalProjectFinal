package Entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;
import MapCreate.MapObjects;
import MapCreate.MapTiles;
import Util.Res;

public class Player extends MapObjects {
    
    private Action action;
    
    //private Patch[][] farm;
    private TextureRegion pixel;
    private int row;
    private int col;
    
    private TextureRegion[] idle;
    private TextureRegion[] moving;
    private TextureRegion[] seeding;
    private TextureRegion[] watering;
    private TextureRegion[] fertilizing;
    
    public enum Action {
        FERTILIZING(),
        WATERING(),
        SEEDING(),
        HARVESTING();
        
    }
    
    public Player(MapTiles mapTiles) {
        super(mapTiles);
        Texture tex = Res.i().getTexture("player");
        TextureRegion[][] split = TextureRegion.split(tex, 32, 32);
        //setAnimation(reg, 0);
        collisionWidth = 20;
        collisionHeight = 20;
        
        moveSpeed = 100;
    }
    
    @Override
    public void update(float dt) {
        
        if (left) {
            dx = -moveSpeed * dt;
        } else if (right) {
            dx = moveSpeed * dt;
        } else {
            dx = 0;
        }
        if (down) {
            dy = -moveSpeed * dt;
        } else if (up) {
            dy = moveSpeed * dt;
        } else {
            dy = 0;
        }
        
    }
    
    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }
    
}