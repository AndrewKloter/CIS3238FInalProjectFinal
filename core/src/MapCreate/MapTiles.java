package MapCreate;

import java.io.BufferedReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;

public class MapTiles {
    //map
    private int[][] collision;
    private int[][] map;
    private int[][] depth;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    
    //tileset
    private TextureRegion[][] tileset;
    private int numTilesAcross;
    
    //tiletypes
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    public static final int LEDGE = 2;
    
    //drawing
    private int rowOffset;
    private int colOffset;
    private int NumRowsToDraw;
    private int NumColsToDraw;
    
    private static final int BACKGROUND = 0;
    private static final int FOREGROUND = 1;
    
    public MapTiles(int tileSize) {
        this.tileSize = tileSize;
        NumRowsToDraw = StartParams.HEIGHT/tileSize + 2;
        NumColsToDraw = StartParams.WIDTH/tileSize + 2;
        
        //System.out.println("tileSize: " + tileSize);
        //System.out.println("NumRowsToDraw" + NumRowsToDraw);
        //System.out.println("NumColsToDraw" + NumColsToDraw);
    }
    public void loadTileset(TextureRegion[][] tileset) {
        this.tileset = tileset;
        System.out.println("tileset: " + tileset);
        numTilesAcross = tileset[0].length;
        System.out.println("numtilesacross: " + numTilesAcross);
    }
    
    public void loadMap(String s) {
        
        try {
            
            FileHandle file = Gdx.files.internal(s);
            BufferedReader br = file.reader(4096);
            
            br.readLine();
            br.readLine();
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            
            System.out.println("numRows" + numRows);
            System.out.println("numCols" + numCols);
            
            collision = new int[numRows][numCols];
            depth = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;
            
            String delims = "\\s+";
            for (int row = numRows - 1; row >= 0; row--) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                    collision[row][col] = Integer.parseInt(tokens[col]);
                }
            }
            
            for (int row = numRows - 1; row >= 0; row--) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    collision[row][col] = Integer.parseInt(tokens[col]);
                }
            }
  
            for (int row = numRows - 1; row >= 0; row--) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    depth[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Could not load map: " + s);
            e.printStackTrace();
        }
    }
    
    public int getTileSize() {return tileSize;}
    public int getWidth() {
        //System.out.println("getWidth: " + getWidth());
        return width;
        }
    public int getHeight() {
                //System.out.println("getWidth: " + getWidth());
        return height;}
    public int getNumRows() {return numRows;}
    public int getNumCols() {return numCols;}
    
    
    public int getType(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) return 0;
        return collision[row][col];
    }
    
    public void render(SpriteBatch sb, OrthographicCamera cam) {
        sb.setColor(Color.WHITE);
        rowOffset = (int) ((cam.position.y - cam.viewportHeight / 2) / tileSize);
        colOffset = (int) ((cam.position.x - cam.viewportWidth / 2) / tileSize);
        System.out.println("cam.pos,y and viewportHeight: " + cam.position.y + " " + cam.viewportHeight);
        System.out.println("cam.pos,x and viewportWidth: " + cam.position.x + " " + cam.viewportWidth);
        System.out.println("offsets, row and col: " + rowOffset + " " + colOffset);

        for (int row = rowOffset; row < rowOffset + NumRowsToDraw; row++) {
            if (row >= numRows) break;
            for (int col = colOffset; col < colOffset + NumColsToDraw; col++) {
                if (col >= numCols) break;
                //System.out.println("row and col: " + row + " " + col);
                
                if (map[row][col] == 0) continue;
                
                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;
                
                sb.draw(tileset[r][c], col * tileSize, row * tileSize);
            }
        }
    }
    
    
}