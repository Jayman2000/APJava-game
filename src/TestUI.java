/* A user interface similar to the one that Mrs. D will be using to play our
 * games.
 *
 * Author: Jason Yundt
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class TestUI
{
    final static JavaArcade game = new NullGame();

    public static void main(String[] args)
    {
        JFrame mainWindow = new JFrame("AP Java Arcade - Test UI");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainPane = mainWindow.getContentPane();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));

        mainPane.add((JPanel)game);

        Container buttonPane = new Container();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

        JButton running = new JButton("running()");
        running.addActionListener(new ButtonAction(ButtonAction.Action.RUNNING));
        buttonPane.add(running);

        JButton start = new JButton("startGame()");
        start.addActionListener(new ButtonAction(ButtonAction.Action.START));
        buttonPane.add(start);

        JButton name = new JButton("getGameName()");
        name.addActionListener(new ButtonAction(ButtonAction.Action.NAME));
        buttonPane.add(name);

        JButton pause = new JButton("pauseGame()");
        pause.addActionListener(new ButtonAction(ButtonAction.Action.PAUSE));
        buttonPane.add(pause);

        JButton instructions = new JButton("getInstructions()");
        instructions.addActionListener(new ButtonAction(ButtonAction.Action.INSTRUCTIONS));
        buttonPane.add(instructions);

        JButton credits = new JButton("getCredits()");
        credits.addActionListener(new ButtonAction(ButtonAction.Action.CREDITS));
        buttonPane.add(credits);

        JButton highScore = new JButton("getHighScore()");
        highScore.addActionListener(new ButtonAction(ButtonAction.Action.HIGH_SCORE));
        buttonPane.add(highScore);

        JButton stop = new JButton("stopGame()");
        stop.addActionListener(new ButtonAction(ButtonAction.Action.STOP));
        buttonPane.add(stop);

        JButton points = new JButton("getPoints()");
        points.addActionListener(new ButtonAction(ButtonAction.Action.POINTS));
        buttonPane.add(points);

        mainPane.add(buttonPane);

        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    private static class ButtonAction implements ActionListener
    {
        public enum Action
        {
            RUNNING,
            START,
            NAME,
            PAUSE,
            INSTRUCTIONS,
            CREDITS,
            HIGH_SCORE,
            STOP,
            POINTS
        }

        private Action action;
        public ButtonAction(Action a)
        {
            action = a;
        }

        public void actionPerformed(ActionEvent e)
        {
            switch(action)
            {
                case RUNNING:
                    System.out.println("game.running():");
                    System.out.println("return value: " + game.running());
                    break;
                case START:
                    System.out.println("game.startGame():");
                    game.startGame();
                    break;
                case NAME:
                    System.out.println("game.getGameName():");
                    System.out.println("return value: " + game.getGameName());
                    break;
                case PAUSE:
                    System.out.println("game.pauseGame():");
                    game.pauseGame();
                    break;
                case INSTRUCTIONS:
                    System.out.println("game.getInstructions():");
                    System.out.println("return value: " + game.getInstructions());
                    break;
                case CREDITS:
                    System.out.println("game.getCredits():");
                    System.out.println("return value: " + game.getCredits());
                    break;
                case HIGH_SCORE:
                    System.out.println("game.getHighScore():");
                    System.out.println("return value: " + game.getHighScore());
                    break;
                case STOP:
                    System.out.println("game.stopGame():");
                    game.stopGame();
                    break;
                case POINTS:
                    System.out.println("game.getPoints():");
                    System.out.println("return value: " + game.getPoints());
                    break;
            }
            System.out.println();
        }
    }
}
