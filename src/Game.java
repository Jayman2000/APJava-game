import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

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


    //  Audio



    // Processing
    //  Ticker


    //  GameLogic


    //  AssetManager

    // Returns a new image that can be used in SwingRenderInfos.
    public static Image loadSprite(String name)
    {
        // Stub
        return null;
    }

    /* Returns a new SwingRenderInfo. This is needed for polymorphism. If we
     * were to switch render backends (from swing to something else), this
     * would make it so we don't have to change code in every Renderable.
     */
    public static RenderInfo newRenderInfo(Object sprite)
    {
        // Stub
        return null;
    }

    public Game()
    {
        binds = new ArrayList<Bind>();
        binds.add(new Bind(KeyEvent.VK_A, null, null));
    }
}
