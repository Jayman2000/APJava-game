/* Struct-like class used to pass visual state from Server to Game.
 *
 * Author: Jason Yundt
 */

import java.awt.Image;

public class RenderInfo
{
    final public double x, y;
    final public Image toDraw;

    public RenderInfo(Image toDraw, double x, double y)
    {
        this.toDraw = toDraw;
        this.x = x;
        this.y = y;
    }
}
