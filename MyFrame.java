import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * By: Jonathan Bonilla, 500640200
 */

// Class that creates the main frame for the program and sets up
// the JMenuBar, Component, and JMenuItem's.
public class MyFrame extends JFrame {
    // Frame dimensions
    public static final int FRAME_WIDTH = 525;
    public static final int FRAME_HEIGHT = 550;

    // The main component for the program.
    private MyComponent myComponent;

    // Constructor
    public MyFrame() {
        // Initialize component
        myComponent = new MyComponent();

        // Set up the JMenuBar
        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createQueueMenu());
        jMenuBar.add(createListMenu());

        // Set the size, location, and resizability of the window.
        getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        add(myComponent);
    }

    /**
     * Creates the file menu
     * @return the file menu
     */
    public JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem.addActionListener(new NewItemListener());
        exitItem.addActionListener(new ExitItemListener());

        fileMenu.add(newItem);
        fileMenu.add(exitItem);
        return fileMenu;
    }

    /**
     * Creates the Queue menu
     * @return the queue menu
     */
    public JMenu createQueueMenu() {
        JMenu queueMenu = new JMenu("Queue");

        JMenuItem removeItem = new JMenuItem("Remove");
        JMenuItem addItem = new JMenuItem("Add");

        removeItem.addActionListener(new RemoveItemListener());
        addItem.addActionListener(new AddItemListener());

        queueMenu.add(removeItem);
        queueMenu.add(addItem);
        return queueMenu;
    }

    /**
     * Creates the list menu
     * @return the list menu
     */
    public JMenu createListMenu() {
        JMenu listMenu = new JMenu("List");

        JMenuItem addFirstItem = new JMenuItem("Add First");
        JMenuItem addLastItem = new JMenuItem("Add Last");
        JMenuItem removeFirstItem = new JMenuItem("Remove First");
        JMenuItem removeLastItem = new JMenuItem("Remove Last");

        addFirstItem.addActionListener(new AddFirstItemListener());
        addLastItem.addActionListener(new AddLastItemListener());
        removeFirstItem.addActionListener(new RemoveFirstItemListener());
        removeLastItem.addActionListener(new RemoveLastItemListener());

        listMenu.add(addFirstItem);
        listMenu.add(addLastItem);
        listMenu.add(removeFirstItem);
        listMenu.add(removeLastItem);
        return listMenu;
    }

    // The ActionListener for the "New" item in the file menu.
    // Clears the component.
    class NewItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myComponent.clear();
        }
    }

    // The ActionListener for the "Exit" item in the file menu.
    // Exits the program.
    class ExitItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // The ActionListener for the "Remove" item in the Queue menu
    // Removes a container from the selected vehicle.
    class RemoveItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myComponent.removeContainer();
        }
    }

    // The ActionListener for the "Add" item in the Queue menu
    // Adds a container to the selected vehicle.
    class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myComponent.addContainer();
        }
    }

    // The ActionListener for "Add First" item in the List menu
    // Adds the selected vehicle to front of the train's trailers.
    class AddFirstItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myComponent.addFirst();
        }
    }


    // The ActionListener for "Add Last" item in the List menu
    // Adds the selected vehicle to end of the train's trailers.
    class AddLastItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myComponent.addLast();
        }
    }


    // The ActionListener for "Remove First" item in the List menu
    // Removes the train's first trailer.
    class RemoveFirstItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myComponent.removeFirst();
        }
    }

    // The ActionListener for "Remove Last" item in the List menu
    // Removes the train's last trailer.
    class RemoveLastItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myComponent.removeLast();
        }
    }
}
