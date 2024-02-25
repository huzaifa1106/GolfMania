/**
 * DifficultyController
 * A program to register the controller to select the difficulty
 * 
 * @author Huzaifa Anwar
 * @since January 11th
 */


//package declarations
package Summative;

//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DifficultyController implements MouseListener {

    private GolfMania model; // main model
    private JPanel panel; // desired panel
    private char difficulty; //assigned difficulty value

    /**
     * Constructor for the Controller that retrieves panel input from the GUI
     *
     * @param someModel // this helps us set the model to send the information
     *                  afterward
     * @param p         // sets the target panel
     * @param c         // what each panel represents
     */
    public DifficultyController(GolfMania someModel, JPanel p, char c) {
        this.model = someModel;
        this.panel = p;
        this.difficulty = c;
    }

    /**
     * A void method used to get the data from the panel and send back to the model
     */
    public void mouseClicked(MouseEvent e) {
        model.setDifficulty(difficulty);
    }

    // Other MouseListener methods (implementation can be empty if not needed)
    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
}//ssalc
  