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

    public Vector2(Vector2 toCopy)
    {
        this(toCopy.getX(), toCopy.getY());
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
    public void add(double dx, double dy)
    {
        x += dx;
        y += dy;
    }

    public void add(Vector2 other)
    {
        add(other.getX(), other.getY());
    }

    public void sub(double dx, double dy)
    {
        add(-dx, -dy);
    }

    public void sub(Vector2 other)
    {
        sub(other.getX(), other.getY());
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
        // Angle between the two vectors
        double angleBetween = Math.abs(angle() - other.angle());

        return magnitude() * other.magnitude() * Math.cos(angleBetween);
    }

    /* Reflect this off of the given normal. Formula from
     * https://math.stackexchange.com/q/13263
     *
     * Precondition(s) : normal is not null
     * Postcondition(s): this has been reflected off of a surface perpindicular
     *                   to the normal.
     */
    public void reflect(Vector2 normal)
    {
        normal = new Vector2(normal);

        if(normal.magnitude() != 1)
            normal.normalize();

        double scalar = 2 * (this.dot(normal));
        normal.scl(scalar);
        this.sub(normal);
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
