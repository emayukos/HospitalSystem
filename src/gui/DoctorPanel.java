package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import commands.DropAssocCommand;
import commands.AssignDoctorCommand;
import entities.Doctor;


/**
 * The panel to display the information for a doctor, and accept operations on the doctor. The
 * panel gives the doctors name and specialty (if any). Options are given to the user for
 * adding a patient to the patients of the doctor, accessing a specific patient of the doctor, 
 * and removing a patient from the patients of the doctor.
 */
public class DoctorPanel extends JPanel {
    /**
     * Create the panel to display the doctors information and accept operations on the doctor.
     * 
     * @param doctor the doctor whose information is to be displayed and on whom operations can be
     *        done
     */
    public DoctorPanel(Doctor doctor) {
        /*
         * The creation of the panel is placed in another method as it needs to be invoked whenever
         * the patient information of the doctor is changed.
         */
        build(doctor);
    }

    /**
     * Fill in the panel to display the doctors information and accept operations on the doctor.
     * 
     * @param doctor the doctor whose information is to be displayed and on whom operations can be
     *        done
     */
    private void build(Doctor doctor) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("Name: " + doctor.getName())); 
        String specialty = ""; // must initialize
        if (doctor instanceof entities.Surgeon) { // instanceof keyword lets us check if an object is a certain class type (returns a boolean) 
        	specialty = "Surgeon";
        }
        else {
        	specialty = "None";
        }
        add(new JLabel("Specialty: " + specialty)); // needs to output either Surgeon or None

        // add a patient to the patients of this doctor
        // don't need a new class (just use a method)
        JPanel addPatientPanel = addPatientPanel(doctor);
        add(addPatientPanel);
        addPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addPatientPanel.setMaximumSize(addPatientPanel.getPreferredSize());
        
        
        // access a specific patient of the doctor. 
        // should open the same patient’s window as accessing a specific patient from the patient operations window
        PatientAccessPanel accessPanel = new PatientAccessPanel();
        // need to check if patient is a patient of the doctor (need to check this for remove patient too)!! (can't do this in PatientAccessPanel b/c it's also used in patient window)
        add(accessPanel);
        accessPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        accessPanel.setMaximumSize(accessPanel.getPreferredSize());
          
        // remove a patient from the patients of this doctor
        JPanel removePatientPanel = removePatientPanel(doctor);
        add(removePatientPanel);
        removePatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        removePatientPanel.setMaximumSize(removePatientPanel.getPreferredSize());
    
        
        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }


    /**
     * A panel to add a doctor-patient association for this doctor. The panel has a prompt to enter
     * the patient's health number, and a field to enter the health number.
     * 
     * @param doctor the current doctor
     * @return a panel to associate a new patient with this doctor
     */
    private JPanel addPatientPanel(final Doctor doctor) {
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.add(new JLabel("Add patient"));
        final JTextField textField = new JTextField(10);
        addPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	int healthNumber = Integer.parseInt(textField.getText()); // convert text from textfield to int
                AssignDoctorCommand addAssoc = new AssignDoctorCommand();
                addAssoc.assignDoctor(doctor.getName(), healthNumber);
                if (addAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, addAssoc.getErrorMessage());
                }
            }
        });
        return addPatientPanel;
    }
    
    
    /**
     * A panel to remove a doctor-patient association for this doctor. The panel has a prompt to enter
     * the patient's health number, and a field to enter the health number.
     * 
     * @param doctor the current doctor
     * @return a panel to associate a new patient with this doctor
     */
	private JPanel removePatientPanel(final Doctor doctor) {
        JPanel removePatientPanel = new JPanel();
        removePatientPanel.add(new JLabel("Remove patient"));
        final JTextField textField = new JTextField(10);
        removePatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int healthNumber = Integer.parseInt(textField.getText()); // convert text from textfield to int
                DropAssocCommand addAssoc = new DropAssocCommand();
                addAssoc.dropAssociation(doctor.getName(), healthNumber);
                if (addAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, addAssoc.getErrorMessage());
                }
            }
        });
        return removePatientPanel;
	}

    public static final long serialVersionUID = 1;
}
