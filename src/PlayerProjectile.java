// Author: Timothy Retelle

public class PlayerProjectile extends Circle implements Collidable
{
    private final Object sprite;
    public static final double SPEED = 3.0/8.0;

    private boolean isDead;

    public PlayerProjectile(double x, double y)
    {
        super(x, y, 0);
        sprite = Game.loadSprite("player-projectile-new.png");
        setRadius(Game.getWidthOfSprite(sprite));
        isDead = false;
    }

    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = { Game.newRenderInfo(sprite, getX(), getY()) };
        return ret;
    }

    public void update(int deltaTime)
    {
        setY(getY()+SPEED*deltaTime);
        if(getY() - getRadius() > GameLogic.HEIGHT)
        {
            isDead = true;
        }
    }

    public boolean isColliding(Circle collidableOther)
    {
        return this.isTouching(collidableOther);
    }
    public void onCollision(Collidable other)
    {
        if(other instanceof Ball)
        {
            isDead = true;
            Ball b = (Ball)other;
            b.push(this);
        }
    }
    public boolean isDead()
    {
        return isDead;
    }
}
