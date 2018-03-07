/* Struct-like class used to pass visual state from Server to Game.
 *
 * Author: Jason Yundt
 */

import java.awt.Image;

public abstract class RenderInfo
{
    protected abstract void setX(double x);
    protected abstract void setY(double y);
    public abstract Object getSprite(); // Returns the 2D image to be drawn

    public RenderInfo(double x, double y)
    {
        setX(x);
        setY(y);
    }
}
