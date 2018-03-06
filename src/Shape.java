/* Used for collision detection.
 *
 * Author: Jason Yundt
 */

public abstract class Shape
{
    /* Tests to see if this shares at least one point with other
     *
     * Precondition(s) : other is not null
     * Postcondition(s): None
     */
    public abstract boolean isTouching(Shape other);
}
