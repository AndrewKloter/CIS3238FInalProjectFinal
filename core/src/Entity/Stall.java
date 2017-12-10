package Entity;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import MapCreate.MapObjects;
import MapCreate.MapTiles;
import Util.Res;

public class Stall extends MapObjects {
    
    private BitmapFont font;
    
    private float time = 2;
    private float timer;
    private boolean showing; 
    
    public Stall(MapTiles mapTiles) {
        super(mapTiles);
        TextureRegion[] reg = new TextureRegion[1];
        reg[0] = new TextureRegion(Res.i().getTexture("stall"));
        setAnimation(reg, 0, 1);
        collisionWidth = width;
        collisionHeight = height;
        font = Res.i().getFont("fontLarge");
    }
    
    public void setShowing() {
        showing = true;
    }
    
    @Override
    public void update(float dt) {
        if (showing) {
            timer+= dt;
            if (timer >= time) {
                timer = 0;
                showing = false;
            }
        }
    }
    
    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }
    
}
