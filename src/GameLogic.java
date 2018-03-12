import java.awt.Image;

import java.io.IOException;

public class GameLogic
{
    public final static int WIDTH  = 640;
    public final static int HEIGHT = 360;

    private Ball ball;

    public GameLogic()
    {
        ball = new Ball(WIDTH/2.0, HEIGHT/2.0, 1, 1);
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
        ball.update(deltaTime);

        RenderInfo[] toDraw = new SwingRenderInfo[1];
        toDraw[0] = ball.getRenderInfo();
        return new OutputInfo(toDraw);
    }
}
