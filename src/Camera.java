import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class Camera implements GameObject {
    private Vector2 position = new Vector2();
    public double zNear = 0.1;
    public double zFar = -1;
    public double focalLength = 100f;
    private double rotation = 0;

    public Camera() {
    
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
        while(this.rotation < 0)
            this.rotation += Math.PI * 2;
        while(this.rotation >= Math.PI * 2)
            this.rotation -= Math.PI * 2;
    }

    public double getRotation(){
        return rotation;
    }

    public void translate(Vector2 offset){
        position.add(offset);
    }

    public Vector2 getPosition(){
        return position;
    }

    public void update(double delta) {
        
        if(Main.keys[KeyEvent.VK_RIGHT])
            setRotation(rotation - delta * 2);
        if(Main.keys[KeyEvent.VK_LEFT])
            setRotation(rotation + delta * 2);
        Vector2 heading = new Vector2(0, 1);
        heading.rotate(rotation);
        heading.scale(delta * 10);
        if(Main.keys[KeyEvent.VK_UP])
            position.add(heading);
        if(Main.keys[KeyEvent.VK_DOWN]){
            position.subtract(heading);
        }
    }
}
