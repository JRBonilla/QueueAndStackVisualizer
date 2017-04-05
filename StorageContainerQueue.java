import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
/**
 * By: Jonathan Bonilla, 500640200
 */

// Class which stores and manipulates the Queue of storage containers.
public class StorageContainerQueue {

    // Dimensions of the base.
    private final int BASE_WIDTH = 33;
    private final int BASE_HEIGHT = 13;
    private final int SPACING = 2;
    private int x, y;
    private Rectangle base;

    // The Queue of storage containers
    private Queue<StorageContainer> storageContainers;

    // Default constructor that sets and x and y to 0.
    public StorageContainerQueue() {
        this.x = 0;
        this.y = 0;
        storageContainers = generateStorageContainers();
    }

    /**
    Constructor which specifies which x and y coordinate to set it to.
    @param x the x coordinate.
    @param y the y coordinate.
    */
    public StorageContainerQueue(int x, int y) {
        this.x = x;
        this.y = y;
        storageContainers = generateStorageContainers();
    }

    /**
    Generates a queue of storage containers with labels A-E, and places them in the correct location.
    @return the generated storage containers.
    */
    public Queue<StorageContainer> generateStorageContainers() {
        Queue<StorageContainer> storageContainers = new LinkedList<>();

        // Generates storage containers from A-E
        for (char label = 'A'; label <= 'E'; label++) {
            storageContainers.add(new StorageContainer(Character.toString(label)));
        }

        // Set the locations of each storage container.
        int storageContainerX = (x + BASE_WIDTH / 2) - (StorageContainer.getWidth() / 2);
        int storageContainerY = y - (StorageContainer.getHeight() + SPACING);
        for (StorageContainer storageContainer : storageContainers) {
            storageContainer.setLocation(storageContainerX, storageContainerY);
            storageContainerY -= (StorageContainer.getHeight() + SPACING);
        }

        return storageContainers;
    }

    /**
    Add a specific storage container to the Queue
    @param added the storage container to add
    */
    public void add(StorageContainer added) {
        int addedX = (x + BASE_WIDTH / 2) - (StorageContainer.getWidth() / 2);
        int addedY = y - ((StorageContainer.getHeight() + SPACING) * (storageContainers.size() + 1));
        added.setLocation(addedX, addedY);
        storageContainers.add(added);
    }

    /**
    Remove the first storage container in the Queue
    @return the removed container.
    */
    public StorageContainer remove() {
        if (storageContainers.size() > 0) {
            StorageContainer removed = storageContainers.remove();
            for (StorageContainer s : storageContainers) {
                s.setLocation(s.getX(), s.getY() + (StorageContainer.getHeight() + SPACING)); // // TODO: Fix spacing.
            }
            return removed;
        }
        return null;
    }

    /**
    Draw the base and the containers.
    @param g2 the graphics to draw to
    */
    public void draw(Graphics2D g2) {
        for (StorageContainer storageContainer : storageContainers) {
            storageContainer.draw(g2);
        }

        base = new Rectangle(x, y, BASE_WIDTH, BASE_HEIGHT);
        g2.setColor(Color.BLACK);
        g2.fill(base);
        g2.draw(base);
    }
}
