/* Used for collision detection.
 *
 * Author: Jason Yundt
 */

public class Circle
{
    private final double radius;
    private final double centerX, centerY;

    public Circle(double x, double y, double radius)
    {
        centerX = x;
        centerY = y;
        this.radius = radius;
    }

    public double getX()
    {
        return centerX;
    }

    public double getY()
    {
        return centerY;
    }

    public double getRadius()
    {
        return radius;
    }

    public boolean isTouching(Circle other)
    {
        double dx = other.getX() - this.getX(); // Delta x
        double dy = other.getY() - this.getY(); // Delta y

        double d = Math.hypot(dx, dy); // distance between the two centers
        return d <= this.getRadius() + other.getRadius();
    }
}
