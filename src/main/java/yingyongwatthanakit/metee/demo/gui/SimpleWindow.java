package yingyongwatthanakit.metee.demo.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;

/**
 * SimpleWindow is a class that extends JFrame and demonstrates the basic setup of a GUI application using Swing.
 * It includes initialization and configuration of a window with a confirm button.
 * The window layout is structured with a main panel that hosts a button panel on the south side,
 * which in turn contains the confirm button. This class showcases the creation, arrangement,
 * and display of GUI components using the Swing library.
 */
public class SimpleWindow extends JFrame {

    protected JButton confirmButton;
    protected JPanel buttonPanel, mainPanel;

    /**
     * Constructs a new SimpleWindow with a specified title.
     *
     * @param title The title of the window.
     */
    public SimpleWindow(String title) {
        super(title);
    }

    /**
     * Initializes the GUI components of the window.
     * This method creates a confirm button, a button panel with FlowLayout,
     * and a main panel with BorderLayout. It is essential to call this method
     * before adding components to the window.
     */
    public void initializeComponents() {
        confirmButton = new JButton("Confirm");
        buttonPanel = new JPanel(new FlowLayout());
        mainPanel = new JPanel(new BorderLayout());
    }

    /**
     * Adds the initialized components to the window.
     * Specifically, it adds the confirm button to the button panel,
     * and then adds the button panel to the south region of the main panel.
     * Finally, the main panel is added to the window. This method should be called after initializing the components.
     */
    public void addComponents() {
        buttonPanel.add(confirmButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    /**
     * Sets the features of the window frame, including its default close operation,
     * visibility, packing behavior, and location. This method configures the window
     * to close on exit, to become visible immediately upon calling, to pack its components
     * according to their preferred sizes, and to center the window relative to the screen.
     */
    public void setFrameFeatures() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Creates and displays the GUI. This static method encapsulates the process of
     * creating a SimpleWindow instance, initializing its components, adding them to the frame,
     * and setting the frame's features. It is designed to run on the Event Dispatch Thread.
     */
    public static void createAndShowGUI() {
        SimpleWindow window = new SimpleWindow("Simple Window");
        window.initializeComponents();
        window.addComponents();
        window.setFrameFeatures();
    }

    /**
     * The entry point of the application. This method invokes the createAndShowGUI
     * method on the Swing Event Dispatch Thread, ensuring thread-safe operation of the GUI.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleWindow::createAndShowGUI);
    }

}
