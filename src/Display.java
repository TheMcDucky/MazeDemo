import java.awt.image.BufferedImage;

public class Display {
    private boolean[][] pixels;
    public Display(){
        pixels = new boolean[128][32];
    }
    
    public void putPixel(int x, int y, boolean value){
        if(x >= 0 && y >= 0 && x < 128 && y < 32)
            pixels[x][y] = value;
    }
    
    public void drawLine(Vector2 a, Vector2 b){
        double x = Math.min(Math.max(a.getX(), 0), 127);
        double y = Math.min(Math.max(a.getY(), 0), 31);
        
        double w = Math.min(Math.max(b.getX(), 0), 127) - x;
        double h = Math.min(Math.max(b.getY(), 0), 31) - y;
        
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
        
        if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
        if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
        double longest = Math.abs(w) ;
        double shortest = Math.abs(h) ;
        if (!(longest>shortest)) {
            longest = Math.abs(h) ;
            shortest = Math.abs(w) ;
            if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
            dx2 = 0 ;
        }
        double numerator = longest/2 ;
        for (int i=0;i<=longest;i++) {
            putPixel((int)Math.round(x),(int)Math.round(y),true) ;
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x += dx1 ;
                y += dy1 ;
            } else {
                x += dx2 ;
                y += dy2 ;
            }
        }
    }
    
    
    public void clear(){
        for (int y = 0; y < 32; y++) {
            for (int x = 0; x < 128; x++) {
                pixels[x][y] = false;
            }
        }
        pixels[64][16] = true;
    }
    
    public void draw(BufferedImage img){
        for (int y = 0; y < 32; y++) {
            for (int x = 0; x < 128; x++) {
                if(pixels[x][y])
                    img.setRGB(x, y, 0x1A2010 + 0x010101 * (int)(Math.random() * 4));
                else
                    img.setRGB(x, y, 0xAAB0A0 + 0x010101 * (int)(Math.random() * 4));
            }
        }
    }
}
