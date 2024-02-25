/**
 * StartController
 * A program for to register the startButton to start the game
 * @author Huzaifa Anwar
 * @since January 11th
 */

//package declaration 
package Summative;

//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartController implements ActionListener {

    private GolfMania model;
    private JButton startButton;

    /**
     * Constructor for the Controller that retreives button input from the GUI
     * 
     * @param someModel // this helps us set the model to send the information to
     *                  afterwords
     * @param aButton   // sets the target button
     */
    public StartController(GolfMania someModel, JButton aButton) {
        this.model = someModel;
        this.startButton = aButton;
    }

    /**
     * A void method used to get the data from the button and send back to the model
     * to start the game
     */
    public void actionPerformed(ActionEvent e) {

        // sends back to system
        model.startGame();

    }

}// ssalc
