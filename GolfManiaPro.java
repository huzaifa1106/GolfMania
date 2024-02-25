/**
 * GolfManiaPro
 * A program for the main method to run all the classes
 * @author Huzaifa Anwar
 * @since January 11th
 */

//package declaration
 package Summative;

//import declaration
import javax.swing.*;


public class GolfManiaPro {

     /**
     * Our Main Method to run the game GOLFMANIA
     * 
     * @param args
     */
    public static void main(String[] args) {

        //declarations and initalizations
        JFrame frame = new JFrame();     //small panel at the side
        GolfMania data = new GolfMania();
        GolfManiaGUI v = new GolfManiaGUI(data);

        // frame adjustments
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(v);
        frame.pack();
        frame.setTitle("Hello");

    }// naim

}// ssalc
 