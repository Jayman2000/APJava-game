import java.io.IOException;

public class Ball extends Circle
{
    private Vector2 velocity; // Measured in units/ms
    private static final Vector2 GRAVITY = new Vector2(0, -0.01); // Aceleration due to gravity in units/ms^2
    private static final double FRICTION = 0.9999; // portion of velocity remaining after 1 ms

    private final Object sprite;

    public Ball(double centerX, double centerY, double vectorX, double vectorY)
    {
        super(centerX, centerY, 0);
        sprite = Game.loadSprite("ball.png");

        velocity = new Vector2(vectorX, vectorY);
    }

    public RenderInfo getRenderInfo()
    {
        return Game.newRenderInfo(sprite, getX(), getY());
    }

    // deltaTime is in ms
    public void update(int deltaTime)
    {
        Vector2 scaledVelocity = new Vector2(velocity);
        scaledVelocity.scl(deltaTime);
        setX(getX() + scaledVelocity.getX());
        setY(getY() + scaledVelocity.getY());

        // Bounce off of walls
        if(getX() < 0)
        {
            setX(0);
            velocity.reflect(new Vector2(-1, 0));
        }
        else if(getX() > GameLogic.WIDTH)
        {
            setX(GameLogic.WIDTH);
            velocity.reflect(new Vector2(1, 0));
        }

        if(getY() < 0)
        {
            setY(0);
            velocity.reflect(new Vector2(0, -1));
        }
        else if(getY() > GameLogic.HEIGHT)
        {
            setY(GameLogic.HEIGHT);
            velocity.reflect(new Vector2(0, 1));
        }

        velocity.add(GRAVITY);
        velocity.scl(Math.pow(FRICTION, deltaTime));
    }
}
