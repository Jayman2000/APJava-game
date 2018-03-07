/* Implementation of RenderInfo specifically for swing.
 *
 * Author: Jason Yundt
 */

import java.awt.Image;

public class SwingRenderInfo extends RenderInfo
{
    /* Still using GameLogic's coordinate system, so you'll need convert them
     * to swing's system before drawing.
     */
    private double x;
    private double y;
    private final Image sprite; // What to draw

    public Image getSprite()
    {
        return sprite;
    }

    protected void setX(double x)
    {
        this.x = x;
    }

    protected void setY(double y)
    {
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }


    public SwingRenderInfo(Image sprite, double x, double y)
    {
        super(x,y);
        this.sprite = sprite;
    }
}
