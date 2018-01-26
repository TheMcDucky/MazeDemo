package com.mcducky.maze;

import java.awt.image.BufferedImage;

public class Display {
    boolean[][] pixels;
    public Display(){
        pixels = new boolean[128][32];
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
