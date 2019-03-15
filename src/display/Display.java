package display;
/**
 * Different options for displaying data must have different display methods
 */
public interface Display
{
    /**
     * A method that allows different implementations
     * in order to achieve different ways of display
     * @param object the object to be displayed
     */
    void display(Object object);
}