public class ProjectionUtil {
    public static double project (Vector2 vertex, Camera camera) {
        double projection;
        if (vertex.getY() == 0) {
            if (vertex.getX() * camera.focalLength > 0)
                return Double.POSITIVE_INFINITY;
            if (vertex.getX() * camera.focalLength < 0)
                return Double.NEGATIVE_INFINITY;
        }
        projection = vertex.getX() * camera.focalLength / vertex.getY();
        if (projection == Double.POSITIVE_INFINITY) projection = Double.MAX_VALUE;
        else if (projection == Double.POSITIVE_INFINITY) projection = Double.MIN_VALUE;
        else if (projection == Double.NaN) projection = 0;
        return projection;
    }
    
    public static Vector2 cameraTransform (Vector2 vertex, Camera camera){
        Vector2 camTransform = Vector2.subtract(vertex, camera.getPosition()); //Translate
        camTransform.rotate(-camera.getRotation()); //Rotate
        return camTransform;
    }
}
