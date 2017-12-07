package MapCreate;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Andrew
 */
public abstract class MapObjects {
    
    //tile map
    protected MapTiles mapTiles;
    protected int tileSize;
    
    //positioning
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;
    
    //dimensions
    protected int width;
    protected int height;
    
    //collision paramaters
    protected int collisionWidth;
    protected int collisionHeight;

    //collision
    protected int currRow;
    protected int currCol;
    protected float xdest;
    protected float ydest;
    protected float xtemp;
    protected float ytemp;
    protected int leftTile;
    protected int rightTile;
    protected int topTile;
    protected int bottomTile;
    protected boolean topColl;
    protected boolean bottomColl;
    protected boolean leftColl;
    protected boolean rightColl;
    
    /*
    //animation
    protected Animation animation;
    protected int currAction;
    protected int prevAction;
    */
    
    //movement
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    
    //movement details
    protected float moveSpeed;
    protected float maxSpeed;
    protected float stopSpeed;
    
    
    //constructor
    public MapObjects(MapTiles mt) {
        mapTiles = mt;
        tileSize = mt.getTileSize();
        //animation = new Animation();
        
    }
    
    
      public Rectangle getRectangle() {
        return new Rectangle( (int)(x - collisionWidth / 2),
                              (int)(y - collisionHeight / 2),
                               collisionWidth, 
                               collisionHeight
                            );
    }
      
    public boolean intersects(MapObjects o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }
    
    public boolean intersects(Rectangle r) {
        return getRectangle().intersects(r);
    }
    
    public boolean contains(int x, int y) {
        return getRectangle().contains(x, y);
    }
    
    public boolean contains(MapObjects o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.contains(r2);
    }
    
    public boolean contains(Rectangle r) {
        return getRectangle().contains(r);
    }
    
  private void CalcCollision(double x, double y) {
      topColl = bottomColl = leftColl = rightColl = false;
      int yt = (int) (y + collisionHeight / 2 - 1);
      int yb = (int) (y - collisionHeight / 2);
      int xl = (int) (x - collisionWidth / 2);
      int xr = (int) (x + collisionWidth / 2 - 1);
      
      topTile = yt / tileSize;
      bottomTile = yb / tileSize;
      leftTile = xl / tileSize;
      rightTile = xr / tileSize;
      
      if (topTile < 0 || bottomTile >= mapTiles.getNumRows() || leftTile < 0 || rightTile >= mapTiles.getNumCols()) {
          return;
      }
      
      for (int i = 0; i < rightTile - leftTile + 1; i++) {
          topColl |= mapTiles.getType(topTile, leftTile + i) == mapTiles.BLOCKED;
          bottomColl |= mapTiles.getType(bottomTile, leftTile + i) == mapTiles.BLOCKED;
      }
      for (int i = 0; i < topTile - bottomTile + 1; i++) {
          leftColl |= mapTiles.getType(bottomTile + i, leftTile) == mapTiles.BLOCKED;
          rightColl |= mapTiles.getType(bottomTile + i, rightTile) == mapTiles.BLOCKED;
      }
  }
  
  
  //protected boolean checkTileMapCollision() {
  // }
  
  
  public int getx() {return (int)x;}
  public int gety() {return (int)y;}
  public double getdx() {return dx;}
  public double getdy() {return dy;}
  public int getWidth() {return width;}
  public int getHeight() {return height;}
  public int getCollisionWidth() {return collisionWidth;}
  public int getCollisionHeight() {return collisionHeight;}
  public int getRow() {return (int) (y / mapTiles.getTileSize());}
  public int getCol() {return (int) (x / mapTiles.getTileSize());}
  
  public void setPosistion(float x, float y) {
      this.x = x;
      this.y = y;
  }
  
  public void setVector(float dx, float dy) {
      this.dx = dx;
      this.dy = dy;
  }
  
  public void setCollision(int w, int h) {
      collisionWidth = w;
      collisionHeight = h;
  }
  
  public void setUp(boolean b) {up = b;}
  public void setDown(boolean b) {down = b;}
  public void setLeft(boolean b) {left = b;}
  public void setRight(boolean b) {right = b;}
  
  public abstract void update(float dt);
  
  public void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE);
      //sb.draw(animation.getImage(), (int) (x - width / 2), (int) (y - height / 2), width, height);
  }
    
    
    
    
}
