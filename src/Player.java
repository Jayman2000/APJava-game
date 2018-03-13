public class Player implements Renderable
{
    private static final int SPEED = 5;
    private double x = 0;
    private double y = 0;
    private Object sprite;

    public Player()
    {
        sprite = Game.loadSprite("idle-new.png");
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void getY(double y)
    {
        this.y = y;
    }

    public RenderInfo[] getRenderInfo()
    {
        RenderInfo[] ret = new RenderInfo[1];
        ret[0] = Game.newRenderInfo(sprite, getX(), getY());
        return ret;
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
