public class Circle extends Shape
{
    private double radius;
    private double centerX, centerY;

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

    public boolean isTouching(Shape other)
    {
        if(other instanceof Circle)
        {
            Circle c1 = this;
            Circle c2 = (Circle)other;

            double d = distance(c1.getX(), c1.getY(), c2.getX(), c2.getY());
            return d <= c1.getRadius() + c2.getRadius();
        }

        return false; // Should be replaced with raising an exception
    }
}
