import java.io.IOException;

public class Player {
    
    private static final int SPEED = 5;
    private double x = 0;
    private double y = 0;
    private Object sprite;
    
    public Player() throws IOException
    {
        sprite = Game.loadSprite();
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public RenderInfo getRenderInfo()
    {
        return Game.newRenderInfo(sprite, getX(), getY());
    }
    
    public void update(int deltaTime)
    {
        if(getX() < 0)
        {
            setX(0);
        }
        else if(getX() > GameLogic.WIDTH)
        {
            setX(GameLogic.WIDTH);
        }
    }
}