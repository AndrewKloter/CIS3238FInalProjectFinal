package State;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;
import Entity.Player;
import Farm.Plot;
import MapCreate.MapTiles;
import Util.Res;
import Util.BoundCamera;

public class PlayState extends State {
    
    private MapTiles mapTiles;
    private BoundCamera cam;
    private Player player;
    private Plot[][] farm;
    private float globalTime;
    
    
    public PlayState(GSM gsm) {
        super(gsm);
        
        mapTiles = new MapTiles(32);
        Texture tiles = Res.i().getTexture("tileset");
        TextureRegion[][] tileset = TextureRegion.split(tiles, 32, 32);
	mapTiles.loadTileset(tileset);
        mapTiles.loadMap("test.tme");
        
        
        cam = new BoundCamera();
	cam.setToOrtho(false, StartParams.WIDTH, StartParams.HEIGHT);
	cam.setBounds(0, 0, mapTiles.getWidth(), mapTiles.getHeight());
        System.out.println("width and height: " + mapTiles.getWidth() + " " + mapTiles.getHeight());
        
        player = new Player(mapTiles);
        //player.setPosition(100, 100);
	player.setPosition(mapTiles.getWidth() / 2, mapTiles.getHeight() / 2 - 64);
        
        
        farm = new Plot[4][10];
            for(int row = 0; row < farm.length; row++) {
		for(int col = 0; col < farm[0].length; col++) {
                    farm[row][col] = new Plot(mapTiles, row + 3, col + 5);
			}
		}
        
        player.setFarm(farm);
    }
    
    
    
    @Override
    public void update(float dt) {
        
        //globalTime += 2880 * dt;
      
       player.setLeft(Gdx.input.isKeyPressed(Keys.LEFT));
		player.setRight(Gdx.input.isKeyPressed(Keys.RIGHT));
		player.setUp(Gdx.input.isKeyPressed(Keys.UP));
		player.setDown(Gdx.input.isKeyPressed(Keys.DOWN));
		player.update(dt);
		
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			player.seed();      
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			player.fertilize(); 
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
			player.water();
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
			player.harvest();
		}
                
                if(Gdx.input.isKeyJustPressed(Keys.H)) {
			gsm.push(new HelpState(gsm));
			return;
		}
                
		if(Gdx.input.isKeyJustPressed(Keys.S) && player.getAction() == null) {
			gsm.push(new StoreState(gsm, player));
			return;
		}
                
        
        
        /*
        if (player.getTotalMoney() > = StartParams.MoneyToReach) {
            gsm.set(new WinState(gsm));
        }
        */
        
                cam.position.set(player.getx(), player.gety(), 0);
		cam.update();
		
		for(int row = 0; row < farm.length; row++) {
			for(int col = 0; col < farm[0].length; col++) {
				farm[row][col].update(dt);
			}
		}
        
        
    }
    
    
    
    @Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		mapTiles.render(sb, cam);
		for(int row = 0; row < farm.length; row++) {
			for(int col = 0; col < farm[0].length; col++) {
				farm[row][col].render(sb);
			}
		}
		player.render(sb);
		//truck.render(sb);
		sb.setProjectionMatrix(super.cam.combined);
		//hud.render(sb);
		sb.end();
	}
    
    /*
    @Override
    public void render(SpriteBatch sb) {
       sb.setProjectionMatrix(cam.combined);
       sb.begin();
        //System.out.println("farm.length" + farm.length);
       mapTiles.render(sb, cam);       
         for (int row = 0; row < farm.length; row++) {
            for (int col = 0; col < farm[0].length; col++) {
                farm[row][col].render(sb);
            }
        }
               
         player.render(sb);
         //sb.setProjectionMatrix(super.cam.combined);
         sb.end();
    }
    */
    
}