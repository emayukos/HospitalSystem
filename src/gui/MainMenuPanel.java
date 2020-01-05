package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The panel for the main menu. There are four button options. One to navigate to patient operations, another
 * to navigate to doctor operations, a button to to access the ward's information, and the quit button to terminate
 * the project.
 */
public class MainMenuPanel extends JPanel {
    /**
     * Create the panel for the main menu. There are four button options. One to 
     * navigate to patient operations, another to navigate to doctor operations, 
     * a button to to access the ward's information, and the quit button to 
     * terminate the project.
     */
    public MainMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // vertical arrangement for components in this panel
        add(Box.createVerticalGlue());
        
        // add a button to display patient operations window
        JButton patientOpButton = new JButton("Patient Operations");
        patientOpButton.setMaximumSize(patientOpButton.getPreferredSize());
        add(patientOpButton);
        patientOpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // code to display patient ops when button is pressed
        patientOpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                PatientOpsFrame frame = new PatientOpsFrame(); // display patient operations window
                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());
        
        // add a button to display doctor operations window
        JButton doctorOpButton = new JButton("Doctor Operations");
        doctorOpButton.setMaximumSize(doctorOpButton.getPreferredSize());
        add(doctorOpButton);
        doctorOpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // code to display doctor ops when button is pressed
        doctorOpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DoctorOpsFrame frame = new DoctorOpsFrame(); // display doctor operations window
//                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());
        
        // add a button to display ward information window
        JButton wardInfoButton = new JButton("Ward Information");
        wardInfoButton.setMaximumSize(wardInfoButton.getPreferredSize());
        add(wardInfoButton);
        wardInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wardInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                WardFrame frame = new WardFrame(); // display ward info window
//                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());

        
        // add a button to terminate or quit the project (other windows only have button to exit the window)
        final JButton quitButton = new JButton("Quit"); 
        quitButton.setMaximumSize(quitButton.getPreferredSize());
        add(quitButton);
        quitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                getTopLevelAncestor().setVisible(false); // this terminates the window(s)
            }
        });
        add(Box.createVerticalGlue());
    }

    public static final long serialVersionUID = 1;
}
