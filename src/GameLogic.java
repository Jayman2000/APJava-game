import java.awt.Image;

import java.io.IOException;

public class GameLogic
{
    public final static int WIDTH  = 640;
    public final static int HEIGHT = 360;

    private Object testSprite;

    public GameLogic()
    {
        testSprite = Game.loadSprite("tile.png");
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
     * Precondition(s) : None
     * Postcondition(s): All entities which respond to the player's input have
     *                   been informed of the input, and all game state has
     *                   been updated for deltaTime milliseconds of game play.
     *
     * Author(s): None (stub)
     */
    public OutputInfo update(Object[] inputSignals, int deltaTime)
    {
        RenderInfo[] toDraw = new SwingRenderInfo[1];
        toDraw[0] = Game.newRenderInfo(testSprite, 5, 5);
        return new OutputInfo(toDraw);
    }
}
