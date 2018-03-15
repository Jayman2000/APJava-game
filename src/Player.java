import java.awt.event.KeyEvent;

import java.util.Arrays;

public class Player implements Renderable, Entity, Controllable, Collidable
{
    private static final double SPEED = 5.0/16.0;
    private double x;
    private double y;
    private Object[] inputs;

    private Object sprite; // The player's current sprite

    private Object idle; // The player's idle sprite
    private Animation walkLeft;
    private Animation walkRight;

    private static enum InputSignals
    {
        LEFT,
        START_LEFT,
        RIGHT,
        START_RIGHT,
        MELEE,
        SHOOT
    }

    public Player()
    {
        x = 0;
        y = 0;
        inputs = new Object[0];
        idle = Game.loadSprite("idle-new.png");

        Object[] leftFrames = {Game.loadSprite("walking-left-1-new.png"),
                               Game.loadSprite("walking-left-2-new.png"),
                               Game.loadSprite("walking-left-3-new.png")
                              };
        walkLeft = new Animation(leftFrames, 100, true);

        Object[] rightFrames = {Game.loadSprite("walking-right-1-new.png"),
                                Game.loadSprite("walking-right-2-new.png"),
                                Game.loadSprite("walking-right-3-new.png")
                               };
        walkRight = new Animation(rightFrames, 100, true);

        sprite = idle;
    }

    public Bind[] getBinds()
    {
        Bind[] ret = {new Bind(KeyEvent.VK_LEFT, InputSignals.START_LEFT, InputSignals.LEFT),
                      new Bind(KeyEvent.VK_RIGHT, InputSignals.START_RIGHT, InputSignals.RIGHT)
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
        if(inputs.length == 0)
            idle();

        for(Object input : inputs)
        {
            if(input == InputSignals.RIGHT)
            {
                x += SPEED * deltaTime;
                walkRight.update(deltaTime);
                sprite = walkRight.getCurrentFrame();
            }
            else if(input == InputSignals.LEFT)
            {
                x -= SPEED * deltaTime;
                walkLeft.update(deltaTime);
                sprite = walkLeft.getCurrentFrame();
            }
            else if(input == InputSignals.START_LEFT)
            {
                walkLeft.reset();
            }
            else if(input == InputSignals.START_RIGHT)
            {
                walkRight.reset();
            }
            else
            {
                idle();
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

    public void death(Ball b)
    {
        if(this.isColliding(b))
             setX(GameLogic.WIDTH/2.0);
    }

    private void idle()
    {
        sprite = idle;
    }
}
