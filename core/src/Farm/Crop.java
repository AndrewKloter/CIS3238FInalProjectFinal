package Farm;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Crop {
 
    private TextureRegion sprite;
    
    private int value;
    private float x;
    private float y;
    private float w;
    private float h;
    
    public Crop(Seed seed) {
        sprite = seed.getType().cropImage;
        value = seed.getType().value;
        x = seed.getx();
        y = seed.gety();
        w = seed.getw();
        h = seed.geth();
    }
    
    public int getValue() {
        return value;
    }
    
    public void render(SpriteBatch sb) {
        sb.draw(sprite, x - w / 2, y - h / 2);
    }
}
