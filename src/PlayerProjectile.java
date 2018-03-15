public class PlayerProjectile extends Circle implements Collidable
//PlayerProjectile by Timothy Retelle
{
    private final Object sprite;
    public static final double SPEED = 5;
    public PlayerProjectile(double x, double y)
    {
        super(x, y, 0);
        sprite = Game.loadSprite("player-projectile-new.png");
		setX(Player.getX());
    }

    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = { Game.newRenderInfo(sprite, getX(), getY()) };
        return ret;
    }

    public void update(int deltaTime)
    {
        setY(getY()+SPEED);
    }

    public boolean isColliding(Circle collidableOther)
    {
        return this.isTouching(collidableOther);
    }
    public void onCollision(Collidable other)
    {
        if(other instanceof Ball)
        {
            ball.push(this);
        }
    }
}
