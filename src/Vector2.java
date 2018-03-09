/* Represents a 2D vector for physics calculations.
 *
 * Author: Jason Yundt
 */

public class Vector2
{
    private double x; // Horizontal component
    private double y; // Vertical component

    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // Getters and setters
    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    // Returns the length of this.
    public double magnitude()
    {
        return Math.hypot(x, y);
    }

    /* Adds two vectors.
     *
     * Precondition(s) : other is not null
     * Postcondition(s): the tip of this has been transformed with a horizontal
     *                   component of other's x-coordinate, and a vertical
     *                   component of other's y-coordinate.
     */

    public void add(Vector2 other)
    {
        x += other.getX();
        y += other.getY();
    }


    // Scales this vector by scalar.
    public void scl(double scalar)
    {
        x *= scalar;
        y *= scalar;
    }

    // Returns the angle formed by this and the top side of the positive x-axis
    public double angle()
    {
        return Math.atan2(y, x);
    }

    // Scales this so that its magnitude is 1
    public void normalize()
    {
        x = Math.cos(angle());
        y = Math.sin(angle());
    }

    /* Returns the dot product of this and other.
     *
     * Precondition(s) : other is not null
     * Postcondition(s): none
     */
    public double dot(Vector2 other)
    {
        return magnitude() * other.magnitude() * Math.cos(angle());
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    // Tests for this class (probally should be commented out in the final build).
    public static void main(String[] args)
    {
        // Test data is from https://www.mathsisfun.com/algebra/vectors.html

        final int TIMES = 100; // Times to do recurring tests

        // Constructor
        for(int i = 0; i < TIMES; i++)
        {
            double x = randomCoordinate();
            double y = randomCoordinate();
            Vector2 test = new Vector2(x, y);
            if (test.x != x || test.y != y)
                System.out.println("ERROR: new Vector2(" + x + ", " + y + ") != " + test);
        }

        // Addition
        {
            Vector2 a = new Vector2(8,13);
            Vector2 b = new Vector2(26,7);

            a.add(b);
            if (a.getX() !=34 || a.getY() != 20)
                System.out.println("ERROR: a + b != c");
        }

        // Multiplication by a scalar
        {
            Vector2 m = new Vector2(7,3);
            m.scl(3);

            if (m.x != 21 || m.y != 9)
                System.out.println("ERROR: 3m != a");
        }
    }

    static double randomCoordinate()
    {
        return Math.random() * 20 - 10;
    }
}
