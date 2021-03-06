/* An imuteable struct-like class for returning what should be outputed after
 * GameLogic.update has been called.
 *
 * Author: Jason Yundt
 */

public class OutputInfo
{
    public final RenderInfo[] visuals; // What to draw
    public final Object[] soundEffects; // What audio to play
    public final Object song; /* What song to change to. If null, continue
                               * playing the current song on loop.
                               */
    public final int score;

    public OutputInfo(RenderInfo[] visuals, Object[] soundEffects, Object song, int score)
    {
        this.visuals = visuals;
        this.soundEffects = soundEffects;
        this.song = song;
        this.score = score;
    }

    /*public OutputInfo(RenderInfo[] visuals, Object[] soundEffects, Object song)
    {
        this(visuals, soundEffects, song, 0);
    }

    public OutputInfo(RenderInfo[] visuals, Object[] soundEffects)
    {
        this(visuals, soundEffects, null);
    }

    public OutputInfo(RenderInfo[] visuals)
    {
        this(visuals, new Object[0]);
    }*/
}
