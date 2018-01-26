package com.mcducky.maze;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class Camera extends GameObject implements KeyListener {
    private Vector2 position;
    private double rotation;

    public Camera(JFrame frame){

        frame.addKeyListener(this);
        position = new Vector2();
        rotation = 0;
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
        while(rotation < 0)
            this.rotation ++;
        while(rotation >= 1)
            this.rotation --;
    }

    public double getRotation(){
        return rotation;
    }

    public void translate(Vector2 offset){
        position.translate(offset);
    }

    public Vector2 getPosition(){
        return position;
    }

    public void update() {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_LEFT:
                setRotation(rotation + 1);
                break;
            case KeyEvent.VK_RIGHT:
                 setRotation(rotation - 1);
                break;
        }
       System.out.println(getRotation());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        /*switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_LEFT:
                rot--;
                break;
            case KeyEvent.VK_RIGHT:
                rot++;
                break;
        }*/
    }
}
