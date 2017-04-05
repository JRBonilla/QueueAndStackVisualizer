import java.awt.* ;
import java.awt.geom.* ;

/**
 This class describes a vehicle that looks like a flatbed
 railcar.  The railcar should be assigned a unique currentRailCar
 displayed on its body. The railcar should have variable and
 methods to allow it to be linked to another vehicle (consider
 whether this variable and associated methods should be
 inherited). This railcar should also have variables and
 methods so that a storage container can be loaded and unloaded
 Add other variables and methods you think are necessary.
 */

public class RailCar extends Vehicle
{

    public static final int UNIT = 10 ;
    public static final int U6 = 6 * UNIT ;
    public static final int U5 = 5 * UNIT ;
    public static final int U4 = 4 * UNIT ;
    public static final int U3 = 3 * UNIT ;
    public static final int U2 = 2 * UNIT ;
    public static final int U15 = UNIT + UNIT / 2 ;
    public static final int U05 =  UNIT / 2 ;
    public static final int BODY_WIDTH = U3 ;
    public static final int BODY_HEIGHT = U2 ;

    private static int currentRailCar = 1;              // Stores the last RailCar number.
    private final int RAILCAR_NUMBER = currentRailCar++;// Sets this RailCar's number and increments it by one.
    private Rectangle2D.Double body;

    // Constructor
    public RailCar() {
        super();
    }

    /**
     * Constructor
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public RailCar(int x, int y) {
        super(x, y);
    }

    /**
     Draw the rail car
     @param g2 the graphics context
     */
    public void draw(Graphics2D g2)
    {
        // think about whether getX() and getY() should be inherited
        // or defined in this class
        int xLeft = getX() ;
        int yTop = getY() ;

        boundingBox = new Rectangle2D.Double(xLeft, yTop + UNIT, U6 + U05, UNIT + UNIT);

        body = new Rectangle2D.Double(xLeft, yTop + UNIT, U6, UNIT);
        Ellipse2D.Double frontTire = new Ellipse2D.Double(xLeft + UNIT, yTop + U2, UNIT, UNIT);
        Ellipse2D.Double rearTire = new Ellipse2D.Double(xLeft + U4, yTop + U2, UNIT, UNIT);

        // the right end of the hitch
        Point2D.Double r5
                = new Point2D.Double(xLeft + U6, yTop + U15);
        // the left end of the hitch
        Point2D.Double r6
                = new Point2D.Double(xLeft + U6 + U05, yTop + U15);

        Line2D.Double hitch = new Line2D.Double(r5, r6);

        g2.setColor(selectionColor);
        g2.draw(body);
        g2.draw(hitch);
        g2.draw(frontTire);
        g2.draw(rearTire);
        g2.draw(body) ;
        // think about whether getNumber() should be inherited or
        // defined in this class
        g2.drawString("" + getNumber(), xLeft + U2, yTop + U2) ;

        // If this RailCar has a trailer, draw it
        if (hasTrailer())
            getTrailer().draw(g2);
        // If this RailCar has a StorageContainer, set its location and draw it.
        if (hasStorageContainer()) {
            storageContainer.setLocation(getX() + ((int) body.getWidth() / 2) - ((int) StorageContainer.getWidth() / 2), getY() - UNIT - 1);
            storageContainer.draw(g2);
        }
    }

    /**
     * Returns a string describing the RailCar
     * @return the string describing the RailCar
     */
    public String toString() {
        return "RailCar #" + getNumber() + ", x: " + getX() + ", y: " + getY();
    }

    /**
     * Returns whether or not this RailCar intersects another RailCar
     * @param other the vehicle to check.
     * @return does this RailCar intersect another RailCar and is it to the left of the other RailCar
     */
    public boolean intersects(Vehicle other) {
        return this.getBoundingBox().intersects(other.getBoundingBox()) && this.getX() > other.getX();
    }

    /**
     * Sets the storage container of this RailCar and sets the container's location.
     * @param storageContainer the storage container for this vehicle
     */
    public void setStorageContainer(StorageContainer storageContainer) {
        if (storageContainer != null) {
            this.storageContainer = storageContainer;
            this.storageContainer.setLocation(getX() + ((int) body.getWidth() / 2) - ((int) StorageContainer.getWidth() / 2), getY() - UNIT - 1);
        } else {
            this.storageContainer = null;
        }
    }

    /**
     * Returns if this RailCar was clicked or not
     * @param x the x location to check
     * @param y the y location to check
     * @return does this RailCar contain x and y?
     */
    @Override
    boolean isClicked(int x, int y) {
        return boundingBox.contains(x, y);
    }

    /**
     * Returns this RailCar's number
     * @return the RailCar's number
     */
    public int getNumber() {
        return RAILCAR_NUMBER;
    }
}
    
    
    