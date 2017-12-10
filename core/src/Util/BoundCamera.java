package Util;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class BoundCamera extends OrthographicCamera {
    
    //private float xmin, xmax, ymin, ymax;
    private float xmin;
    private float ymin;
    private float xmax;
    private float ymax;
    
    public void setBounds(float xmin, float ymin, float xmax, float ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }
    
    @Override
    public void update() {
        checkBounds();
        super.update();
    }
    
    
    private void checkBounds() {
        if (position.x < xmin + viewportWidth / 2) {
            position.x = xmin + viewportWidth / 2;
        }
        
         if (position.x > xmax - viewportWidth / 2) {
             position.x = xmax - viewportWidth / 2;
        }
         
         if (position.y < ymin + viewportWidth / 2) {
             position.y = ymin + viewportWidth / 2;
        }
         
         if (position.y > ymax + viewportWidth / 2) {
             position.y = ymax + viewportWidth / 2;
        }
        
    }
    
}
