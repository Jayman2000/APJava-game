import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements JavaArcade, KeyListener, ActionListener
{
    public Game()
    {
        // Input
        //  Keyboard
        binds = new ArrayList<Bind>();
        binds.add(new Bind(KeyEvent.VK_A, null, null));

        // Output
        //  Visual
        setPreferredSize(new Dimension(GameLogic.HEIGHT, GameLogic.WIDTH));
        setBackground(Color.BLACK);

        renderInfos = new SwingRenderInfo[0];

        // Processing
        //  Ticker
        timer = new Timer((int)Math.round(1.0/24.0 * 1000), this);
        timer.start();

        //  GameLogic
        server = new GameLogic();
    }

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
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(SwingRenderInfo r: renderInfos)
        {
            g.drawImage(r.getSprite(), (int)r.getX(), (int)r.getY(), null);
        }
    }

    //  Audio


    // Processing
    //  Ticker
    private Timer timer;

    // Called every tic
    public void actionPerformed(ActionEvent e)
    {
        OutputInfo result = server.update(null, timer.getDelay());
        if(result.visuals != null)
        {
            renderInfos = new SwingRenderInfo[result.visuals.length];
            for(int i = 0; i < result.visuals.length; i++)
                renderInfos[i] = (SwingRenderInfo)result.visuals[i];
        }

        repaint();
        // Work arround from https://stackoverflow.com/questions/33257540/java-window-lagging-on-ubuntu-but-not-windows-when-code-isnt-lagging#33258929
        if(System.getProperty("os.name").equals("Linux"))
            Toolkit.getDefaultToolkit().sync();
    }


    //  GameLogic
    private GameLogic server;


    //  AssetManager

    public static Image loadSprite(String name)
    {
        Image sprite;
        try
        {
            sprite = ImageIO.read(new File("assets\\" + name));
            return sprite;
        }
        catch(IOException e)
        {
            System.out.println("FATAL: Could not load sprite \"" + name + "\".");
        }

        System.exit(0);
        // This can never happen, but is needed prevent a compiler error.
        return null;
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
}
