/* Represents a keybind.
 *
 * Author: Jason Yundt
 */

import java.util.ArrayList;

public class Bind
{
    public final int key; // What key corresponds with this bind
    public final Object downSignal; // Emited while the key is down
    public final Object pressedSignal; // Emited when the key is first pressed

    private boolean down; // Weather or not to emit downSignal
    private boolean justPressed; //  Weather or not to emit pressedSignal

    public Bind(int key, Object pressedSignal, Object downSignal)
    {
        this.key = key;
        this.pressedSignal = pressedSignal;
        this.downSignal = downSignal;
    }

    public void press()
    {
        if (!down)
        {
            down = true;
            justPressed = true;
        }
    }

    public void release()
    {
        down = false;
        justPressed = false;
    }

    public Object[] getSignals()
    {
        ArrayList<Object> ret = new ArrayList<Object>(2);

        if (down)
        {
            ret.add(downSignal);
        }
        if (justPressed)
        {
            ret.add(pressedSignal);
            justPressed = false;
        }

        return ret.toArray();
    }
}
