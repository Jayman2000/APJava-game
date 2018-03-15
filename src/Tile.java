
public class Tile extends Circle implements Collidable
{
    private Object sprite;

    public Tile(double x, double y)
    {
        super(x, y, 0);
        sprite = Game.loadSprite("tile.png");

        setRadius(Game.getWidthOfSprite(sprite));
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
            sprite = Game.loadSprite("tile2.png");
            /* Add point */
        }
    }

    public void update(int deltaTime)
    {

    }

    public boolean isColliding(Circle collidableOther)
    {
        return this.isTouching(collidableOther);
    }
}
