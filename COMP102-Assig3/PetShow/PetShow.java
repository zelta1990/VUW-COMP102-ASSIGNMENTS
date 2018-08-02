// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 3 
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */

import ecs100.*;

/** Program to create simple animated cartoon strips using the
 *  CartoonFigure class.  
 */

public class PetShow{

    /** Constructor: set up user interface */
    public PetShow(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Animate", this::animate );
        UI.addButton("Three", this::threeAnimalsRoutine );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(0);       // Expand the graphics area
    }

    /** animate creates two animals on the window.
     *  Then animates them according to a fixed script by calling a series
     *  of methods on the figures.
     */
    public void animate(){
        /*# YOUR CODE HERE */
            Animal a1=new Animal("grasshoper","Danny",100,80);
            a1.speak("Hi,my name is Danny.");
            a1.forward(20);
            a1.speak("I'd like to introduce you Ben.");
            Animal a2=new Animal("dinosaur","Ben",300,150);
            a2.jump(30);
            a2.speak("Hi, my name is Ben.");
            a2.speak("I am a dinosaur.");
            a2.think("I'll be late for the class.");
            a2.speak("I gotta go.");
            a2.shout("catch u later.");
            a1.shout("see ya.");
            a2.forward(380);
            

    }

    /** threeDancers creates three animals on the window.
     *  Then makes each animal do the same routine in turn.
     *  You should define a routine method, and threeAnimalsRoutine
     *   should call the routine method three times, to make
     *   each of the three animals perfomr the routine in turn.
     */
    public void threeAnimalsRoutine(){
        /*# YOUR CODE HERE */
        Animal a3=new Animal("bird","Charlie",400,80);
        Animal a4=new Animal("dog","Doge",400,180);
        Animal a5=new Animal("tiger","Bisou",500,250);
        this.routine(a3);
        this.routine(a4);
        this.routine(a5);
    }
        public void routine(Animal animal){
            animal.introduce("Hi, ");
            animal.forward(100);
            animal.jump(50);
            animal.backward(30);
            animal.speak("Glad to meet u.");
            animal.shout("bye.");
            animal.forward(200);
            
          
            

    }


}
