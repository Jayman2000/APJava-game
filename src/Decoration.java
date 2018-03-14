/* A Docoration does not affect gameplay, but is rendered.
 *
 * Author: Jason Yundt
 */

public class Decoration implements Renderable
{
    private final double x, y;
    private final Object sprite;

    public Decoration(String filename, double x, double y)
    {
        this.x = x;
        this.y = y;

        sprite = Game.loadSprite(filename);
    }

    public Decoration(String filename)
    {
        this(filename, 0, 0);
    }

    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = { Game.newRenderInfo(sprite, x, y) };
        return ret;
    }
}
