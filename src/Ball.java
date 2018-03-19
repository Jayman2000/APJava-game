import java.io.IOException;

// Author: Jason Yundt, Timmy Retelle
public class Ball extends Circle implements Entity, Collidable
{
    private Vector2 velocity; // Measured in units/ms
    private static final Vector2 GRAVITY = new Vector2(0, -0.01); // Aceleration due to gravity in units/ms^2
    private static final double FRICTION = 0.9999; // portion of velocity remaining after 1 ms

    private final Object sprite;

    private int score;
    private int level;

    public Ball(double centerX, double centerY, double vectorX, double vectorY)
    {
        super(centerX, centerY, 0);
        sprite = Game.loadSprite("ball.png");
        setRadius(Game.getWidthOfSprite(sprite)/2.0); // Assuming sprite is a square

        velocity = new Vector2(vectorX, vectorY);

        score = 0;
        level = 0;
    }

    public boolean isColliding(Circle collidableOther)
    {
        return this.isTouching(collidableOther);
    }

    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = new RenderInfo[1];
        ret[0] = Game.newRenderInfo(sprite, getX()-getRadius(), getY()-getRadius());
        return ret;
    }

    // deltaTime is in ms
    public void update(int deltaTime)
    {
        Vector2 scaledVelocity = new Vector2(velocity);
        scaledVelocity.scl(deltaTime);
        setX(getX() + scaledVelocity.getX());
        setY(getY() + scaledVelocity.getY());

        // Bounce off of walls
        if(getX()-getRadius() < 0)
        {
            setX(getRadius());
            velocity.reflect(new Vector2(-1, 0));
        }
        else if(getX()+getRadius() > GameLogic.WIDTH)
        {
            setX(GameLogic.WIDTH-getRadius());
            velocity.reflect(new Vector2(1, 0));
        }

        if(getY()-getRadius() < 0)
        {
            setY(getRadius());
            velocity.reflect(new Vector2(0, -1));
        }
        else if(getY()+getRadius() > GameLogic.HEIGHT)
        {
            setY(GameLogic.HEIGHT-getRadius());
            velocity.reflect(new Vector2(0, 1));
        }

        velocity.add(GRAVITY);
        velocity.scl(Math.pow(FRICTION, deltaTime * (9.0/8.0) * level));
    }

    public void onCollision(Collidable other)
    {
        if(other instanceof Tile)
        {
            score++;
            if(score >= GameLogic.SCORE_LIMIT)
                score = GameLogic.SCORE_LIMIT-1;
        }
    }

    public void push(Circle pusher)
    {
        Vector2 toAdd = new Vector2(getX()-pusher.getX(), getY()-pusher.getY());
        toAdd.scl(1.0/32.0);
        velocity.add(toAdd);
    }

    public int getScore()
    {
        return score;
    }

    public void levelUp()
    {
        level++;
    }
}
