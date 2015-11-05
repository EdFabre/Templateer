package templateer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * The HTMLPrinter class is a simple class designed to produce a pop-up
 *   window that displays formatted HTML. String is to be placed in between
 *   starting and ending 'ul' tag.
 *
 * @author rlsokel
 */
public class HTMLPrinter {
    
    // Default constructor.
    HTMLPrinter(JFrame frame, String content) {
        JTextArea textArea = new JTextArea(content);
        JOptionPane.showMessageDialog(frame, textArea);
    }
}
