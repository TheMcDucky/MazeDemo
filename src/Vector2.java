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
    public Vector2(Vector2 vector2){
        this.x = vector2.getX();
        this.y = vector2.getY();
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    
    public Vector2 add(Vector2 other){
        x += other.x;
        y += other.y;
        return this;
    }
    public Vector2 subtract(Vector2 other){
        setX(x - other.x);
        setY(y - other.y);
        return this;
    }

    public Vector2 rotate(double radians) {
        double tx = x;
        double ty = y;
        setX(tx * Math.cos(radians) - ty * Math.sin(radians));
        setY(tx * Math.sin(radians) + ty * Math.cos(radians));
        return this;
    }
    
    public Vector2 scale(double scalar){
        setX(x * scalar);
        setY(y * scalar);
        return this;
    }
    
    // Hadamard product
    public Vector2 hadamard(Vector2 other) {
        setX(x * other.x);
        setY(y * other.y);
        return this;
    }
    
    // dot product
    public double dot(Vector2 other){
        return x * other.x + y * other.y;
    }
    
    public double getLength(){
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

    public Vector2 copy () {
        return new Vector2(this);
    }
    
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    
    // Static methods

    public static Vector2 add(Vector2 a, Vector2 b){
        return new Vector2(a.getX() + b.getX(), a.getY() + b.getY());
    }
    public static Vector2 subtract(Vector2 a, Vector2 b){
        return new Vector2(a.getX() - b.getX(), a.getY() - b.getY());
    }
    public static Vector2 rotate(Vector2 vec, double radians) {
        return new Vector2(vec.getX() * Math.cos(radians) - vec.getY() * Math.sin(radians),
                vec.getX() * Math.sin(radians) + vec.getY() * Math.cos(radians));
    }
}
