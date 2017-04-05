import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * By: Jonathan Bonilla, 500640200
 */

// The superclass of RailCar and TrainEngine.
abstract public class Vehicle {
    // Instance Variables
    private int x, y;                                   // X and Y coordinates.
    private boolean selected = false;                   // The selection of the current vehicle.
    public boolean isTrailer = false;                   // Stores whether or not this vehicle is a trailer.
    private Vehicle trailer = null;                     // The trailer of this vehicle.
    protected StorageContainer storageContainer = null; // The storage container for this vehicle.
    protected Rectangle2D.Double boundingBox;           // The bounding box of this vehicle.
    protected Color selectionColor = Color.black;       // The selection color of this vehicle.

    // Default constructor: sets x and y to 0.
    public Vehicle() {
        x = 0;
        y = 0;
    }

    /**
    Constructor: Sets this vehicles coordinates to the coordinates specified.
    @param x the x coordinate
    @param y the y coordinate.
    */
    public Vehicle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
    Draw method
    @param g2 the graphics to draw to
    */
    public void draw(Graphics2D g2) {}

    /**
     * Returns the x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
    Checks if a vehicle is intersecting with another vehicle. Returns false by default.
    @param other the vehicle to check.
    */
    public boolean intersects(Vehicle other) {
        return false;
    }

    /**
     * Returns the bounding box for the vehicle
     * @return the bounding box
     */
    public Rectangle2D.Double getBoundingBox() {
        return boundingBox;
    }

    /**
    Sets the location of the vehicle and its trailer.
    @param x the desired x location
    @param y the desired y location
    */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        if (hasTrailer())
            getTrailer().setLocation(getX() + (int) getBoundingBox().getWidth(), getY());
    }

    /**
    Checks if the vehicle is clicked or not.
    @param x the x location to check
    @param y the y location to check
    */
    abstract boolean isClicked(int x, int y);

    /**
     * Returns if the vehicle is selected.
     * @return the selected state of this vehicle
     */
    public boolean isSelected() {
        return selected;
    }

    /**
    Sets the selection and selection color.
    @param selection the selection for this vehicle
    */
    public void setSelected(boolean selection) {
        selected = selection;
        if (selected == true)
            setSelectionColor(Color.RED);
        else if (selected == false)
            setSelectionColor(Color.BLACK);
        setTrailerSelection();
    }

    /**
     * Returns the selection color.
     * @return the selection color
     */
    public Color getSelectionColor() {
        return selectionColor;
    }

    /**
    Sets the selections color.
    @param selectionColor the selection color for this vehicle
    */
    public void setSelectionColor(Color selectionColor) {
        this.selectionColor = selectionColor;
    }

    /**
     * Returns the trailer of the vehicle.
     * @return this vehicle's trailer
     */
    public Vehicle getTrailer() {
        return this.trailer;
    }

    /*
    Sets the trailer of the vehicle.
    @param trailer the trailer of this vehicle.
    */
    public void setTrailer(Vehicle trailer) {
        if (trailer != null) {
            trailer.setIsTrailer(true);
            trailer.setLocation(getX() + (int) getBoundingBox().getWidth(), getY());
            this.trailer = trailer;
            setTrailerSelection();
        } else {
            this.trailer = null;
        }
    }

    /* Sets the selection of the trailers to be the same as this vehicles. */
    public void setTrailerSelection() {
        Vehicle currentVehicle = this;
        while (currentVehicle.hasTrailer()) {
            currentVehicle.getTrailer().setSelected(selected);
            currentVehicle.getTrailer().setSelectionColor(currentVehicle.selectionColor);
            currentVehicle = currentVehicle.getTrailer();
        }
    }

    /**
     * Returns whether or not this vehicle has a trailer.
     * @return is vehicle null or not
     */
    public boolean hasTrailer() {
        return this.getTrailer() != null;
    }

    /**
    Sets this vehicle is be/not be a trailer.
    @param b the trailer-hood of this vehicle.
    */
    public void setIsTrailer(boolean b) {
        isTrailer = b;
    }

    /**
     *  Returns this vehicles storage container.
     * @return the storage container.
     */
    public StorageContainer getStorageContainer() {
        return storageContainer;
    }

    /**
     * Sets this vehicles storage container.
     * @param storageContainer the storage container for this vehicle
     */
    public void setStorageContainer(StorageContainer storageContainer) {}

    /**
     * Returns if this vehicle has a storage container.
     * @return is getStorageContainer null or not
     */
    public boolean hasStorageContainer() {
        return getStorageContainer() != null;
    }
}
