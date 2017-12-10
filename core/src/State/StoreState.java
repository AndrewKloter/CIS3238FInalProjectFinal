package State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;
import Entity.Player;
import Farm.Seed;
import Util.Res;

public class StoreState extends State {
    
        private TextureRegion pixel;
	private Seed.Type[][] crops;
	
	private Player player;
	private int currentRow;
	private int currentCol;
	
	private BitmapFont fontSmall;
	private BitmapFont font;
	
	private boolean showingPopup;
	private String popupText;
	
	public StoreState(GSM gsm, Player player) {
		super(gsm);
		
		this.player = player;
		
		pixel = new TextureRegion(Res.i().getTexture("pixel"));
		crops = new Seed.Type[2][4];
		crops[0][0] = Seed.Type.POTATO;
		//crops[0][1] = Seed.Type.CORN;
		//crops[0][2] = Seed.Type.TOMATO;
		//crops[0][3] = Seed.Type.WHEAT;
		//crops[1][0] = Seed.Type.CABBAGE;
		//crops[1][1] = Seed.Type.ORANGE;
		//crops[1][2] = Seed.Type.GRAPES;
		//crops[1][3] = Seed.Type.CHERRIES;
		
		fontSmall = Res.i().getFont("fontsmall");
		font = Res.i().getFont("font");
	}
        
        @Override
        public void update(float dt) {
            
            if (showingPopup) {
                if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
                    showingPopup = false;
                }
            }
            else {
                if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
                    if (currentCol < 3) {
                        currentCol++;
                    }
                }
                if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
                    if (currentCol > 0) {
                        currentCol--;
                    }
                }
                if (Gdx.input.isKeyJustPressed(Keys.UP)) {
                    if (currentRow > 0) {
                        currentRow--;
                    }
                }
                if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
                    if (currentRow < 2) {
                        currentRow++;
                    }
                }
                
                if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
                    gsm.pop();
                }
                if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
                    boolean ok = false;
                    
                    if (currentRow == 0 && currentCol == 0) {
                        ok = player.buySeed(Seed.Type.POTATO);
                    }
                    if (ok) {
                        popupText = "Bought Item!";
                        showingPopup = true;
                    }
                    else {
                        popupText = "Not enough money :')'";
                        showingPopup = true;
                    }
                    
                }
            }
        }
        
        
        @Override
        public void render(SpriteBatch sb) {
            sb.setProjectionMatrix(cam.combined);
            sb.begin();
            sb.setColor(Color.WHITE);
            for(int row = 0; row < crops.length; row++) {
			for(int col = 0; col < crops[0].length; col++) {
				sb.draw(crops[row][col].cropImage, 100 * col + 34, 100 * (2 - row) + 40);
				font.draw(sb, "$" + crops[row][col].cost, 100 * col + (crops[row][col].cost < 100 ? 37 : 30), 100 * (2 - row) + 40);
			}
		}
            sb.end();
        }
        
        
    
}
