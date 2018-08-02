// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */


//I worked out this part with Niharika Kondori
import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** FrogGame
 * The game makes three cars that run along a three lane road
 * The player has two buttons to control a frog that starts on one side (lane 0)
 * and has to hop to the other side of the road (lane 4) without being hit.
 * If the frog gets to the other side, the player gets a point, and a new frog.
 * If a car hits the frog, it goes splat, and the player loses a life.
 * The game is over when the player has no lives left.
 */
public class FrogGame {    

    // Constants. You can access these from other classes, 
    //  eg  FrogGame.FROG_X    or FrogGame.ROAD_RIGHT
    // frog
    public static final double START_LINE = 400; // the y position of the start line for the frog
    public static final double FROG_X = 200;     // the x position of the frog

    // roads
    public static final double ROAD_LEFT = 0;    // the left end of the road
    public static final double ROAD_RIGHT = 500; // the right end of the road
    public static final double LANE_WIDTH = 50;

    // Fields
    private Frog frog;          // The current frog.

    /** Construct a new FrogGame object:
     * - draw the lanes
     * - create the first frog
     * - set up the GUI (the buttons)
     *     "Hop" should make the frog hop forward,
     *     "Back" should make the frog hop backwards.
     * - call the runGame method to make the cars run along the road
     *   and check for collisions with the frog.
     */
    public FrogGame(){
        this.drawLanes();
        this.frog = new Frog();

        UI.addButton("Hop",  this::doHop );
        UI.addButton("Back", this::doHopBack );
        UI.addButton("Quit", UI::quit);

        this.runGame();
    }

    /** Make the current frog hop forward */
    public void doHop(){
        /*# YOUR CODE HERE */
        this.frog.hop();

    }

    /** Make the current frog hop backward */
    public void doHopBack(){
        /*# YOUR CODE HERE */
        this.frog.hopBack();
    }

    /**
     * Draw the lanes.
     * Use the constants defined above.
     */
    public void drawLanes(){
        /*# YOUR CODE HERE */
        UI.drawLine(ROAD_LEFT,START_LINE-10,ROAD_RIGHT,START_LINE-10);
        UI.drawLine(ROAD_LEFT,START_LINE-LANE_WIDTH-10,ROAD_RIGHT,START_LINE-LANE_WIDTH-10);
        UI.drawLine(ROAD_LEFT,START_LINE-LANE_WIDTH*2-10,ROAD_RIGHT,START_LINE-LANE_WIDTH*2-10);
        UI.drawLine(ROAD_LEFT,START_LINE-LANE_WIDTH*3-10,ROAD_RIGHT,START_LINE-LANE_WIDTH*3-10);
    }

    /**
     * Initialise variables for the score and the remaining lives
     * Make three cars, and make them run along their lanes
     * until the player has run out of lives.
     * In the loop,
     *  - make each car move, 
     *  - If the frog has got to lane 4, then
     *     add 1 to the score,
     *     tell the player what the score is.
     *     and make a new frog
     *  - check each car to see if it is hitting the frog.
     *    ( call the hitFrog(..) method to check each car )
     *    If so, make the frog splat, decrease the number of lives left.
     *  - If the player still has any lives left, make a new frog.
     *    and make a new frog.
     *  - then sleep for short time.
     */
    public void runGame(){
        /*# YOUR CODE HERE */
        int score=0;
        int lives=5;
        Car c1=new Car("right", 1,  10);
        Car c2=new Car("left", 2, 490);
        Car c3=new Car("right", 3,10);
        while(lives>0){
            c1.move();
            c2.move();
            c3.move();      
            if(frog.getLane()==4){
                score=score+1;
                UI.println("You've scored "+score);
                frog.erase();
                this.frog=new Frog();
            }
            this.hitFrog(c1);
            this.hitFrog(c2);
            this.hitFrog(c3);
            if(hitFrog(c1)==true||hitFrog(c2)==true||hitFrog(c3)==true){
                this.frog.splat();
                lives=lives-1;
                if(lives>0){
                    this.frog=new Frog();
                    //UI.sleep(100);

                }
            } UI.sleep(100);
        }

    }

    /**
     * Returns true if the car is hitting the frog:
     * the lane of the car must be the same as the current lane of the frog
     * and the x position of the car must be such that it is hitting the frog.
     * If the car is not hitting the frog, then it should return false.
     */
    public boolean hitFrog(Car car){
        /*# YOUR CODE HERE */
        if(car.getLane()==frog.getLane()){
            if(car.getX()>=FROG_X){
                if(car.getX()-FROG_X<=40){
                    return true;
                }else{
                    return false;
                }
            }
            if(car.getX()<FROG_X){
                if(FROG_X-car.getX()<=50){
                    return true;
                }else{
                    return false;
                }
            }
            if(car.getX()==FROG_X){
                return true;
            }
        }
    else{
        return false;
    }
    return false;
}


// must return a boolean (true or false) in order for the file to compile
//return true;    // THIS IS WRONG  It is just here to make it compile.

// Main
public static void main(String[] arguments){
new FrogGame();
}   

}
