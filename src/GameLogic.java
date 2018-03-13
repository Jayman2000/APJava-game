import java.awt.Image;

import java.util.ArrayList;

public class GameLogic
{
    public final static int WIDTH  = 640;
    public final static int HEIGHT = 360;

    private ArrayList<Renderable> renderables; // Will be drawn on every call to update
    private ArrayList<Entity> entities;

    public GameLogic()
    {
        entities = new ArrayList<Entity>();
        entities.add(new Ball(WIDTH/2.0, HEIGHT/2.0, 1, 1));

        renderables = new ArrayList<Renderable>();
        renderables.add(new Player());
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
        // Update everything
        for(Entity e : entities)
        {
            e.update(deltaTime);
        }

        // Create the OutputInfo to return
        ArrayList<RenderInfo> retVisuals = new ArrayList<RenderInfo>(renderables.size());

        // ArrayList.toArray requires an array of the right size to populate
        addToRetVisuals(retVisuals, entities.toArray(new Renderable[entities.size()]));
        addToRetVisuals(retVisuals, renderables.toArray(new Renderable[renderables.size()]));

        return new OutputInfo(retVisuals.toArray(new RenderInfo[retVisuals.size()]));
    }

    private static void addToRetVisuals(ArrayList<RenderInfo> retVisuals, Renderable[] toAdd)
    {
        for(Renderable r: toAdd)
        {
            for(RenderInfo info : r.getRenderInfo())
            {
                retVisuals.add(info);
            }
        }
    }
}
