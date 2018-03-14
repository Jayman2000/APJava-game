/* Used for timed sprite swaping.
 *
 * Author: Jason Yundt
 */

import java.util.Arrays;

public class Animation
{
    private final Object[] frames;
    private final int frameTime; // Time in ms that frames stay on for
    private boolean loop;

    private int currentFrame;
    private int timeLeft; // Time until the next frame

    /* Precondition(s) : frames is not null, and is not of length 0
     *                   frameTime > 0
     * Postcondition(s): None
     */
    public Animation(Object[] frames, int frameTime, boolean loop)
    {
        /* Copy the frames array so that no one can mess with it. The frames
         * themselves should be copied too, but that's a whole other can of
         * worms.
         */
        this.frames = Arrays.copyOf(frames, frames.length);
        this.frameTime = frameTime;
        this.loop = loop;

        currentFrame = 0;
        timeLeft = frameTime;
    }

    public Object getCurrentFrame()
    {
        return frames[currentFrame];
    }

    public void update(int deltaTime)
    {
        timeLeft -= deltaTime;

        if(timeLeft <= 0)
        {
            currentFrame++;
            timeLeft = frameTime;
        }

        if(currentFrame == frames.length)
            if(loop)
                reset();
            else
                currentFrame = frames.length - 1;
    }

    public void reset()
    {
        currentFrame = 0;
        timeLeft = frameTime;
    }
}
