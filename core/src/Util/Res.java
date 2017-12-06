package Util;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.

public class Res {
    
    private static Res res = new Res();
    
    public static Res i() {
        return res;
    }
    
    private Map<String, Texture> textures;
    //private Map<String, BitmapFont> fonts;
    
    public Res() {
        textures = new HashMap<String, Texture>();
        //fonts = new HashMap<String, BitmapFont>();
    }
    
    public void loadTexture(String key, String path) {
        textures.put(key, new Texture(path));
    }
    
    public Texture getTexture(String key) {
        return textures.get(key);
    }
    
}