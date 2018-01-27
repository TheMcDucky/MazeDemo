import javax.swing.*;
import java.util.*;
public class GameContext implements Drawable, GameObject  {
    public JFrame frame;
    private double eyeHeight = 1;
    private double wallHeight = 3;
    private Camera cam;
    double[][] zBuffer;
    ArrayList<Wall> walls;

    public GameContext(JFrame frame){
        this.frame = frame;
        zBuffer = new double[128][32];
        walls = new ArrayList<Wall>();
        cam = new Camera();
        walls.add(new Wall(new Vector2(-1, 10), new Vector2(1, 10)));
    }

    public void update(double deltaTime){
       cam.update(deltaTime);
    }

    public void drawWall(Display display, Wall wall){
        Vector2 camTransformA = ProjectionUtil.cameraTransform(wall.a, cam);
        Vector2 camTransformB = ProjectionUtil.cameraTransform(wall.b, cam);
    
        double a = ProjectionUtil.project(camTransformA, cam);
        double b = ProjectionUtil.project(camTransformB, cam);
        
        if (camTransformA.getY() <= 0) {
            if (camTransformB.getY() <= 0)
                return;
            a = camTransformA.getX() * Double.MAX_VALUE;
        }
    
        if (camTransformB.getY() <= 0) {
            if (camTransformA.getY() <= 0)
                return;
            b = camTransformB.getX() * Double.MAX_VALUE;
        }
        
        double aFloor = ProjectionUtil.project(new Vector2(eyeHeight, camTransformA.getLength()), cam);
        double bFloor = ProjectionUtil.project(new Vector2(eyeHeight, camTransformB.getLength()), cam);
        double aCeil = ProjectionUtil.project(new Vector2(eyeHeight - wallHeight, camTransformA.getLength()), cam);
        double bCeil = ProjectionUtil.project(new Vector2(eyeHeight - wallHeight, camTransformB.getLength()), cam);
    
        display.drawLine(new Vector2(a + 64, aFloor + 16), new Vector2(a + 64, aCeil + 16));
        display.drawLine(new Vector2(b + 64, bFloor + 16), new Vector2(b + 64, bCeil + 16));
        display.drawLine(new Vector2(a + 64, aCeil + 16), new Vector2(b + 64, bCeil + 16));
        display.drawLine(new Vector2(a + 64, aFloor + 16), new Vector2(b + 64, bFloor + 16));
    }
    
    public void draw(Display display) {
        for (int i = 0; i < walls.size(); i++) {
            drawWall(display, walls.get(i));
        }
    }
}
