//Author: Jason Yundt, Timothy Retelle, Jason O’Dwyer

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements JavaArcade, KeyListener, ActionListener
{
    private final static int frameTime = (int)Math.round(1.0/60.0 * 1000); // 60 fps
    private BufferedReader reader;
    private FileWriter writer;

    public Game()
    {
        // Input
        //  Keyboard
        binds = new ArrayList<Bind>();
        setFocusable(true);
        addKeyListener(this);


        // Output
        //  Visual
        setPreferredSize(new Dimension(GameLogic.HEIGHT, GameLogic.WIDTH));
        setBackground(Color.BLACK);

        renderInfos = new SwingRenderInfo[0];

        // Processing
        //  Ticker
        timer = new Timer(frameTime, this);

        //  GameLogic
        server = new GameLogic();

        for(Bind b : server.getBinds())
        {
            binds.add(b);
        }
    }

    // Input
    //  JavaArcade (Author: Timmy Retelle)
    public void startGame()
    {
        timer.start();
    }

    public void pauseGame()
    {
        /* Stop timer */
        timer.stop();
    }

    public void stopGame()
    {
        /* Stop timer */
        /* Reset score */
        score = getPoints();
        timer.stop();
        if(currentSong != null)
            currentSong.stop();
        currentSong = null;
        server = new GameLogic();
    }


    //  Keyboard (Author: Jason Yundt)
    private ArrayList<Bind> binds;

    public Object[] getSignals()
    {
        ArrayList<Object> ret = new ArrayList<Object>(binds.size());

        for(Bind b : binds)
        {
            for(Object signal : b.getSignals())
            {
                ret.add(signal);
            }
        }

        return ret.toArray(new Object[ret.size()]);
    }

    // Methods required by KeyListener
    public void keyTyped(KeyEvent e) { /* Intentionally left empty */ }
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
    //  JavaArcade (Author: Timmy Retelle)

    private int score;

    public boolean running()
    {
        return timer.isRunning();
    }

    public String getInstructions(){
        return("Don't get hit by the ball as you use it to clear out the tiles! Use left and right to move, use Z to shoot the ball and bounce it into the tiles.");
    }

    public String getCredits(){
        return("A game by Jason, Jason, and Timmy.");
    }

    public String getGameName(){
        return("Yours!!!");
    }

    public String getHighScore()
    {
        try
        {
            reader = new BufferedReader(new FileReader(“/Users/JasonODwyer/Documents/APJava-game”)
        }
        catch(FileNotFoundException e)
        {
            e.printStacktrace();
        }

        String unparsed = “”+0;
        int score;
        int points = 5; //sample points variable, find real points variable later

        try
        {
            unparsed = reader.readLine();
            score = Integer.parseInt(unparsed);
            reader.close();
        }
        catch(IOException e)
        {
            return unparsed;
        }

        if(score < points)
        {
            try
            {
                writer = new FileWriter(/Users/JasonODwyer/Documents/APJava-game)
            }
            catch(IOException e);
            {
                e.printStackTrace();
            }
            try
            {
                writer.write(points+””;
                writer.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            return unparsed;
    }

    public int getPoints()
    {
        return score;
    }


    //  Visual (Author: Timmy Retelle)
    private SwingRenderInfo[] renderInfos;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(SwingRenderInfo r: renderInfos)
        {
            g.drawImage(r.getSprite(), r.getX(), r.getY(), null);
        }
    }

    //  Audio (Author: Jason Yundt)
    private AudioClip currentSong;

    private void changeSong(AudioClip next)
    {
        // Uncomment this when we have a song.
        /*if(currentSong != null)
            currentSong.stop();

        currentSong = next;
        currentSong.setCycleCount(AudioClip.INDEFINITE);
        currentSong.play();*/
    }

    // Processing
    //  Ticker (Author: Jason O'Dwyer)
    private Timer timer;

    // Called every tic
    public void actionPerformed(ActionEvent e)
    {
        OutputInfo result = server.update(getSignals(), timer.getDelay());

        renderInfos = new SwingRenderInfo[result.visuals.length];
        for(int i = 0; i < result.visuals.length; i++)
            renderInfos[i] = (SwingRenderInfo)result.visuals[i];

        score = result.score;

        if(result.song != null)
            changeSong((AudioClip)result.song);

        repaint();
        // Work arround from https://stackoverflow.com/questions/33257540/java-window-lagging-on-ubuntu-but-not-windows-when-code-isnt-lagging#33258929
        if(System.getProperty("os.name").equals("Linux"))
            Toolkit.getDefaultToolkit().sync();
    }


    //  GameLogic
    private GameLogic server;


    //  AssetManager (Author: Jason O'Dwyer

    public static Image loadSprite(String filename)
    {
        Image sprite;
        try
        {
            sprite = ImageIO.read(stringToFile(filename));
            return sprite;
        }
        catch(IOException e)
        {
            System.out.println("FATAL: Could not load sprite \"" + filename + "\".");
            System.exit(0);
        }

        // This can never happen, but is needed prevent a compiler error.
        return null;
    }

    public static AudioClip loadAudio(String filename)
    {
        return new AudioClip(stringToFile(filename).toURI().toString());
    }

    private static File stringToFile(String filename)
    {
        return new File("assets", filename);
    }

    /* Note: apparently Image.getWidth(), and getHeight() can return -1 if not
     * all of the image has been loaded yet. How do we prevent that from
     * happening?
     */
    public static int getWidthOfSprite(Object sprite)
    {
        Image spriteImage = (Image)sprite;
        return spriteImage.getWidth(null);
    }

    public static int getHeightOfSprite(Object sprite)
    {
        Image spriteImage = (Image)sprite;
        return spriteImage.getHeight(null);
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
