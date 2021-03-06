package display;

import java.io.Serializable;

/**
 * Offers the ability to display results as text.
 */
public class DisplayText implements Display, Serializable
{
    private static final long serialVersionUID=1L;

    /**
     * Displays required object as text
     * @param o the object that is going to be displayed on the screen
     */
    public void display(Object o)
    {
        System.out.print(o);
    }
}