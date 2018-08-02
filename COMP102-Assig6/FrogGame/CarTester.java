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

/** CarTester   */

public class CarTester{

    /** Construct a new CarTester object and initialise the interface */
    public CarTester(){
        UI.initialise();
        UI.addButton("Quit", UI::quit);
        this.testCars();
    }

    /**
     * Test the cars.  Makes three cars, one going right and two going left,
     * then makes them repeatedly move for ever.
     * Prints a message every time the first car is overlapping FrogGame.FROG_X
     */

    public void testCars(){
        // draw the lanes.
        double y = FrogGame.START_LINE-5;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        UI.setColor(Color.lightGray);
        UI.fillRect(100, 0, 3, 500);
        UI.setColor(Color.black);


        Car car1 = new Car("right", 1, FrogGame.ROAD_LEFT);
        Car car2 = new Car("left",  2, FrogGame.ROAD_LEFT+100);
        Car car3 = new Car("left", 2, FrogGame.ROAD_RIGHT-70);
        while (true){
            car1.move();
            car2.move();
            car3.move();
            UI.sleep(50);
            // report when the car crosses the position 100
            if (car1.getX() <= 100 && car1.getX()+Car.WIDTH >= 100 ){
                this.message("Car in lane "+car1.getLane()+" crossing grey line");
            }
            else if (car2.getX() <= 100 && car2.getX()+Car.WIDTH >= 100 ){
                this.message("Car2 in lane "+car2.getLane()+" crossing grey line ");
            }
            else if (car3.getX() <= 100 && car3.getX()+Car.WIDTH >= 100 ){
                this.message("Car in lane "+car3.getLane()+" crossing grey line");
            }
            else {
                this.message("");
            }

        }
    }

    /** draws a message for a second */
    public void message(String msg){
        UI.eraseRect(0,0,600,60);  // erase the message
        UI.setFontSize(36);
        UI.setColor(Color.red);
        UI.drawString(msg, 0, 50);
    }

    public static void main(String[] arguments){
        CarTester obj = new CarTester();
    }        

}

