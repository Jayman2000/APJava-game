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

    public int getX()
    {
        return convertX(x);
    }

    public int getY()
    {
        return convertY(y);
    }

    // Converts GameLogic style coordinates to Swing style ones
    private int convertX(double x)
    {
        return (int)Math.round(x);
    }

    private int convertY(double y)
    {
        double ret = y;
        // Account for the fact that Image's origin in on the top
        ret += Game.getHeightOfSprite(sprite);
        // Account for the fact that JPanel's origin is on the top
        ret = GameLogic.HEIGHT - ret;
        // Round
        return convertX(ret);
    }


    public SwingRenderInfo(Image sprite, double x, double y)
    {
        super(x,y);
        this.sprite = sprite;
    }
}
