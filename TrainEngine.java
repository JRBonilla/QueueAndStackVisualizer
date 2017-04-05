import java.awt.geom.* ;
import java.awt.* ;

/**
 Train Engine is a vehicle that can pull a chain of railcars
 */
public class TrainEngine extends Vehicle
{
    /**
     Constants
     */
    private static final double WIDTH = 35 ;
    private static final double UNIT = WIDTH / 5 ;
    private static final double LENGTH_FACTOR = 14 ; // length is 14U
    private static final double HEIGHT_FACTOR = 5 ; // height is 5U
    private static final double U_3 = 0.3 * UNIT ;
    private static final double U2_5 = 2.5 * UNIT ;
    private static final double U3 = 3 * UNIT ;
    private static final double U4 = 4 * UNIT ;
    private static final double U5 = 5 * UNIT ;
    private static final double U10 = 10 * UNIT ;
    private static final double U10_7 = 10.7 * UNIT ;
    private static final double U12 = 12 * UNIT ;
    private static final double U13 = 13 * UNIT ;
    private static final double U14 = 14 * UNIT ;

    // Default constructor
    public TrainEngine() {
        super();
    }

    // Constructor that sets the coordinates of the train engine.
    public TrainEngine(int x, int y) {
        super(x, y);
    }

    /**
     Draws the train engine
     @param g2 the graphics context
     */

    public void draw(Graphics2D g2)
    {
        // decide whether to implement getX() and getY() in this
        // class or in superclass
        int x1 = getX() ;
        int y1 = getY() ;

        // The wheels and hitch are set to the same color as the selection
        // color.

        Rectangle2D.Double hood = new Rectangle2D.Double(x1, y1 + UNIT,
                U3, U3 ) ;
        g2.setColor(Color.blue) ;
        g2.fill(hood) ;

        Rectangle2D.Double body = new Rectangle2D.Double(x1 + U3,
                y1,
                U10, U4) ;
        g2.setColor(Color.blue) ;
        g2.fill(body) ;

        Line2D.Double hitch = new Line2D.Double(x1 + U13,
                y1 + U2_5,
                x1 + U14,
                y1 + U2_5) ;
        g2.setColor(selectionColor) ;
        g2.draw(hitch) ;

        Ellipse2D.Double wheel1 = new Ellipse2D.Double(x1 + U_3,
                y1 + U4,
                UNIT, UNIT) ;
        g2.setColor(selectionColor) ;
        g2.fill(wheel1) ;

        Ellipse2D.Double wheel2 = new Ellipse2D.Double(x1 + 1.3 * UNIT,
                y1 + U4,
                UNIT, UNIT) ;
        g2.setColor(selectionColor) ;
        g2.fill(wheel2) ;

        Ellipse2D.Double wheel3 = new Ellipse2D.Double(x1 + 2.3 * UNIT,
                y1 + 4 * UNIT,
                UNIT, UNIT) ;
        g2.setColor(selectionColor) ;
        g2.fill(wheel3) ;

        Ellipse2D.Double wheel4 = new Ellipse2D.Double(x1 + U10_7,
                y1 + U4,
                UNIT, UNIT) ;
        g2.setColor(selectionColor) ;
        g2.fill(wheel4) ;

        Ellipse2D.Double wheel5 = new Ellipse2D.Double(x1 + U12,
                y1 + U4,
                UNIT, UNIT) ;
        g2.setColor(selectionColor) ;
        g2.fill(wheel5) ;

        Ellipse2D.Double wheel6 = new Ellipse2D.Double(x1 + 9.7 * UNIT,
                y1 + U4,
                UNIT, UNIT) ;
        g2.setColor(selectionColor) ;
        g2.fill(wheel6) ;

        // Set the bounding box of the train engine
        boundingBox = new Rectangle2D.Double(x1, y1, hood.getWidth() + body.getWidth() + UNIT, body.getHeight() + UNIT);

        // If the train engine has a trailer, draw it.
        if (hasTrailer()) getTrailer().draw(g2);
    }

    /**
     * Returns a string that describes the train engine.
     * @return a string describing the train engine.
     */
    public String toString() {
        return "Train Engine, x: " + getX() + ", y: " + getY();
    }

    /**
     * Returns a string that describes the train engine.
     * @param x the x location to check
     * @param y the y location to check
     * @return does this vehicle contains those coordinates.
     */
    @Override
    boolean isClicked(int x, int y) {
        return boundingBox.contains(x, y);
    }
}
