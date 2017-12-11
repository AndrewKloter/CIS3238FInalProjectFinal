package State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;
import Entity.Player;
//import Entity.Player.Action;
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
		crops = new Seed.Type[2][2];
		crops[0][0] = Seed.Type.POTATO;
		crops[0][1] = Seed.Type.CORN;
		crops[1][1] = Seed.Type.TOMATO;
		crops[1][0] = Seed.Type.WHEAT;
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
                    if (currentCol < 1) {
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
                    if (currentRow < 1) {
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
                    } else if (currentRow == 0 && currentCol == 1) {
                        ok = player.buySeed(Seed.Type.CORN);
                    } else if (currentRow == 1 && currentCol == 1) {
                        ok = player.buySeed(Seed.Type.TOMATO);
                    } else if (currentRow == 1 && currentCol == 0) {
                        ok = player.buySeed(Seed.Type.WHEAT);
                    }
                    
                    if (ok) {
                        popupText = "Bought Item!";
                        showingPopup = true;
                    }
                    else {
                        popupText = "Not enough money";
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
            
            sb.draw(pixel, 100 * currentCol + 20, 100 * (3 - currentRow) - 20, 60, 1);
            sb.draw(pixel, 100 * currentCol + 20, 100 * (2 - currentRow) + 20, 60, 1);
            sb.draw(pixel, 100 * currentCol + 20, 100 * (2 - currentRow) + 20, 1, 60);
            sb.draw(pixel, 100 * (currentCol + 1) - 20, 100 * (2 - currentRow) + 20, 1, 60);

            
            if (showingPopup) {
                sb.setColor(Color.BLACK);
                sb.draw(pixel, StartParams.WIDTH / 2 - 100, StartParams.HEIGHT / 2 - 25, 200, 50);
                sb.setColor(Color.WHITE);
			sb.draw(pixel, StartParams.WIDTH / 2 - 100, StartParams.HEIGHT / 2 - 25, 200, 1);
			sb.draw(pixel, StartParams.WIDTH / 2 - 100, StartParams.HEIGHT / 2 - 25, 1, 50);
			sb.draw(pixel, StartParams.WIDTH / 2 - 100, StartParams.HEIGHT / 2 + 25, 200, 1);
			sb.draw(pixel, StartParams.WIDTH / 2 + 100, StartParams.HEIGHT / 2 - 25, 1, 50);
			if(popupText.equals("Not enough money!")) {
				font.draw(sb, popupText, 127, 154);
			}
			else {
				font.draw(sb, popupText, 147, 154);
			}
            }
            font.draw(sb, "Money: $" + player.getMoney(), 10, StartParams.HEIGHT - 10);
            font.draw(sb, "Enter to buy. Escape to return to game.", 30, 20);
                
            sb.end();
        }
        
        
    
}
