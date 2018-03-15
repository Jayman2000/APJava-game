
public class Tile extends Circle implements Collidable
{
    private final Object sprite;
	
    public Tile(double x, double y, double width)
    {
        super(x, y, (width/2.0));
        sprite = Game.loadSprite("tile.png");
    }
	
    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = new RenderInfo[1];
        ret[0] = Game.newRenderInfo(sprite, getX()-getRadius(), getY()-getRadius());
        return ret;
    }

    public void collision(Ball b)
    {
        if(this.isColliding(b))
        {
            sprite = Game.loadSprite(tile2.png);
            /* Add point */
        }
}
