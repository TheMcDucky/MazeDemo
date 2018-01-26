package com.mcducky.maze;
import javax.swing.*;
import java.util.*;
public class GameContext extends GameObject implements Drawable  {
    public JFrame frame;

    private Camera cam;
    double[][] zBuffer;
    ArrayList<Wall> walls;

    public GameContext(JFrame frame){
        this.frame = frame;
        zBuffer = new double[128][32];
        walls = new ArrayList<Wall>();
        cam = new Camera(frame);
        walls.add(new Wall(new Vector2(0, 1), new Vector2(-50, 10)));
    }

    public void update(){
       cam.update();
    }

    public void draw(Display display){
        for (int i = 0; i < walls.size(); i++) {
            Wall w = walls.get(i);
            Vector2 camp = cam.getPosition();

            display.pixels[(int)(w.a.getX() + 64 - camp.getX())]
                    [(int)(w.a.getY() + 16 - camp.getY())] = true;
            display.pixels[(int)(w.b.getX() + 64 - camp.getX())]
                    [(int)(w.b.getY() + 16 - camp.getY())] = true;
        }
    }
}
