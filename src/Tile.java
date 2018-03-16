// Author: Jason O'Dwyer

public class Tile extends Circle implements Collidable
{
    private Object sprite;
    private boolean isDead;

    public Tile(double x, double y)
    {
        super(x, y, 0);
        sprite = Game.loadSprite("tile.png");

        setRadius(Game.getWidthOfSprite(sprite)/2.0);

        isDead = false;
    }

    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = new RenderInfo[1];
        ret[0] = Game.newRenderInfo(sprite, getX()-getRadius(), getY()-getRadius());
        return ret;
    }

    public void update(int deltaTime)
    {

    }

    public boolean isColliding(Circle collidableOther)
    {
        return this.isTouching(collidableOther);
    }

    public void onCollision(Collidable other)
    {
        if(other instanceof Ball)
        {
            sprite = Game.loadSprite("tile2.png");
            isDead = true;
        }
    }

    public boolean isDead()
    {
        return isDead;
    }

    public void reset()
    {
        sprite = Game.loadSprite("tile.png");
        isDead = false;
    }
}
