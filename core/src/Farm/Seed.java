package Farm;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Util.Res;

public class Seed {

    
    public static Type[] types = Type.values();
    
    
    public enum Type {

        POTATO(new TextureRegion(Res.i().getTexture("farmtiles1"), 96, 0, 32, 32),
                new TextureRegion(Res.i().getTexture("farmtiles1"), 0, 32, 32, 32),
                10,
                14,
                10),
        CORN(new TextureRegion(Res.i().getTexture("farmtiles1"), 96, 0, 32, 32),
                new TextureRegion(Res.i().getTexture("farmtiles1"), 32, 32, 32, 32),
                25,
                18,
                12),
        TOMATO(new TextureRegion(Res.i().getTexture("farmtiles1"), 96, 0, 32, 32),
                new TextureRegion(Res.i().getTexture("farmtiles1"), 64, 32, 32, 32),
                20,
                24,
                15),
        WHEAT(new TextureRegion(Res.i().getTexture("farmtiles1"), 96, 0, 32, 32),
                new TextureRegion(Res.i().getTexture("farmtiles1"), 96, 32, 32, 32),
                22,
                35,
                20),;
        
        
        public TextureRegion sprite;
        public TextureRegion cropImage;
        public float requiredTime;
        public int value;
        public int cost;
        
        private Type(TextureRegion sprite, TextureRegion cropImage, float requiredTime, int value, int cost) {
            this.sprite = sprite;
            this.cropImage = cropImage;
            this.requiredTime = requiredTime;
            this.value = value;
            this.cost = cost;
        }
    };
    
    
    private Type type;
    private Crop crop;
    private float x;//, y, w, h;
    private float y;
    private float w;
    private float h;
    
    private boolean seeded;
    private boolean watered;
    private float time;
    private float requiredTime;
    private boolean grown;
    
    private TextureRegion sprite;
    private TextureRegion pixel;
    
    public Seed(Type type, float x, float y, float w, float h) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        sprite = type.sprite;
        requiredTime = type.requiredTime;
        crop = new Crop(this);
        pixel = new TextureRegion(Res.i().getTexture("pixel"));
    }


    public float getx() {
        return x;
    }
    
    public float gety() {
        return y;
    }
    
    public float getw() {
        return w;
    }
    
    public float geth() {
        return h;
    }
    
    public Type getType() {
        return type;
    }
    
    public Crop getCrop() {
        return crop;
    }
    
    public boolean isGrown() {
        return grown;
    }
    
    public void setSeeded() {
        seeded = true;
    }
    
    public void setWatered() {
        watered = true;
    }

    
    
    public void update(float dt) {
        if (watered) {
            time += dt;
            if (time >= requiredTime) {
                grown = true;
            }
        }
    }
    
    public void render(SpriteBatch sb) {
        sb.draw(sprite, x - w / 2, y - h / 2);
        
        if (watered) {
            Color c = sb.getColor();
            sb.draw(pixel, x - w / 2, y + h / 2, w * time / requiredTime, 3);
            sb.setColor(Color.BLACK);
            sb.draw(pixel, x - w / 2, y + h / 2, w, 1);
            sb.draw(pixel, x - w / 2, y + h / 2 + 3, w, 1);
            sb.draw(pixel, x - w / 2, y + h / 2, 1, 4);
            sb.draw(pixel, x - w / 2, y + h / 2, 1, 4);
            sb.setColor(c);
        }
    
    }
    
    
}
