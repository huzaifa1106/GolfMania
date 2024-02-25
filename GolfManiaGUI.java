/**
 * GolfManiaGUI
 * A program for the visual interface for the GolfMania class
 * @author Huzaifa Anwar
 * @since January 11th
 */

//package declaration
package Summative;

//import files

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class GolfManiaGUI extends JPanel {

    // model
    private GolfMania model;

    // main window
    private JFrame frame;

// StartUp Panel

    // image for startup screen
    private BufferedImage startUpPicture;
    private JLabel imageLabel;

    // startup button
    private JButton startButton;

    // panel to store all components for panel
    private JPanel startPanel;
    private JPanel sPanel;

    private BorderLayout L1 = new BorderLayout();

    // Difficulty Panel
    // Panel for selecting difficult
    private JPanel difficultySelect;

    // panels to store pictures
    private JPanel easyPanel = new JPanel();
    private JPanel moderatePanel = new JPanel();
    private JPanel hardPanel = new JPanel();
    private JPanel emptyPanel3;

    private JButton resumeButton;

    // panels to hold pictures together
    private JPanel panels = new JPanel();

    // border layout for difficulty panel
    private BorderLayout L3 = new BorderLayout();

    // pictures
    private BufferedImage EasyImage;
    private BufferedImage ModerateImage;
    private BufferedImage HardImage;

 // Game Layout

    // Declarations and Initializations
    private JPanel slidersPanel;
    private JPanel buttonsPanel;

    //button for shooting
    private JButton shootButton; //for shooting
    private JButton exitButton; //for exiting

    //layout managers
    private BorderLayout L5 = new BorderLayout();
    private BoxLayout L6;
    private FlowLayout L7 = new FlowLayout();

//GamePanel
    
    private JPanel gamePanel; //panel
    private GolfMap testMap; //GameMap

    //slider and power controller
    private JSlider power;
    private JSlider angle;

    //labels for the controller
    private JLabel powerLabel;
    private JLabel angleLabel;

    // panels
    private JPanel E1, E2, E3; // maps easy
    private JPanel M1, M2, M3, M4, M5; // maps moderate
    private JPanel H1, H2, H3, H4, H5, H6, H7; // maps hard

    //different courses
    private GolfMap testMap1;
    private GolfMap testMap2;
    private GolfMap testMap3;
    private GolfMap testMap4;

    // Table View
    private static JScrollPane scrollPane; // to add headers
    private static JPanel tableView; // panel to store table
    private static int[][] sb; // score board
    private static Object[][] scoreData; // score data


    //winner
    JPanel lostPanel, winPanel, drawPanel;
    BufferedImage lostImage, winImage, drawImage;

    /**
     * Creating a view class to display and update the model data on as it interact
     * with the user
     * 
     * @param someModel  //importing model data to output to display
     * 
     */

    public GolfManiaGUI(GolfMania someModel) {
        super();
        this.model = someModel;
        frame = new JFrame();
        this.model.setGUI(this);

        this.testMap = new GolfMap();
        this.TableScreen();
        this.startScreen();
        this.difficultyScreen();
        this.initGamePanels();
        this.registerAllControllers();
        this.update();

    }

    /**
     * A void method used to intalize and set all easyMaps
     * 
     * @param currentRound // helps decide which map with which round
     */
    public void easyLayout(int currentRound) {

        // Varies according to the round
        switch (currentRound) {
            //Easy Map #1
            case 1:
                System.out.println("First Round");
                E1 = this.createGame(testMap1);
                frame.setContentPane(E1);
                break;

            //Easy Map #2    
            case 2:
                System.out.println("Second Round");
                E2 = this.createGame(testMap2);

                frame.setContentPane(E2);
                break;

            //Easy Map #3    
            case 3:
                System.out.println("Third Round");
                E3 = this.createGame(testMap1);

                frame.setContentPane(E3);
                break;

            default:
                break;
        }

    }// end of easyLayout

    /**
     * A void method used to intalize and set all moderateMaps
     * 
     * @param currentRound // helps decide which map with which round
     */
    public void moderateLayout(int currentRound) {

        // Varies according to the round
        switch (currentRound) {
            
            //Moderate Map #1
            case 1:
            
            System.out.println("First Round");

                M1 = this.createGame(testMap2);
                frame.setContentPane(M1);
                break;

            //Moderate Map #2
            case 2:

            System.out.println("Second Round");

                M2 = this.createGame(testMap3);
                frame.setContentPane(M2);
                break;

            //Moderate Map #3
            case 3:

            System.out.println("Third Round");

                M3 = this.createGame(testMap2);
                frame.setContentPane(M3);
                break;

            //Moderate Map #4
            case 4:

            System.out.println("Fourth Round");

                M4 = this.createGame(testMap4);
                frame.setContentPane(M4);
                break;

            //Moderate Map #5
            case 5:

            System.out.println("Fifth Round");

                M5 = this.createGame(testMap3);
                frame.setContentPane(M5);
                break;

            default:
                break;
        }

    }// end of moderateLayout

    /**
     * A void method used to initalize and set all hardMaps
     * 
     *  @param currentRound // helps decide which map with which round
     */
    public void hardLayout(int currentRound) {

        // Varies according to the round
        switch (currentRound) {
           //Hard Map #1
            case 1:

                System.out.println("First Round");
                H1 = this.createGame(testMap1);
                frame.setContentPane(H1);
                break;

        //Hard Map #2
            case 2:

                System.out.println("Second Round");
                H2 = this.createGame(testMap3);
                frame.setContentPane(H2);
                break;

        //Hard Map #3
            case 3:

                System.out.println("Third Round");
                H3 = this.createGame(testMap2);
                frame.setContentPane(H3);
                break;

        //Hard Map #4
            case 4:
                
                System.out.println("Fourth Round");
                H4 = this.createGame(testMap4);
                frame.setContentPane(H4);
                break;

        //Hard Map #5
            case 5:
                System.out.println("Fifth Round");
                H5 = this.createGame(testMap3);
                frame.setContentPane(H5);
                break;

        //Hard Map #6        
            case 6:

                System.out.println("Sixth Round");
                H6 = this.createGame(testMap4);
                frame.setContentPane(H6);
                break;

        //Hard Map #7
            case 7:
               
                 System.out.println("7th Round");
                H6 = this.createGame(testMap4);
                frame.setContentPane(H7);
                break;

            default:
                break;
        }

    }// end of hard layout

    /**
     * A void method used to initalize all GamePanels
     */
    private void initGamePanels() {
        // Initialize your game panels for easy
        E1 = new JPanel();
        E2 = new JPanel();
        E3 = new JPanel();
        // Initialize your game panels for moderate
        M1 = new JPanel();
        M2 = new JPanel();
        M3 = new JPanel();
        M4 = new JPanel();
        M5 = new JPanel();
        // Initialize your game panels for hard
        H1 = new JPanel();
        H2 = new JPanel();
        H3 = new JPanel();
        H4 = new JPanel();
        H5 = new JPanel();
        H6 = new JPanel();
        H7 = new JPanel();
    }// end of initGamePanels

    /**
     * A void method used to help draw the start screen
     */
    public void startScreen() {

        // initalization
        startButton = new JButton("START GAME"); // startButton
        startPanel = new JPanel();
        sPanel = new JPanel();
        imageLabel = new JLabel(); // for picture

        // loading image
        BufferedImage startUpImage = loadImage("programs/summative/StartUpImage.png");

        // layout for the button and picture
        OverlayLayout L2 = new OverlayLayout(sPanel);

        // Set layout for the panels
        startPanel.setLayout(L1);
        sPanel.setLayout(L2);

        // Scaling image for display
        int scaledWidth = (int) (startUpImage.getWidth() * 0.6);// making smaller for display
        int scaledHeight = (int) (startUpImage.getHeight() * 0.6);        ImageIcon imageIcon = new ImageIcon(
        startUpImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);

        // Helping center buttons to the center of picture
        imageLabel.setAlignmentY(0.795f);
        imageLabel.setAlignmentX(0.430f);

        // Add the imageLabel containing picture to the sPanel
        sPanel.add(imageLabel, BorderLayout.PAGE_START);

        // Prefered Size
        startButton.setPreferredSize(new Dimension(200, 80));

        // Add button components to sPanel
        sPanel.add(startButton, BorderLayout.LINE_END);

        // Add the sPanel to the startPanel
        startPanel.add(sPanel, BorderLayout.CENTER);

        // Set the frame size to match image dimensions
        frame.setSize(scaledWidth, scaledHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }// end of StartScreen

    /**
     * A void method used to help draw the difficultyScreen
     */
    public void difficultyScreen() {

        // initatization
        difficultySelect = new JPanel();
        // layout for pictures
        GridLayout L4 = new GridLayout(2, 3, 10, 10); // Increased space between components

        resumeButton = new JButton("Resume Game"); // Button to resume game
        emptyPanel3 = new JPanel(); // panel that storesResumeButton

        // gamePanel initatization
        shootButton = new JButton("Shoot");
        exitButton = new JButton("Exit");
        angle = new JSlider(JSlider.VERTICAL, 0, 180, 0);
        power = new JSlider(JSlider.VERTICAL, 0, 200, 0);

        // setting layouts
        difficultySelect.setLayout(L3);
        panels.setLayout(L4);

        // loading images for each difficulty option
        EasyImage = loadImage("programs/Summative/EasyLevelImage.png");
        ModerateImage = loadImage("C:programs/Summative/ModerateLevelImage.png");
        HardImage = loadImage("programs/Summative/HardLevelImage.png");

        // Fancy Header for Select Difficulty
        JLabel headerLabel = new JLabel("SELECT DIFFICULTY");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headerLabel.setForeground(new Color(102, 0, 51)); // Dark red color
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        difficultySelect.add(headerLabel, BorderLayout.PAGE_START);

        // adding small headers below the image
        easyPanel.add(createImageLabel(EasyImage, 170, 250));
        easyPanel.add(createTextLabel("Easy"));
        moderatePanel.add(createImageLabel(ModerateImage, 170, 250));
        moderatePanel.add(createTextLabel("Moderate"));
        hardPanel.add(createImageLabel(HardImage, 170, 250));
        hardPanel.add(createTextLabel("Hard"));

        // adding and sizing button
        resumeButton.setPreferredSize(new Dimension(150, 50));
        emptyPanel3.add(resumeButton);

        // adding to panel
        panels.add(new JPanel()); // Empty panel for spacing
        panels.add(easyPanel);
        panels.add(new JPanel()); // Empty panel for spacing
        panels.add(moderatePanel);
        panels.add(emptyPanel3); // Empty panel for spacing
        panels.add(hardPanel);

        // adding panels to main panel
        difficultySelect.add(panels, BorderLayout.CENTER);

        // frame adjusments
        frame.setSize(600, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }// end of difficultyScreen

    /**
     * A void method create gamePanel
     * 
     * @param testMap // The Map used to play the game
     */
    public JPanel createGame(GolfMap testMap) {
        // initalization
        gamePanel = new JPanel();
        slidersPanel = new JPanel();
        testMap = new GolfMap();
        L6 = new BoxLayout(slidersPanel, BoxLayout.Y_AXIS);
        gamePanel.setLayout(L5);


        // Set specific values for power slider (0-10)
        power.setMajorTickSpacing(30);
        power.setPaintTicks(true);
        power.setPaintLabels(true);

        // Set specific values for angle slider (0-180)

        angle.setMajorTickSpacing(30);
        angle.setPaintTicks(true);
        angle.setPaintLabels(true);

        // Create labels for sliders
        powerLabel = new JLabel("Power");
        angleLabel = new JLabel("Angle");

        // Create a panel for sliders and set its layout to BoxLayout (vertical)
        slidersPanel.setLayout(L6);
        slidersPanel.add(powerLabel);
        slidersPanel.add(power);
        slidersPanel.add(angleLabel);
        slidersPanel.add(angle);

        // Panel for buttons and set its layout to FlowLayout
        buttonsPanel = new JPanel(L7);
        buttonsPanel.setLayout(L7);
        buttonsPanel.add(shootButton);
        buttonsPanel.add(exitButton);

        //Set Dim
        exitButton.setPreferredSize(new Dimension(75,26));

        // Add Map to the center of gamePanel
        gamePanel.add(testMap, BorderLayout.CENTER);

        // Add tsliders panel to the right of gamePanel
        gamePanel.add(slidersPanel, BorderLayout.EAST);

        // Add the buttons panel to the bottom of gamePanel
        gamePanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Set preferred sizes for sliders
        power.setPreferredSize(new Dimension(55, 150));
        angle.setPreferredSize(new Dimension(55, 150));

        return gamePanel;
    }// end of gamePanel

    /**
     * A void method used create the table Screen
     */
    public void TableScreen() {

        sb = model.getScoreBoard();

        JTable mainTable = new JTable();
        String[] columnNames = { "Round", "Player", "Robot" };

        // set number of rounds according to difficulty

        // 3 Rounds
        if (model.getDifficulty() == 'e') {
            scoreData = createScoreData(sb, 3);
        }

        // 5 Rounds
        else if (model.getDifficulty() == 'm') {
            scoreData = createScoreData(sb, 5);
        }

        // 7 Rounds
        else if (model.getDifficulty() == 'h') {
            scoreData = createScoreData(sb, 7);
        }

        // Default case
        else {
            scoreData = new Object[0][3];
        }

        mainTable = new JTable(scoreData, columnNames);

        // Create the scroll pane and add the table to it
        scrollPane = new JScrollPane(mainTable);

        tableView = new JPanel();
        tableView.add(scrollPane);
        tableView.setPreferredSize(new Dimension(300,150));

    }// end of TableScreen

    /**
     * A void method used to help register all the controllers
     */
    public void registerAllControllers() {
        this.registerStart();
        this.registerPanels();
        this.registerShooters();

    }// end of registerAllControllers

    /**
     * A void method used to help register the startup to the model class
     */
    public void registerStart() {

        // register start
        StartController startC = new StartController(this.model, this.startButton);
        this.startButton.addActionListener(startC);

        // register resume
        ResumeGameController startR = new ResumeGameController(this.model, this.resumeButton);
        this.resumeButton.addActionListener(startR);

        EndGameController startE = new EndGameController(model, exitButton);
        this.exitButton.addActionListener(startE);

    }// end of registerStart

    /**
     * A void methodto help register the JPanels when clicked on to the model
     * class
     */
    public void registerPanels() {

        DifficultyController easyC = new DifficultyController(this.model, this.easyPanel, 'e');
        this.easyPanel.addMouseListener(easyC);

        DifficultyController moderateC = new DifficultyController(this.model, this.moderatePanel, 'm');
        this.moderatePanel.addMouseListener(moderateC);

        DifficultyController hardC = new DifficultyController(this.model, this.hardPanel, 'h');
        this.hardPanel.addMouseListener(hardC);

    }// end of registerPanels

    /**
     * A void method used to help register the controllers to the main model
     */
    public void registerShooters() {

        ShooterController shootingC = new ShooterController(this.model, this.power, this.angle);
        this.shootButton.addActionListener(shootingC);

    }// end of registerShooters

    /**
     * Loads an image from the specified file path.
     * 
     * @param imagePath The path to the image file.
     * 
     **/
    private static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            System.out.println("Image Not Found");
            return null;
        }
    }// end of load image

    /**
     * Create a JLabel with the specified BufferedImage and size.
     *
     * @param image  The BufferedImage to be displayed.
     * @param width  The width of the label.
     * @param height The height of the label.
     * @return JLabel with the image.
     */
    private static JLabel createImageLabel(BufferedImage image, int width, int height) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return new JLabel(icon);

    }// end of createImageLabel


    /**
     * Creates a JLabel with text inside
     *
     * @param text //the text you want to display
     * @return JLabel with text.

     */

    private static JLabel createTextLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setHorizontalAlignment(JLabel.CENTER);// adjusted using swing constant
        return label;
    }// end of createTextLabel

    /**
     * A void method used to update the GUI
     */
    public void update() {

        // start screen
        if (!model.getStartStatus()) {
            frame.setContentPane(startPanel);
            frame.setTitle("STARTUP");
        }

        // difficulty select
        else if (model.getStartStatus() && model.getDifficulty() == 'z') {
            frame.setContentPane(difficultySelect);
            frame.setTitle("Select Difficulty");
        }

        // gameGUI
        else if (model.getStartStatus() && model.getDifficulty() != 'z' && !model.getRoundStatus() && !model.getGameOver()) {

            int currentRound = model.getCurrentRound();
            char difficulty = model.getDifficulty();

            frame.setTitle("GOLF MANIA");

            switch (difficulty) {
                case 'e':
                    easyLayout(currentRound);
                    break;
                case 'm':
                    moderateLayout(currentRound);
                    break;
                case 'h':
                    hardLayout(currentRound);
                    break;
                default:
                    break;
            }

            frame.setSize(1050, 600);
            frame.setLocationRelativeTo(null);
        }

        // Table 
        else if (model.getStartStatus() && model.getDifficulty() != 'z' && model.getRoundStatus() && !model.getGameOver()) {
            JFrame frame2 = new JFrame();
            JPanel table = tableView;
            frame2.setContentPane(table);
            frame2.pack();
            frame2.setTitle("Score Table");
            frame2.setVisible(true);
            frame.repaint();
            
            try {
                // Introduce a delay before starting the next round
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frame2.setVisible(false);

        }
        //Winner Output
        else if (model.getStartStatus() && model.getDifficulty() != 'z' && model.getGameOver()){

            //initalizing panels 
            lostPanel = new JPanel();
            winPanel = new JPanel();
            drawPanel = new JPanel();

            //if robot won
            if(model.getWinner() ==  2){
                lostImage = loadImage("C:programs/Summative/youLost.jpg");
                lostPanel.add(createImageLabel(lostImage, 600, 360));
                frame.setContentPane(lostPanel);
            }
            //if player won
            else if (model.getWinner() ==1){
                winImage = loadImage("C:programs/Summative/youWon.jpg");
                winPanel.add(createImageLabel(winImage, 600, 360));
                frame.setContentPane(winPanel);
            }
            //if draw
            else if (model.getWinner() ==0){
                drawImage = loadImage("C:programs/Summative/draw.png");
                drawPanel.add(createImageLabel(drawImage, 600, 360));
                frame.setContentPane(drawPanel);

            }
            frame.setTitle("Who Won??");

        }   

        frame.repaint();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }// end of update

    /**
     * A void method used to help shoot te ball in the GolfMap
     * 
     * @angle //angle the ball is shot with
     * @power //power the ball is shot with
     */
    public void shoot(int angle, int power) {

        System.out.println("Values reach");
        System.out.println(angle);
        System.out.println(power);
        testMap.shoot(angle, power);
        testMap.repaintScreen();

    }// end of shoot

    /**
     * A method that returns a boolean value from the GolfMap Class
     * @return Returns boolean of shot success.

     */
    public boolean getShotStatus() {

        return testMap.checkForWin();
    }// end of getShotStatus

    /**
     * A method that creates the ScoreData for the table
     * @param sb // currentScoreBoard from model
     * @param rounds// amount of rounds in the round for scoreTable
     */
    private static Object[][] createScoreData(int[][] sb, int rounds) {

        Object[][] scoreData = new Object[rounds][3];

        // setting score values
        for (int r = 0; r < rounds; r++) {
            scoreData[r][0] = "Round " + (r + 1);

            // if current round less than total rounds
            if (r < sb.length) {

                // values available for round
                if (sb[r].length >= 2) {
                    scoreData[r][1] = sb[r][0];
                    scoreData[r][2] = sb[r][1];
                }
                // values not available for round
                else {
                    scoreData[r][1] = 0;
                    scoreData[r][2] = 0;
                }
            }

            else {
                scoreData[r][1] = 0;
                scoreData[r][2] = 0;
            }
        }

        return scoreData;
    }// end of createScoreData

    /**
     * A method that returns a GolfMap to the model class to initalize GolfMap
     * @return //returns map value for the Golf Mania Class
     */
    public GolfMap getMap() {
        return testMap;
    }// end of getMapClass

}// ssalc