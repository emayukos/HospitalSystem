package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import commands.AddDoctorCommand;


/**
 * The panel to obtain data for the creation of a doctor, and to cause the doctor to be created.
 */
public class DoctorAddPanel extends JPanel {
    /* Declare the components of the panel needed by inner classes. */

    /**
     * A text area to be used to display an error if one should occur.
     */
    JTextArea error = null;

    /**
     * A panel for the entry of the name of a new doctor.
     */
    ValueEntryPanel namePanel;
    
    /**
     * The field for the check box.
     */
	JCheckBox surgeonCheckBox;

    /**
     * Create the panel to obtain data for the creation of a doctor, and to cause the doctor to be
     * created.
     */
    public DoctorAddPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        // add a label with a prompt to enter the doctor data
        JLabel prompt = new JLabel("Enter Doctor Information");
        prompt.setMaximumSize(prompt.getPreferredSize());
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of the doctor's name
        namePanel = new ValueEntryPanel("Name");
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        add(namePanel);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        add(Box.createVerticalGlue());

        // add a panel with surgeon check box
		surgeonCheckBox = new JCheckBox("Surgeon");
		add(surgeonCheckBox); // add single check box to panel
		surgeonCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT); 
        add(Box.createVerticalGlue());
        

        // add a button to submit the information and create the doctor
        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(submitButton.getPreferredSize());
        add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitListener()); // only listener in this class (our focus)
        add(Box.createVerticalGlue());
    }

    /**
     * The class listening for the press of the submit button. It accesses the name and specialty,
     * and uses them to add a new doctor to the system.
     */
    private class SubmitListener implements ActionListener {
        /**
         * When the submit button is pressed, access the name and specialty entered, and use
         * them to add a new Doctor to the system.
         */
        public void actionPerformed(ActionEvent event) {
            if (error != null) {
                // remove error from the previous submission
                remove(error);
                error = null;
            }
            String name = namePanel.getValueAsString(); // get doctors name
            Boolean isSurgeon = surgeonCheckBox.isSelected(); // get surgeon status
            AddDoctorCommand addDoctor = new AddDoctorCommand(); 
            addDoctor.addDoctor(name, isSurgeon); // add doctor to system
            if (addDoctor.wasSuccessful()) {
                getTopLevelAncestor().setVisible(false);
            } else {
                error = new JTextArea(SplitString.at(addDoctor.getErrorMessage(), 40));
                error.setMaximumSize(error.getPreferredSize());
                add(error);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(Box.createVerticalGlue());
                revalidate(); // redraw the window as it has changed
            }
        }
    }

    public static final long serialVersionUID = 1;
}
