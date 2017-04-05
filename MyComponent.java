import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

/**
 * By: Jonathan Bonilla, 500640200
 */

// The component class of the program.
public class MyComponent extends JComponent {
    private ArrayList<Vehicle> vehicles;                // ArrayList which stores the vehicles for this class
    private Vehicle selected;                           // Vehicle object that stores the selected vehicle.
    private TrainEngine trainEngine;                    // The TrainEngine for the component.
    private StorageContainerQueue storageContainerQueue;// The StorageContainerQueue for the component.

    // The Component's MouseListener
    class MyComponentMouseListener implements MouseListener {
        // Stores the current mouse press.
        int press = 0;

        @Override
        public void mousePressed(MouseEvent e) {
            // Increment the current press by one.
            press++;

            if (press == 1) // For the first press, initialize the train engine and add it the ArrayList.
            {
                trainEngine = new TrainEngine(e.getX(), e.getY());
                vehicles.add(trainEngine);
            }
            else if (press > 1 && press < 7) // From the 2nd to 6th press, add a new RailCar to the vehicles list
            {
                vehicles.add(new RailCar(e.getX(), e.getY()));
            }
            else if (press == 7) // On the 7th press, create a new StorageContainerQueue.
            {
                storageContainerQueue = new StorageContainerQueue(e.getX(), e.getY());
            }
            else if (press > 7) // If the press is greater than 7, check for selection.
            {
                // Go through the vehicles list
                for (Vehicle vehicle : vehicles) {
                    // If the vehicle is clicked and it is not a trailer.
                    if (vehicle.isClicked(e.getX(), e.getY()) && !vehicle.isTrailer) {
                        selected = vehicle;        // Set selected to be the vehicle.
                        vehicle.setSelected(true); // Set the vehicle to selected.
                    }
                    // If the vehicle is not clicked, and it is not a trailer.
                    else if (!vehicle.isClicked(e.getX(), e.getY()) && !vehicle.isTrailer) {
                        /*
                        If there was a selected vehicle and it was not clicked, set
                        selected to null
                        */
                        if (selected != null && !selected.isClicked(e.getX(), e.getY()))
                            selected = null;
                        // Set the vehicle to not selected.
                        vehicle.setSelected(false);
                    }
                }
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // If selection is enabled, check for intersection.
            if (press > 7) {
                // First, get each vehicle.
                for (Vehicle first : vehicles) {
                    // Secondly, go through the rest of the vehicles.
                    for (Vehicle second : vehicles) {
                        // If the first =/= second, then check for intersection.
                        if (first != second) {
                            /*
                            If the first vehicle intersects the second, and first is not already a trailer,
                            and the second does not have a trailer, then set the second's trailer to be the first vehicle.
                            */
                            if (first.intersects(second) && !first.isTrailer && !second.hasTrailer()) {
                                second.setTrailer(first);
                                repaint();
                            }
                        }
                    }
                }
            }
        }

        // Resets the press count.
        public void resetPress() {
            press = 0;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    // Mouse motion listener for the component.
    class MyComponentMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // If there is a selected vehicle
            if (selected != null) {
                // Set the location of the selected vehicle to be the mouse position.
                selected.setLocation(e.getX(), e.getY());
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    }

    // Two private instances of the MouseListener and MouseMotionListener classes.
    private MyComponentMouseListener myComponentMouseListener;
    private MyComponentMouseMotionListener myComponentMouseMotionListener;

    // Constructor that simply intitializes everything.
    public MyComponent() {
        myComponentMouseListener = new MyComponentMouseListener();
        myComponentMouseMotionListener = new MyComponentMouseMotionListener();
        addMouseListener(myComponentMouseListener);
        addMouseMotionListener(myComponentMouseMotionListener);

        trainEngine = null;
        selected = null;
        vehicles = new ArrayList<>();
    }

    /*
    Clears the component by clearing the vehicle list, resetting the StorageContainerQueue to null, resetting the
    presses, and repainting.
    */
    public void clear() {
        vehicles.clear();
        storageContainerQueue = null;
        myComponentMouseListener.resetPress();
        repaint();
    }

    // Adds the selected vehicle to the front of TrainEngine trailers.
    public void addFirst() {
        // If there is a train engine
        if (trainEngine != null) {
            /*
            And if there was a vehicle selected that was not a TrainEngine,
            then add the vehicle to the front of the TrainEngine trailers.
            */
            if (selected != null && !(selected instanceof TrainEngine)) {
                // If the TrainEngine has a trailer, then push the old trailer.
                if (trainEngine.hasTrailer()) {
                    Vehicle oldTrailer = trainEngine.getTrailer(); // Store the old TrainEngine trailer.
                    trainEngine.setTrailer(selected); // Set the new TrainEngine trailer to selected vehicle.
                    Vehicle currentVehicle = selected; // Set the current vehicle to the selected vehicle.
                    while (currentVehicle.hasTrailer()) { // Goes through the selected vehicles trailer list and finds the last one.
                        currentVehicle = currentVehicle.getTrailer();
                    }
                    currentVehicle.setTrailer(oldTrailer); // Once selected's last trailer was found, set its trailer to the old trailer.
                }
                // Otherwise, simply add the trailer to the TrainEngine.
                else {
                    trainEngine.setTrailer(selected);
                }
                repaint();
            }
        }
    }

    // Adds the selected vehicle to the end of the TrainEngine's trailers.
    public void addLast() {
        // Only proceed if the TrainEngine was initialized.
        if (trainEngine != null) {
            // If there was a selected vehicle and it was not a TrainEngine,
            // add it the end of the list.
            if (selected != null && !(selected instanceof TrainEngine)) {
                // If the TrainEngine has a trailer, find the last one and set its trailer to selected vehicle.
                if (trainEngine.hasTrailer()) {
                    Vehicle currentTrailer = trainEngine.getTrailer();
                    while (currentTrailer.hasTrailer()) {
                        currentTrailer = currentTrailer.getTrailer();
                    }
                    currentTrailer.setTrailer(selected);
                    repaint();
                }
                // Otherwise, set TrainEngine's trailer to selected.
                else {
                    trainEngine.setTrailer(selected);
                    repaint();
                }
            }
        }
    }

    // Removes the first trailer from the TrainEngine and places it in a random location.
    public void removeFirst() {
        if (trainEngine != null) {
            // Generate a random x and y within the bounds of the window.
            Random random = new Random();
            int randomX = random.nextInt(MyFrame.FRAME_WIDTH);
            int randomY = random.nextInt(MyFrame.FRAME_HEIGHT);

            // If the TrainEngine has a trailer, remove it.
            if (trainEngine.hasTrailer()) {
                Vehicle firstTrailer = trainEngine.getTrailer(); // Store the first trailer.
                // If the first trailer has a trailer, set that trailer to be the TrainEngine's trailer
                if (firstTrailer.hasTrailer()) {
                    Vehicle secondTrailer = firstTrailer.getTrailer();
                    trainEngine.setTrailer(secondTrailer);
                    firstTrailer.setTrailer(null);
                }
                // Otherwise, just set TrainEngine's trailer to null.
                else {
                    trainEngine.setTrailer(null);
                }
                // Set the first trailer to no longer be a trailer, place it somewhere random, and deselect it.
                firstTrailer.setIsTrailer(false);
                firstTrailer.setLocation(randomX, randomY);
                firstTrailer.setSelected(false);
                repaint();
            }
        }
    }

    // Removes the last trailer from the TrainEngine.
    public void removeLast() {
        // If the TrainEngine was initialized, proceed.
        if (trainEngine != null) {
            // Generate a random x and y.
            Random random = new Random();
            int randomX = random.nextInt(MyFrame.FRAME_WIDTH - RailCar.BODY_WIDTH);
            int randomY = random.nextInt(MyFrame.FRAME_HEIGHT - RailCar.BODY_HEIGHT);

            // Only remove the last trailer if the TrainEngine has a trailer.
            if (trainEngine.hasTrailer()) {
                // Find the last trailer.
                Vehicle lastTrailer = trainEngine.getTrailer();
                while (lastTrailer.hasTrailer()) {
                    lastTrailer = lastTrailer.getTrailer();
                }

                // If the first trailer is not the last trailer, find the last trailer's lead trailer
                if (trainEngine.getTrailer().hasTrailer()) {
                    Vehicle leadTrailer = trainEngine.getTrailer();
                    while (leadTrailer.getTrailer() != lastTrailer) {
                        leadTrailer = leadTrailer.getTrailer();
                    }
                    // Set the lead trailer to no longer point to the last trailer.
                    leadTrailer.setTrailer(null);
                }
                // Otherwise just set the TrainEngine to no longer point to the last trailer.
                else {
                    trainEngine.setTrailer(null);
                }
                //Reset the last trailer.
                lastTrailer.setIsTrailer(false);
                lastTrailer.setLocation(randomX, randomY);
                lastTrailer.setSelected(false);
                repaint();
            }
        }
    }

    // Adds a container to the Queue.
    public void addContainer() {
        if (storageContainerQueue != null) {
            if (selected != null) {
                // If the selected vehicle has a storage container, remove it and add it to the Queue.
                if (selected.hasStorageContainer() && selected instanceof RailCar) {
                    storageContainerQueue.add(selected.getStorageContainer());
                    selected.setStorageContainer(null);
                }
                // Otherwise, check if the trailers have any storage containers and remove those.
                else {
                    if (selected.hasTrailer()) {
                        Vehicle trailer = selected.getTrailer();
                        while (!trailer.hasStorageContainer() && trailer.hasTrailer()) {
                            trailer = trailer.getTrailer();
                        }
                        if (trailer.hasStorageContainer()) {
                            storageContainerQueue.add(trailer.getStorageContainer());
                            trailer.setStorageContainer(null);
                        }
                    }
                }
            }
        }
        repaint();
    }

    // Removes a storage container from the Queue
    public void removeContainer() {
        if (storageContainerQueue != null) {
            if (selected != null) {
                // If the selected vehicle doesn't have a storage container, remove the container from the Queue and
                // add it to the vehicle.
                if (!selected.hasStorageContainer() && selected instanceof RailCar) {
                    StorageContainer removed = storageContainerQueue.remove();
                    selected.setStorageContainer(removed);
                }
                // Otherwise check it any of the trailers don't have any, and if so, add the container.
                else {
                    if (selected.hasTrailer()) {
                        Vehicle trailer = selected.getTrailer();
                        while (trailer.hasStorageContainer() && trailer.hasTrailer()) {
                            trailer = trailer.getTrailer();
                        }
                        if (!trailer.hasStorageContainer()) {
                            StorageContainer removed = storageContainerQueue.remove();
                            trailer.setStorageContainer(removed);
                        }
                    }
                }
            }
        }
        repaint();
    }

    /*
    Paints the component
    @param g the
    */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Go through the vehicles list and draw each vehicle that is not a trailer.
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isTrailer) {
                vehicle.draw(g2);
            }
        }

        // Draw the StorageContainerQueue if it was initialized.
        if (storageContainerQueue != null) storageContainerQueue.draw(g2);
    }
}