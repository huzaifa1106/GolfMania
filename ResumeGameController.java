/**
 * ResumeGameController
 * A program for to register the resumeButton to start the game
 * @author Huzaifa Anwar
 * @since January 11th
 */

//package declaration
package Summative;

//import declaration
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ResumeGameController implements ActionListener {

    private GolfMania model;
    private JButton resumeButton;

    /**
     * Constructor for the Controller that retreives resume game button input from
     * the GUI
     * 
     * @param someModel // this helps us set the model to send the information to
     *                  afterwords
     * @param aButton   // sets the target button
     */
    public ResumeGameController(GolfMania someModel, JButton aButton) {
        this.model = someModel;
        this.resumeButton = aButton;
    }

    /**
     * A void method used to information from the button and send back to the model
     * to resume the game
     */
    public void actionPerformed(ActionEvent e) {

        // sends back to system
        model.resumeGame(); // resume game
    }

}// ssalc
