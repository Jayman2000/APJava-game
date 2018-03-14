import java.awt.event.KeyEvent;

public class Player implements Renderable, Entity, Controllable
{
    private static final double SPEED = 1;
    private double x = 0;
    private double y = 0;
    private Object[] inputs;

    private Object sprite;

    private static enum InputSignals
    {
        LEFT,
        RIGHT,
        MELEE,
        SHOOT
    }

    public Player()
    {
        inputs = new Object[0];
        sprite = Game.loadSprite("idle-new.png");
    }

    public Bind[] getBinds()
    {
        Bind[] ret = new Bind[1];

        ret[0] = new Bind(KeyEvent.VK_RIGHT, null, InputSignals.RIGHT);

        return ret;
    }

    public void sendInputs(Object[] signals)
    {
        /* The input array is cloned so that other classes do not have access to
         * it. The objects themselves are not cloned, but this does not matter
         * since the only signals we care about are enums which are imutable.
         */
        inputs = new Object[signals.length];
        for(int i = 0; i < signals.length; i++)
            inputs[i] = signals[i];
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
        for(Object input : inputs)
        {
            if(input == InputSignals.RIGHT)
            {
                x += SPEED * deltaTime;
            }
        }

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
