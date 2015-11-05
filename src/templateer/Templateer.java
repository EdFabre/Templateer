/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author rlsokel
 */
public class Templateer extends JFrame {

    // Static class vars.
    private static JFrame guiFrame;
    private static JPanel guiPanel, choiceGroup_p;
    private static JScrollPane textAreaScrollView, reqAreaScrollView, conAreaScrollView;
    private static TitledBorder title_b, description_b,
            requirements_b, contact_b, choiceGroup_b;
    private static JTextField title, syllabusLink;
    private static JTextArea description, requirements, contact, prerequisites;
    private static GridBagConstraints constraints;
    private static ButtonGroup choiceGroup;
    private static JRadioButton guiRadioYes, guiRadioNo;
    private static JButton submit;
    
    /**
     * Method inits all components and prepares gui.
     */
    private static void initComponents() {
        
        // Creates the frame and panel; appends gridbag layout.
        guiFrame = new JFrame("Templateer");
        guiPanel = new JPanel(new GridBagLayout());
        guiPanel.setPreferredSize(new Dimension(320, 320));
        guiFrame.add(guiPanel);
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create text fields, labels, and buttons; button group is also created.
        title = new JTextField();
        title.setColumns(16);
        title_b = new TitledBorder("Title");
        title.setBorder(title_b);
        
        description = new JTextArea();
        description.setColumns(16);
        description_b = new TitledBorder("Details/Description");
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        
        requirements = new JTextArea();
        requirements.setColumns(16);
        requirements_b = new TitledBorder("Requirements");
        requirements.setLineWrap(true);
        requirements.setWrapStyleWord(true);
        
        contact = new JTextArea();
        contact.setColumns(16);
        contact_b = new TitledBorder("Contact Info");
        contact.setLineWrap(true);
        contact.setWrapStyleWord(true);
        
        guiRadioYes = new JRadioButton("Yes");
        guiRadioYes.setActionCommand("yes");
        guiRadioYes.addActionListener(new JobSelect());
        guiRadioNo = new JRadioButton("No");
        guiRadioNo.setActionCommand("no");
        guiRadioNo.addActionListener(new JobSelect());
        guiRadioNo.setSelected(true);
        choiceGroup = new ButtonGroup();
        choiceGroup.add(guiRadioYes);
        choiceGroup.add(guiRadioNo);
        choiceGroup_b = new TitledBorder("Job Posting");
        choiceGroup_p = new JPanel();
        choiceGroup_p.setLayout(new FlowLayout());
        choiceGroup_p.add(guiRadioYes);
        choiceGroup_p.add(guiRadioNo);
        choiceGroup_p.setBorder(choiceGroup_b);
        
        textAreaScrollView = new JScrollPane(description,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textAreaScrollView.setBorder(description_b);
        
        reqAreaScrollView = new JScrollPane(requirements,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reqAreaScrollView.setBorder(requirements_b);
        
        conAreaScrollView = new JScrollPane(contact,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        conAreaScrollView.setBorder(contact_b);
        
        submit = new JButton("Submit");
        submit.setMnemonic(KeyEvent.VK_ENTER);
        submit.setActionCommand("submit");
        submit.addActionListener(new Submit());
        
        
        
        // Configure layout constraints and add components to panel.
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        
        constraints.gridx = 1;
        constraints.gridy = GridBagConstraints.RELATIVE;
        
        guiPanel.add(title, constraints);
        guiPanel.add(textAreaScrollView, constraints);
        guiPanel.add(reqAreaScrollView, constraints);
        guiPanel.add(conAreaScrollView, constraints);
        guiPanel.add(choiceGroup_p, constraints);
        guiPanel.add(submit, constraints);
        
        reqAreaScrollView.setVisible(false);
        conAreaScrollView.setVisible(false);
        
        // Pack comps into panel and display.
        guiFrame.pack();
        guiFrame.setVisible(true);
    }
    
    /**
     * Method grabTitleInput takes in a ref to a text field, which is then parsed for
     *   text and returned.
     * 
     * @param inputField
     * @return 
     */
    protected static String grabTitleInput(JTextField inputField) {
        return inputField.getText();
    }
    
    /**
     * Method grabDescInput takes in a ref to a text area, which is then parsed,
     *   split into an array, and returned.
     * 
     * @param inputArea
     * @return 
     */
    protected static String[] grabDescInput(JTextArea inputArea) {
        return inputArea.getText().split("\n");
    }
    
    /**
     * Class extends ActionListener and listens for events originating from the
     *   radio buttons.
     * 
     */
    private static class JobSelect implements ActionListener{
        
        // Constructor.
        JobSelect() {};
        
        @Override
        public void actionPerformed(ActionEvent aE) {
            
            // Switch through choices: determines layout of GUI.
            switch(aE.getActionCommand()) {
                case "yes":
                    reqAreaScrollView.setVisible(true);
                    conAreaScrollView.setVisible(true);
                    guiFrame.pack();
                    guiFrame.setVisible(true);
                    break;
                    
                case "no":
                    reqAreaScrollView.setVisible(false);
                    conAreaScrollView.setVisible(false);   
                    guiFrame.pack();
                    guiFrame.setVisible(true);                    
                    break;
                    
                default:
                    break;
            }
        }
    }
    
    private static class Submit implements ActionListener{
        
        // Constructor.
        Submit() {}
        
        @Override
        public void actionPerformed(ActionEvent aE) {
            
            // Submit. Plain and simple.
            /* Method: grabInput */
            /* EnclosingBox.encloseBox() */
            /* Provide dialog box to print result to */
            HTMLPrinter printer;
            
            if(guiRadioYes.isSelected()) {
                printer = new HTMLPrinter(guiFrame, Encloser.encloseBox(
                        grabTitleInput(title),
                        Encloser.encloseJob(
                                grabDescInput(description),
                                grabDescInput(requirements),
                                grabDescInput(contact))));
            } else {
            printer = new HTMLPrinter(guiFrame, Encloser.encloseBox(
                    grabTitleInput(title),
                    grabDescInput(description)));
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initComponents();
    }
    
}
