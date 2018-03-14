import java.awt.event.KeyEvent;

import java.util.Arrays;

public class Player implements Renderable, Entity, Controllable
{
    private static final double SPEED = 5.0/16.0;
    private double x;
    private double y;
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
        x = 0;
        y = 0;
        inputs = new Object[0];
        sprite = Game.loadSprite("idle-new.png");
    }

    public Bind[] getBinds()
    {
        Bind[] ret = {new Bind(KeyEvent.VK_LEFT, null, InputSignals.LEFT),
                      new Bind(KeyEvent.VK_RIGHT, null, InputSignals.RIGHT)
                     };

        return ret;
    }

    public void sendInputs(Object[] signals)
    {
        /* The input array is cloned so that other classes do not have access to
         * it. The objects themselves are not cloned, but this does not matter
         * since the only signals we care about are enums which are imutable.
         */
        inputs = Arrays.copyOf(signals, signals.length);
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
            else if(input == InputSignals.LEFT)
            {
                x -= SPEED * deltaTime;
            }
        }

        // Make sure the player cannot leave the playfield
        if(getX() < 0)
        {
            setX(0);
        }
        else if(getX()+Game.getWidthOfSprite(sprite) > GameLogic.WIDTH)
        {
            setX(GameLogic.WIDTH-Game.getWidthOfSprite(sprite));
        }
    }
}
