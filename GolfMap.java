package Summative;

/**
 * GolfMap
 * A program for visual components of the GameMap
 * @Author: Huzaifa A.
 * @since January 20th
 */

import javax.swing.*;
import java.awt.*;

public class GolfMap extends JComponent {

  // Coordinates
  private int cBallX = 95;
  private int cBallY = 390;
  private int pBallX = 400;
  private int pBallY = 390;
  private int targetX = 903;
  private int targetY = 100;

  // Math Values
  private double acc = 56.569 / (50 * 50);
  private double groundFriction = 0.009;
  private double bounceLossY = -0.5;
  private double bounceLossX = 0.8;
  private double vX;
  private double vY;

  // Logic
  private boolean bounceFlipper = true;
  private boolean xFlipper = true;
  private boolean yFlipper = true;
  private int pWins = 0;

  /**
   * Constructor for GolfMap.
   * Sets the preferred size of the component.
   */
  public GolfMap() {
    this.setPreferredSize(new Dimension(1000, 500));
  }

  /**
   * Paints graphical elements on the component.
   * @param g The Graphics object used for painting.
   */
  public void paintComponent(Graphics g) {
    // Drawing the background
    g.setColor(new Color(13, 135, 35));
    g.fillRect(0, 400, 1000, 100);
    g.setColor(new Color(40, 100, 240));
    g.fillRect(0, 0, 1000, 400);
    
    // Drawing obstacles and target
    g.setColor(Color.BLACK);
    g.fillRect(900, 360, 6, 40);
    g.fillRect(888, 400, 30, 5);
    g.setColor(Color.RED);
    g.fillRect(880, 350, 25, 10);

    // Drawing golf balls
    g.setColor(Color.RED);
    g.fillOval(cBallX, cBallY, 10, 10);
    g.setColor(Color.WHITE);
    g.fillOval(pBallX, pBallY, 10, 10);
  }

  /**
   * Simulates a shot with given angle and power.
   * Moves the player's ball according to the shot parameters.
   * @param angle The angle of the shot.
   * @param power The power of the shot.
   */
  public void shoot(double angle, double power) {
    // Calculating initial velocities
    vX = (-power * (Math.cos(Math.toRadians(180 - angle)))) / 50;
    vY = (power * (Math.sin(Math.toRadians(angle)))) / 50;

    double extraX = 0;
    double extraY = 0;

    // Simulating ball movement
    while (yFlipper && xFlipper) {
      pBallX += Math.floor(vX + extraX);
      pBallY -= Math.floor(vY + extraY);
      extraX = (vX + extraX) - Math.floor(vX + extraX);
      extraY = (vY + extraY) - Math.floor(vY + extraY);
      changeVelocity();
      endCheck();
      checkCollision();
      nextFrame(7);
      if (checkForWin()) {
        break;
      }
    }
  }

  /**
   * Checks if the shot has ended based on the velocity.
   */
  public void endCheck() {
    if ((-0.05 < vY && vY < 0.05) && pBallY > 390) {
      yFlipper = false;
    }
    if ((-0.05 < vX && vX < 0.05)) {
      xFlipper = false;
    }
  }

  /**
   * Adjusts the velocity based on the current conditions.
   */
  public void changeVelocity() {
    if (pBallY < 390) {
      vY -= acc;
    } else {
      if (vX > 0) {
        vX = vX - groundFriction;
      } else {
        vX = vX + groundFriction;
      }
    }
  }

  /**
   * Checks for collisions with the ground and walls, and adjusts the velocity accordingly.
   */
  public void checkCollision() {
    if (pBallY < 390) {
      bounceFlipper = false;
    } else if (pBallY > 390 && bounceFlipper == false) {
      vY = (vY * bounceLossY);
      vX = (vX * bounceLossX);
      bounceFlipper = true;
    }

    if (pBallX < 10 || pBallX > 990) {
      vX = -vX;
    }
  }

  /**
   * Checks if the player has won the game.
   * @return True if the player has won, otherwise false.
   */
  public boolean checkForWin() {
    if (((-20 < pBallX - targetX && pBallX - targetX < 10) && (pBallY > 389))) {
      System.out.print("You win");
      for (int i = 0; i < 5; i++) {
        pBallY++;
        nextFrame(100);
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Delays the next frame update.
   * @param millis The delay time in milliseconds.
   */
  public void nextFrame(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.repaint();
  }

  /**
   * Main method to create and display the game window.
   * @param args Command-line arguments (unused).
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    GolfMap drawing = new GolfMap();
    panel.add(drawing);
    frame.setContentPane(panel);
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Repaints the screen to reflect any changes.
   */
  public void repaintScreen() {
    this.repaint();
  }
}
