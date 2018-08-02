// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;


/** FrogTester
    This is a little program for testing your frog class.
    It has buttons that let you
    - make a new frog, (which should then appear below the bottom line on the screen)
    - make the frog jump forward or backwards 
    - temporarily puts a thing in the frog's way in a lane, and
      checks if the frog is in that lane, "splatting" the frog if
      it is in the same lane as the thing.
      (The frog should change its look, and should no longer be alive)
 */

public class FrogTester{

    Frog theCurrentFrog;

    /** Construct a new FrogTester object and initialise the interface */
    public FrogTester(){
        UI.initialise();
        UI.addButton("New Frog", this::newFrog);
        UI.addButton("Forward", this::makeItHopForward);
        UI.addButton("Backward", this::makeItHopBack);
        UI.addButton("Thing", this::makeThing);
        UI.addButton("Quit", UI::quit);
    }

    public void newFrog(){
        UI.clearGraphics();
        this.theCurrentFrog = new Frog();
        this.theCurrentFrog.draw();
        //draw lanes not quite right, but good enough for testing.
        UI.setColor(Color.black);
        double y = FrogGame.START_LINE-5;
        UI.drawLine(0, y, 600, y);
        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(0, y, 600, y);
        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(0, y, 600, y);
    }

    /** Makes the frog hop forward */
    public void makeItHopForward(){
        if (!this.theCurrentFrog.isAlive()) {
            this.message("Frogs can't hop if they aren't alive");
        }
        this.theCurrentFrog.hop();
    }

    /** Makes the frog hop forward */
    public void makeItHopBack(){
        if (!this.theCurrentFrog.isAlive()) {
            this.message("Frogs can't hop if they aren't alive");
        }
        this.theCurrentFrog.hopBack();
    }

    /** Makes a thing in a random lane, and splats the frog if it is in that lane */
    public void makeThing(){
        int lane = 1 + (int)(Math.random()*2);  // 1, or 2

        // draw thing in the lane
        double y = FrogGame.START_LINE - lane*FrogGame.LANE_WIDTH;
        UI.setColor(Color.blue);
        UI.fillOval(FrogGame.FROG_X-50, y, 200,FrogGame.LANE_WIDTH-10);

        // if the frog is in the same lane, splat it
        if (this.theCurrentFrog.getLane() == lane && this.theCurrentFrog.isAlive()) {
            this.theCurrentFrog.splat();

            // check that the frog isn't alive any more
            if (this.theCurrentFrog.isAlive()){
                this.message("The frog shouldn't still be alive!!!");
            }
            else {
                this.message("No more frog!");
            }
        }
        UI.sleep(1000);
        UI.eraseOval(FrogGame.FROG_X-50, y, 200,FrogGame.LANE_WIDTH-10);
    }

    /** draws a message for a second */
    public void message(String msg){
        UI.setFontSize(36);
        UI.setColor(Color.red);
        UI.drawString(msg, 0, 50);
        UI.sleep(1000);
        UI.eraseRect(0,0,600,60);  // erase the message
    }

    public static void main(String[] arguments){
        new FrogTester();    // creates a FrogTester object
    }        

}
