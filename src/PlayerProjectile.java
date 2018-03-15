public class PlayerProjectile extends Circle implements Collidable
{
    private final Object sprite;

    public PlayerProjectile(double x, double y)
    {
        super(x, y, 0);
        sprite = Game.loadSprite("player-projectile-new.png");
    }

    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = { Game.newRenderInfo(sprite, getX(), getY()) };
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
            //ball.push(getX(), getY());
            System.out.println("Hit ball");
        }
    }
}
