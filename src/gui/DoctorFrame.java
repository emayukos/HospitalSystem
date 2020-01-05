package gui;

import javax.swing.JFrame;

import containers.DoctorSetAccess;
import entities.Doctor;



/**
 * The frame for the window to display the information for a doctor, and accept operations on the
 * doctor.
 */
public class DoctorFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame to display the information for a doctor.
     * 
     * @param name doctor's name
     * @precond name of doctor exists in the system 
     */
    public DoctorFrame(String name) {
        Doctor doctor = DoctorSetAccess.dictionary().get(name);
        if (doctor != null) {
            setTitle("Dr. " + name);
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(doctor);
            add(panel);
        } else
            throw new RuntimeException("Invalid name " + name);
    }

    public static final long serialVersionUID = 1;
}
