package com.mcducky.maze;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Main implements Runnable {
    private boolean stop;
    private JFrame frame;
    private Display display;
    private BufferedImage gBuffer;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private GameContext gc;
    
    public static void main(String[] args) {
        new Main().run();
    }
    
    public Main(){
    }
    
    private void initFrame(){
        gBuffer = new BufferedImage(128, 32, BufferedImage.TYPE_INT_RGB);
        frame = new JFrame("Maze Demo");
        frame.setResizable(true);
        frame.setSize(new Dimension(1024,256));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void run() {
        initFrame();
        gc = new GameContext(frame);
        display = new Display();
        stop = false;
        while(!stop){
            try {
                Thread.sleep(1000/24);
                gc.update();
                render();
            } catch(InterruptedException e) {
            
            }
        }
    }
    
    private void render(){
        if(bufferStrategy == null){
            frame.createBufferStrategy(3);
            bufferStrategy = frame.getBufferStrategy();
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();
    
        frame.update(graphics);

        gc.draw(display);
        display.draw(gBuffer);
        
        graphics.drawImage(gBuffer, 0, 0,frame.getWidth() ,frame.getHeight(), null);
        bufferStrategy.show();
        
    }
}
