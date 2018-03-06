/* A JavaArcade game that does nothing. For testing our test UI.
 *
 * Author: Jason Yundt
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class NullGame extends JPanel implements JavaArcade
{
    public NullGame()
    {
        super();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(640, 360));
    }
    public boolean running()
    {
        System.out.println("NullGame.running()");
        return false;
    }

    public void startGame()
    {
        System.out.println("NullGame.startGame()");
    }

    public String getGameName()
    {
        System.out.println("NullGame.getGameName()");
        return null;
    }

    public void pauseGame()
    {
        System.out.println("NullGame.pauseGame()");
    }

    public String getInstructions()
    {
        System.out.println("NullGame.getInstructions()");
        return null;
    }

    public String getCredits()
    {
        System.out.println("NullGame.getCredits()");
        return null;
    }

    public String getHighScore()
    {
        System.out.println("NullGame.getHighScore()");
        return null;
    }

    public void stopGame()
    {
        System.out.println("NullGame.stopGame()");
    }

    public int getPoints()
    {
        System.out.println("NullGame.getPoints()");
        return 0;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawString("NULL GAME", getWidth() / 2, getHeight() / 2);
    }
}
