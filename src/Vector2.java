/* Represents a 2D vector for physics calculations. This class is immuteable,
 * so all operations (add, subtract, etc.) return a new Vector2.
 *
 * Author: Jason Yundt
 */

public class Vector2
{
    public final double x; // Horizontal component
    public final double y; // Vertical component

    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /* Adds this to other.
     *
     * Precondition(s) : other is not null
     * Postcondition(s): None
     */
    public Vector2 add(Vector2 other)
    {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    // Scales this vector by scalar.
    public Vector2 scl(double scalar)
    {
        return new Vector2(scalar * x, scalar * y);
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

            Vector2 c = a.add(b);
            if (c.x !=34 || c.y != 20)
                System.out.println("ERROR: a + b != c");
        }

        // Multiplication by a scalar
        {
            Vector2 m = new Vector2(7,3);
            Vector2 a = m.scl(3);

            if (a.x != 21 || a.y != 9)
                System.out.println("ERROR: 3m != a");
        }
    }

    static double randomCoordinate()
    {
        return Math.random() * 20 - 10;
    }
}
