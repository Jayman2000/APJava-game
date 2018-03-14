/* Compatibility layer for JavaArcade. When we created Game.java we didn't
 * known that we needed to have a class called UserPanel that has a constructor
 * that takes in a width and a height.
 *
 * Author: Jason Yundt
 */

public class UserPanel extends Game
{
    public UserPanel(int width, int height)
    {
        super();
        System.out.println("WARNING: width and height are ignored.");
    }
}
