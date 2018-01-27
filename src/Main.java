import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Main implements Runnable, KeyListener {
    public static boolean keys[];
    private boolean stop;
    private double deltaTime;
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
        keys = new boolean[KeyEvent.VK_Z];
    }
    
    private void initFrame(){
        gBuffer = new BufferedImage(128, 32, BufferedImage.TYPE_INT_RGB);
        frame = new JFrame("Maze Demo");
        frame.addKeyListener(this);
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
        long startTime;
        
        while(!stop){
            startTime = System.currentTimeMillis();
            try {
                Thread.sleep(1);
                gc.update(deltaTime);
                render();
            } catch(InterruptedException e) {
            
            }
            deltaTime = (double) (System.currentTimeMillis() - startTime) / 1000;
        }
    }
    
    private void render(){
        if(bufferStrategy == null) {
            frame.createBufferStrategy(3);
            bufferStrategy = frame.getBufferStrategy();
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();
    
        frame.update(graphics);

        gc.draw(display);
        display.draw(gBuffer);
        display.clear();
        
        graphics.drawImage(gBuffer, 0, 0,frame.getWidth(), frame.getHeight(), null);
        bufferStrategy.show();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
