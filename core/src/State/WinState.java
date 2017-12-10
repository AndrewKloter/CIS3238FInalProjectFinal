package State;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Util.Res;

public class WinState extends State {
    
    private BitmapFont font;
    
    public WinState(GSM gsm) {
        super(gsm);
        
        font = Res.i().getFont("font");
    }
    
    @Override
    public void update(float dt) {
        
    }
    
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        font.draw(sb, "YOU WIN!!", 154, 180);
        sb.end();
    }
    
}
