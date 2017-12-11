package Entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;
import Farm.Crop;
import Farm.Plot;
import Farm.Seed;
import MapCreate.MapObjects;
import MapCreate.MapTiles;
import Util.Res;

public class Player extends MapObjects {
    
    private Plot[][] farm;
    
    private List<Crop> crops;
    private List<Seed.Type> seeds;
    private int money = 0;
    private int totalMoney;
    
    private Action action;
    private int actionRow;
    private int actionCol;
    private float actionTime;
    private float actionTimeRequired;
    private float[] actionSpeedMultipliers = {1, 1, 1, 1};
    
    private Seed.Type nextSeedType;
    private TextureRegion pixel;
    private Plot selectedPlot;
    private int row;
    private int col;
    
    
    private TextureRegion[] idle;
    private TextureRegion[] moving;
    private TextureRegion[] seeding;
    private TextureRegion[] watering;
    private TextureRegion[] fertilizing;
    
    
    public enum Action {
        FERTILIZING(2),
        WATERING(3),
        SEEDING(1),
        HARVESTING(4);
        public float timeRequired;
        Action(float timeRequired) {
            this.timeRequired = timeRequired;
        }
        
    };
    
    public Player(MapTiles mapTiles) {
        super(mapTiles);
        
        Texture tex = Res.i().getTexture("player");
        
        System.out.println("texture tex: " + tex);
        TextureRegion[][] split = TextureRegion.split(tex, 32, 32);
        idle = split[0];
        moving = split[0];
        seeding = split[0];
        fertilizing = split[0];
        watering = split[0];
        setAnimation(idle, 0, 1);
        collisionWidth = 20;
        collisionHeight = 20;
                
        
        moveSpeed = 100;
        crops = new ArrayList<Crop>();
        seeds = new ArrayList<Seed.Type>();
        
        pixel = new TextureRegion(Res.i().getTexture("pixel"));
        for (int i = 0; i < StartParams.SeedsStartWith; i++) {
            seeds.add(Seed.Type.POTATO);
        }
    }
    
    
    public boolean buySeed(Seed.Type type) {
        if (money >= type.cost) {
            money -= type.cost;
            seeds.add(type);
            return true;
        }
        return false;
    }
    
    public void addMoney(int amount) {
        money += amount;
        totalMoney += amount;
    }
    
    public int getMoney() {
        return money;
    }
    
    public int getTotalMoney() {
        return totalMoney;
    }
    
    public int getNumSeeds() {
        return seeds.size();
    }
    
    public void setFarm(Plot[][] farm) {
        this.farm = farm;
    }
    
    public void actionStarted(Action action, int actionRow, int actionCol) {
        this.action = action;
        this.actionRow = actionRow;
        this.actionCol = actionCol;
        actionTime = 0;
        actionTimeRequired = action.timeRequired;
    }
    
    public void actionFinished() {
        switch(action) {
            case SEEDING:
                farm[actionRow][actionCol].seed(new Seed(nextSeedType,
                             tileSize * ((int) (x / tileSize) + 0.5f),
                             tileSize * ((int) (y / tileSize) + 0.5f), 32, 32));
                seeds.remove(0);
                break;
            case FERTILIZING:
                farm[actionRow][actionCol].fertilize();
                break;
            case WATERING:
                farm[actionRow][actionCol].water();
                break;
            case HARVESTING:
                Crop crop = farm[actionRow][actionCol].harvest();
                if (crop != null) {
                    crops.add(crop);
                }
                break;
        }
        action = null;
    }
    
    private void getCurrentTile() {
        row = (int) (mapTiles.getNumRows() - (y / tileSize));
        col = (int) (x / tileSize);
        row -= 3;
        col -= 5;
    }
    
    
    public void seed() {
        getCurrentTile();
        if (row < 0 || row >= farm.length || col < 0 || col >= farm[0].length) {
            return;
        }
        nextSeedType = (seeds.isEmpty() || farm[row][col].hasSeed() || farm[row][col].getState() != Plot.State.NORMAL) ? null : seeds.get(0);
        if (action == null && farm[row][col].canSeed() && nextSeedType != null)
            actionStarted(Action.SEEDING, row, col);
    }
    
    public void fertilize() {
        getCurrentTile();
        if (row < 0 || row >= farm.length || col < 0 || col >= farm[0].length) {
            return;
        }
        if (action == null && farm[row][col].canFertilize()) {
            actionStarted(Action.FERTILIZING, row, col);
        }
    }
    
    public void water() {
        getCurrentTile();
        if (row < 0 || row >= farm.length || col < 0 || col >= farm[0].length) {
            return;
        }
        if (action == null && farm[row][col].canWater()) {
            actionStarted(Action.WATERING, row, col);
        }
    }
    
    public void harvest() {
        getCurrentTile();
        if (row < 0 || row >= farm.length || col < 0 || col >= farm[0].length) {
            return;
        }
        if (action == null && farm[row][col].canHarvest()) {
            actionStarted(Action.HARVESTING, row, col);
        }
    }
    
    
    
    public boolean sell() {
        if (crops.isEmpty()) {
            return false;
        }
        for (Crop crop : crops) {
            addMoney(crop.getValue());
        }
        crops.clear();
        return true;
    }
    
    public int getNumCrops() {
        return crops.size();
    }
    
    public Action getAction() {
        return action;
    }
    
    private void highlightPlot() {
        int row = (int) (mapTiles.getNumRows() - (y / tileSize));
        int col = (int) (x / tileSize);
        row -= 3;
        col -= 5;
        if (row < 0 || row >= farm.length || col < 0 || col >= farm[0].length) {
            selectedPlot = null;
            return;
        }
            selectedPlot = farm[row][col];
    }
    
    
    
    @Override
    public void update(float dt) {
        if (action != null) {
            actionTime += dt;
            if (actionTime >= actionTimeRequired) {
                actionFinished();
            }
        } else {
            if (left) {
                dx = -moveSpeed * dt;
            } else if (right) {
                dx = moveSpeed * dt;
            } else {
                dx = 0;
            }
            
            if (down) {
                dy = -moveSpeed * dt;
            } else if (up) {
                dy = moveSpeed * dt;
            } else {
                dy = 0;
            }
            
            checkTileMapCollision();
            x = xtemp;
            y = ytemp;
            
            highlightPlot();

        }
    }
    
    
    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (selectedPlot != null) {
            selectedPlot.renderHighlight(sb);
        }
    }
    
}