import java.awt.Image;

import java.util.ArrayList;

public class GameLogic
{
    public final static int SCORE_LIMIT = 999999999;
    public final static int WIDTH  = 600;
    public final static int HEIGHT = 450;

    Player p;
    Ball b;

    private ArrayList<Renderable> renderables;
    private ArrayList<Entity> entities;
    private ArrayList<Controllable> controllables;
    private ArrayList<Collidable> collidables;

    private ArrayList<Tile> tiles;
    private ArrayList<PlayerProjectile> bullets;

    private Object nextSong;

    public GameLogic()
    {
        p = new Player();
        b = new Ball(WIDTH/2.0, HEIGHT/2.0, 1, 1);

        tiles = new ArrayList<Tile>();
        for(int x = 64; x < WIDTH-64; x += 32)
            for(int y = 64; y < HEIGHT-64; y += 32)
                tiles.add(new Tile(x, y));

        controllables = new ArrayList<Controllable>();
        controllables.add(p);

        collidables = new ArrayList<Collidable>();
        for(Collidable c : tiles)
        {
            collidables.add(c);
        }
        collidables.add(b);

        entities = new ArrayList<Entity>();
        for(Entity e : collidables)
        {
            entities.add(e);
        }
        for(Entity e : controllables)
        {
            entities.add(e);
        }

        collidables.add(p); // Makes sure player isn't added to Entities twice

        // NOTE: order matters for determining what is on top of what
        renderables = new ArrayList<Renderable>();
        renderables.add(new Decoration("background.png", 0, -4));
        for(Renderable r : entities)
        {
            renderables.add(r);
        }

        //nextSong = Game.loadAudio("music.m4a");

        bullets = new ArrayList<PlayerProjectile>();
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
     *                   inputSignals is not null.
     * Postcondition(s): All entities which respond to the player's input have
     *                   been informed of the input, and all game state has
     *                   been updated for deltaTime milliseconds of game play.
     *
     * Author(s): Jason Yundt
     */
    public OutputInfo update(Object[] inputSignals, int deltaTime)
    {
        if(!p.isDead())
        {
            // Send signals
            for(Controllable c : controllables)
            {
                c.sendInputs(inputSignals);
            }

            // Update everything
            for(Entity e : entities)
            {
                e.update(deltaTime);
            }

            // Check for collisions
            for(Collidable a : collidables)
            {
                if(a.isColliding(b))
                {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }

            if(allClear())
                resetTiles();
        }

        for(PlayerProjectile b : p.getBullets())
        {
            bullets.add(b);
            renderables.add(b);
            entities.add(b);
            collidables.add(b);
        }

        for(int i = bullets.size()-1; i >= 0; i--)
        {
            PlayerProjectile b = bullets.get(i);
            if(b.isDead())
            {
                bullets.remove(i);
                renderables.remove(b);
                entities.remove(b);
                collidables.remove(b);
            }
        }

        // Create the OutputInfo to return
        ArrayList<RenderInfo> retVisuals = new ArrayList<RenderInfo>(renderables.size());

        for(Renderable rend : renderables)
        {
            for(RenderInfo info : rend.getRenderInfo())
            {
                retVisuals.add(info);
            }
        }

        OutputInfo ret =  new OutputInfo(retVisuals.toArray(new RenderInfo[retVisuals.size()]),
                                         new Object[0], nextSong, b.getScore());
        nextSong = null;
        return ret;
    }

    public Bind[] getBinds()
    {
        ArrayList<Bind> ret = new ArrayList<Bind>(controllables.size());

        for(Controllable c : controllables)
        {
            for(Bind b : c.getBinds())
            {
                ret.add(b);
            }
        }

        return ret.toArray(new Bind[ret.size()]);
    }

    private void resetTiles()
    {
        for(Tile t : tiles)
            t.reset();
    }

    private boolean allClear()
    {
        for(Tile t : tiles)
            if(!t.isDead())
                return false;

        return true;
    }
}
