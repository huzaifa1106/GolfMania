/**
 * ShooterController
 * A program to register the controller to shoot the golf ball
 * @author Huzaifa Anwar
 * @since January 11th
 */

//package declaration
package Summative;

//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ShooterController implements ActionListener {

    private GolfMania model; //model class
    private JSlider power; //slider for power
    private JSlider angle; // slider for angle

    /**
     * Constructor for the Controller that retreives sliders input from the GUI
     * 
     * @param someModel // this helps us set the model to send the information to
     *                  afterwords
     * @param p         // sets the Slider for power
     * @param a         // sets the Slider for angle
     */
    public ShooterController(GolfMania someModel, JSlider p, JSlider a) {
        this.model = someModel;
        this.power = p;
        this.angle = a;
    }

    /**
     * A void method used to get the data from the button and send back to the model
     */
    public void actionPerformed(ActionEvent e) {

        //setting values
        int PV = power.getValue();
        int AV = angle.getValue();

        // sending shot to system
        model.registerShot(PV, AV);
    }

}//ssalc
