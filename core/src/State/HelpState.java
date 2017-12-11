package State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import FarmSimulator.FarmSimulator;
import Farm.Seed;
import FarmSimulator.StartParams; 
import Util.Res;

public class HelpState extends State {
    
    private BitmapFont font;
    
    public HelpState(GSM gsm) {
        super(gsm);
        font = Res.i().getFont("font");
    }
    
    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            cam.position.y -= 100 * dt;
            cam.update();
        }
        else if (Gdx.input.isKeyPressed(Keys.UP)) {
            cam.position.y += 100 * dt;
            cam.update();
        }
        else if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
            gsm.pop();
        }
    }
    
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        int height = StartParams.HEIGHT - 10;
        font.draw(sb, "Your goal is to reach $" + StartParams.MoneyToReach + " in total money.", 10, height -= 15);
        font.draw(sb, "To grow crops: Plant a seed, press 1.", 10, height -= 25);
        font.draw(sb, "Then press 2 to fertilize, and 3 to water.", 10, height -= 15);
        font.draw(sb, "Finally wait for the crops to grow.", 10, height -= 15);
        font.draw(sb, "When they're done growing, press 4 to harvest.", 10, height -= 15);
        
        font.draw(sb, "Sell your crops by walking to the stall.", 10, height -= 25);
        
        
        font.draw(sb, "Crop Info:", 10, height -= 25);
		for(Seed.Type type : Seed.types) {
			font.draw(sb, type.toString() + " - " + (float) Math.round((type.requiredTime / 30) * 100.0) / 100.0f + " days, $" + type.value + " value, $" + type.cost + " cost", 10, height -= 15);
		}
        sb.end();
    }
    
}
