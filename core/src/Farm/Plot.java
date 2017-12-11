package Farm;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Util.Res;
import MapCreate.MapTiles;

public class Plot {
    
    public enum State {
        
        NORMAL(new TextureRegion(Res.i().getTexture("FarmTiles"), 0, 0, 32, 32)),
        SEEDED(new TextureRegion(Res.i().getTexture("FarmTiles"), 32, 0, 32, 32)),
        FERTILIZED(new TextureRegion(Res.i().getTexture("FarmTiles"), 64, 0, 32, 32)),
        WATERED(new TextureRegion(Res.i().getTexture("FarmTiles"), 96, 0, 32, 32));
        
        public TextureRegion sprite;
        private State(TextureRegion sprite) {
            this.sprite = sprite;
        }
    };
    
    private State state;
    
    private float x, y, w, h;
    private Seed seed;
    private Crop crop;
    private TextureRegion sprite;
    private TextureRegion pixel;
    
    public Plot(MapTiles mapTiles, int row, int col) {
        state = State.NORMAL;
        int tileSize = mapTiles.getTileSize();
        x = tileSize * (col + 0.5f);
        y = tileSize * (mapTiles.getNumRows() - 1 - row + 0.5f);
        w = h = tileSize;
        sprite = state.sprite;
        pixel = new TextureRegion(Res.i().getTexture("pixel"));
    }
    
    public boolean canSeed() {
        return state == State.NORMAL && crop == null && seed == null;
    }
    
    public boolean seed(Seed seed) {
        if (canSeed()) {
            this.seed = seed;
            if (state == State.NORMAL) {
                state = State.SEEDED;
                sprite = state.sprite;
                seed.setSeeded();
            }
            return true;
        }
        return false;
    }
        
    
    public boolean canFertilize() {
        return state == State.SEEDED && crop == null;
    }
    
    public void fertilize() {
        if (canFertilize()) {
            state = State.FERTILIZED;
            sprite = state.sprite;
        }
    }
    
    public boolean canWater() {
        return state == State.FERTILIZED && crop == null;
    }
    
    public void water() {
        if (canWater()) {
            state = State.WATERED;
            sprite = state.sprite;
            if (seed != null) {
                seed.setWatered();
            }
        }
    }
    
    
    
    public State getState() {
        return state;
    }
    
    public boolean hasSeed() {
        return seed != null;
    }
    
    
    
    public boolean canCrop() {
        return seed != null;
    }
    
    private void crop() {
        if (canCrop()) {
            state = State.NORMAL;
            sprite = state.sprite;
            crop = seed.getCrop();
            seed = null;
        }
    }
    
    public boolean canHarvest() {
        return crop != null;
    }
    
    public Crop harvest() {
        Crop ret = null;
        if (canHarvest()) {
            sprite = state.sprite;
            ret = crop;
            crop = null;
        }
        return ret;
    }
    
    
    
    public void update(float dt) {
        if (seed != null) {
            seed.update(dt);
            if (seed.isGrown()) {
                crop();
            }
        }
    }
   
 
    public void render(SpriteBatch sb) {
        sb.draw(sprite, x-w / 2, y-h / 2);
        
       if (seed != null) {
            seed.render(sb);
        }
       if (crop != null) {
           crop.render(sb);
       }
                
    }
    
    public void renderHighlight(SpriteBatch sb) {
		sb.setColor(Color.BLACK);
		sb.draw(pixel, x - w / 2, y - h / 2, w, 1);
		sb.draw(pixel, x - w / 2, y - h / 2, 1, h);
		sb.draw(pixel, x - w / 2, y + h / 2, w, 1);
		sb.draw(pixel, x + w / 2, y - h / 2, 1, h);
	}
    
}
