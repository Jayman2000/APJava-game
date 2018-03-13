import java.awt.Image;

import java.io.IOException;

import java.util.ArrayList;

public class GameLogic
{
    public final static int WIDTH  = 640;
    public final static int HEIGHT = 360;

    private ArrayList<Renderable> toDraw; // Will be drawn on every call to update

    public GameLogic()
    {
        toDraw = new ArrayList<Renderable>();
        toDraw.add(new Player());
    }

    /* Runs one 'tic' of game logic.
     *
     * Paramaters:
     *     inputSignals - What inputs the client got since the last tic.
     *     deltaTime - time in milliseconds since last tic.
     *
     * Returns: What the client should draw, in the order the client should
     *          draw it.
     *
     * Precondition(s) : toDraw and all of its elements are not null.
     * Postcondition(s): All entities which respond to the player's input have
     *                   been informed of the input, and all game state has
     *                   been updated for deltaTime milliseconds of game play.
     *
     * Author(s): Jason Yundt
     */
    public OutputInfo update(Object[] inputSignals, int deltaTime)
    {
        ArrayList<RenderInfo> retVisuals = new ArrayList<RenderInfo>(toDraw.size());

        for(Renderable r : toDraw)
        {
            for(RenderInfo toAdd : r.getRenderInfo())
            {
                retVisuals.add(toAdd);
            }
        }

        return new OutputInfo(retVisuals.toArray(new RenderInfo[retVisuals.size()]));
    }
}
