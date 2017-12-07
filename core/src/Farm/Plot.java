package Farm;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Util.Res;
import MapCreate.MapTiles;

public class Plot {
    
    public enum State {
        NORMAL(new TextureRegion(Res.i().getTexture("FarmTiles"), 0, 0, 32, 32)),
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
        //pixel = new TextureRegion(Res.i().getTexture("pixel"));
    }
    
    
}
