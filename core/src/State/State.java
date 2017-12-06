package State;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import FarmSimulator.StartParams;

public abstract class State {
    
    protected GSM gsm;
    protected OrthographicCamera cam;
    
    protected State(GSM gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, StartParams.WIDTH, StartParams.HEIGHT);
    }
    
    public abstract void update(float dt); 
    public abstract void render(SpriteBatch sb);
    
}