package com.mcducky.maze;

public class Vector2 {
    // Constants
    public  static Vector2 ZERO = new Vector2(){
        @Override public void setX(double x){}
        @Override public void setY(double y){}};
    public  static Vector2 IDENTITY = new Vector2(1, 1){
        @Override public void setX(double x){}
        @Override public void setY(double y){}};

    protected double x, y;
    public Vector2(){
        x = 0;
        y = 0;
    }
    public Vector2(double x, double y){

        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public Vector2 add(Vector2 other){
        return new Vector2(this.x + other.getX(), this.y + other.getY());
    }
    public Vector2 subtract(Vector2 other){
        return new Vector2(this.x - other.getX(), this.y - other.getY());
    }
    public void translate(Vector2 offset) {
        x += offset.x;
        y += offset.y;
    }

    public void rotate(double radians) {
        double tx = x;
        double ty = y;
        x = tx * Math.cos(radians) - ty * Math.sin(radians);
        y = tx * Math.sin(radians) + ty * Math.cos(radians);
    }
    public double abs(){
        return Math.sqrt(x*x+y*y);
    }
    public double getAngle(){
        return Math.atan2(y, x);
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    // Static methods

    public static Vector2 add(Vector2 a, Vector2 b){
        return new Vector2(a.getX() + b.getX(), a.getY() + b.getY());
    }
    public static Vector2 subtract(Vector2 a, Vector2 b){
        return new Vector2(a.getX() - b.getX(), a.getY() - b.getY());
    }
}
