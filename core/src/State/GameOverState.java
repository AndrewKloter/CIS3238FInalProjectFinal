package State;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Util.Res;

public class GameOverState extends State {
    
    private BitmapFont font;
    
    public GameOverState(GSM gsm) {
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
        font.draw(sb, "Game Over!", 170, 200);
        font.draw(sb, "You Lose!", 170, 180);
        font.draw(sb, "Better luck next time!", 120, 160);
        sb.end();
    }
    
}
