package gui;

import javax.swing.JFrame;

import startup.HospitalSystem;


/**
 * The frame for main menu for the hospital system.
 */
public class MainMenuFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Initialize the frame to display the possible operations.
     */
    public MainMenuFrame() {
        setTitle("Main Menu");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        MainMenuPanel panel = new MainMenuPanel();
        add(panel);
    }
    
    /**
     * A main to run the GUI version of the hospital system.
     */
    public static void main(String[] args) {
        HospitalSystem system = new HospitalSystem();
        system.initialize();
        MainMenuFrame frame = new MainMenuFrame();
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }


    public static final long serialVersionUID = 1;
}
