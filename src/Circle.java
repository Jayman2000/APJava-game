/* Used for collision detection.
 *
 * Author: Jason Yundt
 */

public class Circle
{
    private double radius;
    private double centerX, centerY;

    public Circle(double x, double y, double radius)
    {
        centerX = x;
        centerY = y;
        this.radius = radius;
    }

    public Circle(double x, double y)
    {
        this(x, y, 0);
    }

    public double getX()
    {
        return centerX;
    }

    public double getY()
    {
        return centerY;
    }

    public void setX(double x)
    {
        centerX = x;
    }

    public void setY(double y)
    {
        centerY = y;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public boolean isTouching(Circle other)
    {
        double dx = other.getX() - this.getX(); // Delta x
        double dy = other.getY() - this.getY(); // Delta y

        double d = Math.hypot(dx, dy); // distance between the two centers
        return d <= this.getRadius() + other.getRadius();
    }
}
