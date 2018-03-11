import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel implements JavaArcade, KeyListener
{
    // Input
    //  JavaArcade
    public void startGame()
    {
        // Stub
    }

    public void pauseGame()
    {
        // Stub
    }

    public void stopGame()
    {
        // Stub
    }


    //  Keyboard
    private ArrayList<Bind> binds;

    public Object[] getSignals()
    {
        for(Bind b : binds)
        {
            b.getSignals();
        }
        return null;
    }

    // Methods required by KeyListener
    public void keyTyped(KeyEvent e) { /* Intentiorally left empty */ }
    public void keyPressed(KeyEvent e)
    {
        for(Bind b : binds)
        {
            if(b.key == e.getKeyCode())
            {
                b.press();
            }
        }
    }
    public void keyReleased(KeyEvent e)
    {
        for(Bind b : binds)
        {
            if(b.key == e.getKeyCode())
            {
                b.release();
            }
        }
    }


    // Output
    //  JavaArcade
    public boolean running()
    {
        // Stub
        return false;
    }

    public String getGameName()
    {
        // Stub
        return null;
    }

    public String getInstructions()
    {
        // Stub
        return null;
    }

    public String getCredits()
    {
        // Stub
        return null;
    }

    public String getHighScore()
    {
        // Stub
        return null;
    }

    public int getPoints()
    {
        // Stub
        return 0;
    }


    //  Visual
    private SwingRenderInfo[] renderInfos;
    public void drawSprites(OutputInfo o)
    {
        renderInfos = (SwingRenderInfo[])o.visuals;
    }
    public void paintComponent(Graphics g)
    {
        g.drawRect(20, 20, 50, 50);
        g.drawRoundRect(70, 70, 50, 50, 40, 40);
        for(SwingRenderInfo r: renderInfos)
        {
            g.drawImage(r.getSprite(), (int)r.getX(), (int)r.getY(), null);
        }
    }

    //  Audio



    // Processing
    //  Ticker


    //  GameLogic


    //  AssetManager

    public static Image loadSprite(String name) throws IOException
    {
        Image sprite = ImageIO.read(new File("assets/" + name));
        return sprite;
    }

    /* Note: apparently Image.getWidth() can return -1 if not all of the image
     * has been loaded yet. How do we prevent that from happening?
     */
    public static int getWidthOfSprite(Object sprite)
    {
        Image spriteImage = (Image)sprite;
        return spriteImage.getWidth(null);
    }

    /* Returns a new SwingRenderInfo. This is needed for polymorphism. If we
     * were to switch render backends (from swing to something else), this
     * would make it so we don't have to change code in every Renderable.
     *
     * Parameters: name - the path of the sprite to load, realitive to the
     *                    asset directory.
     *
     * Return value: a SwingRenderInfo at (0.0, 0.0)
     */
    public static RenderInfo newRenderInfo(Object sprite, double x, double y)
    {

        return new SwingRenderInfo((Image)sprite, x, y);
    }

    public Game()
    {
        renderInfos = new SwingRenderInfo[0];
        binds = new ArrayList<Bind>();
        binds.add(new Bind(KeyEvent.VK_A, null, null));
    }
}
