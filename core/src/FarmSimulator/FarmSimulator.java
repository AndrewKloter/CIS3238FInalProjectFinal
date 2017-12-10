package FarmSimulator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import State.GSM;
import State.PlayState;
import Util.Res;


public class FarmSimulator extends ApplicationAdapter {
	
    private SpriteBatch sb;
    private GSM gsm;
    private Res res;
    
	@Override
	public void create () {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            
         
            
                res = Res.i();
		res.loadTexture("tileset", "tileset_1.png");
		res.loadTexture("player", "player_1.png");
                res.loadTexture("farmtiles1", "farmtiles_1.png");
                res.loadTexture("FarmTiles", "FarmTiles.png");
		res.loadTexture("pixel", "pixel2.png");
                
                
                sb = new SpriteBatch();
		gsm = new GSM();
		gsm.push(new PlayState(gsm));
        }

            @Override
            public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
}
