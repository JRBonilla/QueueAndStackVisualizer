import java.awt.*;

/**
 * By: Jonathan Bonilla, 500640200
 */

// Class that creates a storage container with properties.
public class StorageContainer {

    // Dimension and properties
    private static final int CONTAINER_WIDTH = 20;
    private static final int CONTAINER_HEIGHT = CONTAINER_WIDTH;
    private final Color color = Color.GREEN;
    private int x, y;
    private String label;
    private Rectangle container;

    /**
     * Constructor
     * @param label the label for this storage container.
    */
    public StorageContainer(String label) {
        this.x = 0;
        this.y = 0;
        this.label = label;
    }

    /**
     * Second constructor
     * @param x the x coordinate
     * @param y the y coordinate
     * @param label the label for this storage container
     */
    public StorageContainer(int x, int y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    void draw(Graphics2D g2) {
        container = new Rectangle(x, y, CONTAINER_WIDTH, CONTAINER_HEIGHT);
        g2.setColor(color);
        g2.draw(container);
        g2.setColor(Color.BLACK);
        g2.drawString(label, x + (CONTAINER_WIDTH / 2) - 4, y + (CONTAINER_HEIGHT / 2) + 4);
    }

    /**
     * Returns this containers label
     * @return this containers lable
     */
    public String getLabel() {
        return label;
    }

    /**
     * The toString method for the container
     * @return a string describing the storage container.
     */
    public String toString() {
        return "Label: " + getLabel();
    }

    /**
     * Returns the x coordinate
     * @return the x coord.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate
     * @return y the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the location of this container on the screen.
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A static instance of the storage container width.
     * @return the container width.
     */
    public static int getWidth() {
        return CONTAINER_WIDTH;
    }

    /**
     * A static instance of the storage container height
     * @return the height of the container
     */
    public static int getHeight() {
        return CONTAINER_HEIGHT;
    }

}
