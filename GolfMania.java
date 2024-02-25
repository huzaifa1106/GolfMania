/**
 * GolfMania
 * A program for the main model the GolfMania game
 * @author Huzaifa Anwar
 * @since January 11th
 */

//package declaration 
package Summative;

//import declaration
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class GolfMania extends Object {

    private GolfManiaGUI view; // GUI
    private boolean startGame; // gameStart
    private boolean gameOver; // game finished

    private boolean endRound; // round ended
    private char difficulty; // difficulty level
    private int[][] scoreBoard; // score board to store the scores
    private int totalRounds; // total number of rounds
    private int currentRounds; // current round
    private int playerTurn; // determines which player
    private int winner; // winner

    private int roundCheck;

    private boolean robotShotTaken; // player shot taken
    private boolean playerShotTaken; // robot shot taken
    private Random random; // randomized numbers
    private boolean successfulShot; // shot successful or not

    /**
     * Creating a model class to save information and update the GUI as the user
     * interacts
     * 
     */
    public GolfMania() {
        startGame = false;
        gameOver = false;
        endRound = false;
        successfulShot = false;
        totalRounds = 0;
        currentRounds = 1;
        playerTurn = 1;
        difficulty = 'z';
        roundCheck = 0;
        random = new Random();
    }

    /**
     * A void method used to help start the game
     */
    public void startGame() {
        startGame = true;
        this.update();
    }// end of start game

    /**
     * A void method used to help set the difficulty of the game
     * 
     * @param c // level of difficulty
     */
    public void setDifficulty(char c) {
        difficulty = c;
        this.adjustValues(c); // adjust # of total rounds
        scoreBoard = new int[totalRounds][2];
        this.update();
    }// end of set difficulty

    /**
     * A void method used to help resume the game
     */
    public void resumeGame() {
        readText(); // read text from txt file
        startGame = true;
        this.update();
    }// end of resumeGame

    /**
     * A void method used to help end the game
     */
    public void endGame() {
        gameOver = true;
        this.update();
    }// end of endGame

    /**
     * A void method used to help set the number of rounds
     */
    public void adjustValues(char d) {
        // easy
        if (d == 'e') {
            totalRounds = 3;
        }
        // moderate
        else if (d == 'm') {
            totalRounds = 5;
        }
        // hard
        else if (d == 'h') {
            totalRounds = 7;
        }

        this.update();
    }// end of adjust values

    /**
     * A void method used to help register the shot values
     * 
     * @param power // power for the shot
     * @param angle //angle for the shot
     */
    public void registerShot(int power, int angle) {
        view.shoot(angle, power); // shoot the ball
        playerShotTaken = true;
        this.setSuccessfulShot(); // check if shot went in
        saveScore(playerTurn); // save score

        try {
            // a delay before starting the next round
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        playerTurn =0;
        robotMove(); // simulate robot's move
        this.setSuccessfulShot();
        saveScore(playerTurn);

        // Check if both player and robot have taken their shots
        if (playerShotTaken && robotShotTaken) {
            // Check if all rounds are completed
            if (currentRounds <= totalRounds) {
                try {
                    // delay before starting the next round
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Round Ednded");

                endRound(); // End the round after both shots are taken
            } else {
                // All rounds are completed, end the game
                System.out.println("All rounds completed. Game over!");
                gameOver = true;
                this.setWinner();
                this.update();
            }
        }

        this.update();
    }// end of register shots

    /**
     * A void method used to help select robot values
     */
    private void robotMove() {
        // Simulate robot's move by registering the randomized values
        int randomPower = random.nextInt(61) + 140; // Random power between 140 and 200
        int randomAngle = random.nextInt(62) + 20; // Random angle between 20 and 81
        robotShotTaken = true;
        view.shoot(randomAngle, randomPower);

    }// end of robotMove

    /**
     * A void method for what to do after the round ends
     */
    private void endRound() {

        endRound = true;
        // Print to file and screen
        printToFile();
        printCurrentScore();

        // Reset variables for the next round
        playerTurn = 1;
        currentRounds++;

        // Check if all rounds are completed
        if (currentRounds > totalRounds) {
            System.out.println("All rounds completed. Game over!");
            gameOver = true;
        }

        // default values
        playerShotTaken = false;
        robotShotTaken = false;

        this.update();
        endRound = false;
    }

    /**
     * A void method for printing to an outside text file
     */
    private void printToFile() {

        // clear file each time
        try {
            PrintWriter clear = new PrintWriter("Programs/Summative/score.txt");
            clear.print("");
            clear.close();
            System.out.println("File cleared successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        String roundInfo = Integer.toString(currentRounds); // currentRoundValue
        String difficultyInfo = Character.toString(difficulty); // currentDifficultyValue

        // WRITING SCORE INFO TO FILE
        try (PrintWriter out = new PrintWriter(new FileWriter("Programs/Summative/score.txt", true))) {
            // CURRENT ROUND INFO
            out.println(roundInfo);

            // DIFFICULTY ISSUE
            out.println(difficultyInfo);

            // OUTPUTTING EACH ROUND SCORE
            for (int x = 0; x < totalRounds; x++) {
                // Adjusted the loop index to start from 1
                out.print("[");
                out.print(scoreBoard[x][0]);
                out.print(scoreBoard[x][1]);
                out.println();
            }
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// end of printToFile

    /**
     * A void method for retrieving text from file
     */
    public void readText() {
        String line = null;

        // READING FILE
        FileReader reader = null;
        try {
            reader = new FileReader("Programs/Summative/score.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exists");
            e.printStackTrace();
        }

        // EXTRACTING INFO FROM FILE
        BufferedReader input = new BufferedReader(reader);
        try {
            while ((line = input.readLine()) != null) {
                checkForInfo(line);// checking what value string represents
            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        // update GUI
        this.update();

    }// end of readText

    /**
     * A void method for checking Info and setting values
     * 
     * @param data // string being sent to be checked from fileReader
     */
    public void checkForInfo(String data) {

        System.out.println(data);
        // Check for numbers
        if (!data.contains("[") && data.contains("1") || data.contains("2") || data.contains("3") || data.contains("4")
                || data.contains("5")
                || data.contains("6") || data.contains("7")) {
            currentRounds = Integer.parseInt(data);
            setCurrentRounds(currentRounds);
        }

        // Check for characters
        else if (data.equals("e") || data.equals("m") || data.equals("h")) {
            difficulty = data.charAt(0);
            setDifficulty(difficulty);
        }

        // Check for round score info
        else if (data.startsWith("[")) {
            processRoundScoreInfo(data.substring(1)); // Pass the substring excluding the "["
        }

    }// end of checkForInfo

    /**
     * A void method for calculating the winner
     * 
     *
     */
    public void setWinner() {

        int playerWins = 0;
        int robotWins = 0;

        // calculate wins for each
        for (int x = 0; x < totalRounds; x++) {
            playerWins += scoreBoard[x][0];
            robotWins += scoreBoard[x][1];
        }

        // if player wins
        if (playerWins > robotWins) {
            winner = 1;
        }
        // if robot wins
        else if (playerWins < robotWins) {

            winner = 2;
        }

        // if neither
        else if (playerWins == robotWins) {
            winner = 0;
        }

    }// end of setWinner

    /**
     * A void method for setting the currentRound
     */
    public void setCurrentRounds(int current) {

        currentRounds = current;

    }// end of setCurrentRounds

    /**
     * A void method to process scoreInfo
     * 
     * @data // line being passed to extract scores from
     */
    public void processRoundScoreInfo(String data) {
        int[][] sb = new int[totalRounds][2];

        if (roundCheck < totalRounds) {
            sb[roundCheck][0] = Integer.parseInt(data.charAt(0) + "");
            sb[roundCheck][1] = Integer.parseInt(data.charAt(1) + "");
            roundCheck++;
        } else {
            scoreBoard = sb;
        }
    }// end of processRoundScoreInfo

    /**
     * A boolean method for getting the round status
     * 
     * @return endRound // return value if round has ended or not
     */
    public boolean getRoundStatus() {

        return endRound;

    }// end of getRoundStatus

    /**
     * A void method for outputting currentScore
     */
    private void printCurrentScore() {
        for (int z = 0; z < scoreBoard.length; z++) {
            System.out.print("Round " + (z + 1) + ": ");
            for (int y = 0; y < scoreBoard[z].length; y++) {
                System.out.print(scoreBoard[z][y] + " ");
            }
            System.out.println();
        }
    }// end of printCurrentScore

    /**
     * A void method saving score according to if the shot went in or not
     */
    private void saveScore(int playerTurn) {
        // Check if position allowed
        if (currentRounds > 0 && currentRounds <= scoreBoard.length &&
                playerTurn >= 0 && playerTurn < scoreBoard[0].length) {
    
            // Debugging statements
            System.out.println("Saving score for round " + currentRounds + ", player " + playerTurn);
    
            // If the player/robot successfully gets the ball in, set the value to 1 in the
            // array, else, leave it as 0
            if (successfulShot) {
                scoreBoard[currentRounds-1][playerTurn] = 1;
            } else {
                scoreBoard[currentRounds-1][playerTurn] = 0;
            }
        } else {
            System.out.println("Invalid position in saveScore method");
        }
    }
    // end of saveScore

    /**
     * A void method for checking if the shot was successful from GolfMap class
     */
    public void setSuccessfulShot() {
        successfulShot = view.getShotStatus();
        System.out.println(successfulShot);
        // checking map class from if the shot went in or not
    }// end of setSuccessfulShot

    /**
     * A void method setting the view of this model to the GUI
     */
    public void setGUI(GolfManiaGUI currentView) {
        this.view = currentView;
    }// end of setGUI

    /**
     * A method that returns the current round
     * 
     * @return currentRounds // current round
     */
    public int getCurrentRound() {

        return currentRounds;

    }// end of getCurrentInt

    /**
     * A method that returns the character for difficulty
     * 
     * @return difficulty// difficulty
     */
    public char getDifficulty() {

        return difficulty;

    }// end of getDifficulty

    /**
     * A boolean method that tells if game has been started or not
     * 
     * @return startGame // return value if game has started
     */
    public boolean getStartStatus() {

        return startGame;
    }// end of getStartStatus

    /**
     * A method that returns a 2D array for the scoreBoard
     * 
     * @return scoreBoard // return value of scoreboard
     */
    public int[][] getScoreBoard() {

        return scoreBoard;
    }// end of getScoreBoard

    /**
     * A method that returns a boolean if the game has ended
     */
    public boolean getGameOver() {
        return gameOver;
    }// end of getGameOver

    /**
     * A method that returns a int for who won
     */
    public int getWinner() {

        return winner;

    }// end of getWinner

    /**
     * A void method for calling update method in GUI
     */
    public void update() {
        view.update();
    }// end of update

}// ssalc