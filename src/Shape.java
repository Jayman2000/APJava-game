/* Used for collision detection.
 *
 * Author: Jason Yundt
 */

public abstract class Shape
{
    /* Tests to see if this shares at least one point with other.
     *
     * Precondition(s) : other is not null
     * Postcondition(s): None
     */
    public abstract boolean isTouching(Shape other);

    // Calculates the distance between the points (x1, y1) and (x2, y2).
    public static double distance(double x1, double y1, double x2, double y2)
    {
        return Math.hypot(x2-x1, y2-y1);
    }
}
