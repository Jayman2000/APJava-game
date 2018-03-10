import java.io.IOException;

public class Ball
{
    private double x, y;
    private Vector2 velocity; // Measured in units/ms

    private final Object sprite;

    public Ball(double centerX, double centerY, double vectorX, double vectorY) throws IOException
    {
        x = centerX;
        y = centerY;
        velocity = new Vector2(vectorX, vectorY);

        sprite = Game.loadSprite("ball.png");
    }

    public RenderInfo getRenderInfo()
    {
        return Game.newRenderInfo(sprite, x, y);
    }

    // deltaTime is in ms
    public void update(int deltaTime)
    {
        // Stub
    }
}
