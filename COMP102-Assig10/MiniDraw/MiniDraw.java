 

// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JColorChooser;

/** The MiniDraw program allows the user to create, save, and reload files
specifying drawings consisting of a list of simple shapes.
The program allows the user to
- add a new shape to the drawing
- remove a shape from the drawing
- move a shape to a different position
- set the colour for the next shape
- save the current drawing to a file
- load a previous drawing from a file.
The shapes include lines, rectangles, ovals, and dots

Classes
The MiniDraw class handles all the user interaction:
buttons, mouse actions, file opening and closing.
It stores the current drawing in an ArrayList of Shape .

The Shape interface specifies the Shape type
The shape classes all implement Shape and represent different kinds of shapes.

Files:
A drawing is stored in a file containing one line for each shape,
Each line must start with the name of the type of shape,
followed by a specification of the shape,
First the colour (three integers for red, blue, and green)
Then the position (x and y)
The other values will depend on the shape.

User Interface:
There are buttons for dealing with the whole drawing (New, Open, Save),
buttons for specifying the next shape to draw, and
button for setting the color,
buttons for moving, removing and resizing shapes.
 */

public class MiniDraw {
    // Fields
    private ArrayList<Shape> shapes = new ArrayList<Shape>();    // the collection of shapes

    // suggested fields to store mouse positions, current action, current shape, current colour, etc

    private double pressedX;                 //where the mouse was pressed
    private double pressedY;  
    private String currentAction = "Line";   // current action ("Move", etc) or shape ("Line" etc)
    private Shape currentShape = null;      //  the current shape (or null if no shape)
    private Color currentColor = Color.red;

    /** Constructor sets up the GUI:
     *  sets the mouse listener and adds all the buttons
     *  For New, Open, Save, and Color, call the appropriate method (see below)
     *   to perform the action immediately.
     *  For other buttons, store the button name in the currentAction field
     */
    public MiniDraw(){
        /*# YOUR CODE HERE */       //I worked with Niharika on this part
        UI.setMouseListener(this::doMouse);
        UI.addButton("New", this::newDrawing);
        UI.addButton("Open", this::openDrawing);
        UI.addButton("Save",this::saveDrawing);
        UI.addButton("Line", this::doLine);
        UI.addButton("Rectangle", this::doRectangle);
        UI.addButton("Oval", this::doOval);
        UI.addButton("Dot", this::doDot);
        UI.addButton("Color", this::selectColor);
        UI.addButton("Move", this::moveShape);
        UI.addButton("Delete",this::deleteShape);
        UI.addButton("Resize",this::resizeShape);

    }

    public void doLine(){
        this.currentAction =("Line");
    }

    public void moveShape(){
        this.currentAction =("Move");
    }

    public void doRectangle (){
        this.currentAction =("Rectangle");
    }

    public void doOval () {
        this.currentAction = ("Oval");
    }

    public void doDot () {
        this.currentAction =("Dot");
    }

    public void deleteShape () {
        this.currentAction = ("Delete");
    }

    public void resizeShape () {
        this.currentAction = ("Resize");
    }

    // Respond to mouse events

    /** When mouse is pressed, remember the position in fields
     *  and also find the shape it is on (if any), and store
     *  the shape in a field (use the findShape(..) method)
     *  When the Mouse is released, depending on the currentAction,
     *  - perform the action (move, delete, or resize).
     *    move and resize are done on the shape where the mouse was pressed,
     *    delete is done on the shape where the mouse was released 
     *  - construct the shape and add to the shapes ArrayList,
     *    (though the polygon is more complicated)
     *  - redraw the drawing.
     *  It is easiest to call other methods (see below) to actually do the work,
     *  otherwise this method gets too big!
     */
    public void doMouse(String mouseAction, double x, double y) {
        if (mouseAction.equals("pressed")){
            /*# YOUR CODE HERE */
            this.pressedX= x;
            this.pressedY =y;
            this.currentShape=this.findShape(x,y);
            UI.println(this.currentShape);

        }
        if (mouseAction.equals("released")){
            /*# YOUR CODE HERE */
            if(this.currentAction.equals("Move")){
                this.moveShape(x-this.pressedX, y-this.pressedY);
            }
            
           if(this.currentAction.equals("Line")){
                this.addShape(this.pressedX,this.pressedY, x,y);

            }
           else if(this.currentAction.equals("Delete")){
                this.deleteShape(x,y);
            }

            this.drawDrawing();
        }
    }

    // -----------------------------------------------------
    // Methods that actually do the work  
    // -----------------------------------------------------

    /** Draws all the shapes in the list on the graphics pane
     *  First clears the graphics pane, then draws each shape,
     *  Finally repaints the graphics pane
     */
    public void drawDrawing(){
        UI.clearGraphics();
        /*# YOUR CODE HERE */
        for(int i=0; i<this.shapes.size(); i++){
            this.shapes.get(i).redraw();
        }
        UI.repaintGraphics();
    }  

    /** Checks each shape in the list to see if the point (x,y) is on the shape.
     *  It returns the topmost shape for which this is true.
     *     Returns null if there is no such shape.
     */
    public Shape findShape(double x, double y){
        /*# YOUR CODE HERE */
        for(int i=0; i<this.shapes.size(); i++){
            Shape s = this.shapes.get(i);
            if(s.on(x,y)){
                return s;
            }
        }
        return null;  
    }

    /** Sets the current color.
     * Asks user for a new color using a JColorChooser (see MiniPaint, Assig 6)
     * As long as the color is not null, it remembers the color 
     */
    private void selectColor(){
        /*# YOUR CODE HERE */
        this.currentColor = JColorChooser.showDialog( null , "Choose Color", this.currentColor);
        if (this.currentColor!=null){
            UI.setColor(this.currentColor);
        }
    }

    /** Start a new drawing -
     *  initialise the shapes ArrayList and clear the graphics pane. 
     */
    public void newDrawing(){
        /*# YOUR CODE HERE */
        this.shapes = new ArrayList<Shape>();
        UI.clearGraphics ();
    }

    /** Construct a new Shape object of the appropriate kind
     *    (depending on currentAction) using the appropriate constructor
     *    of the Line, Rectangle, Oval, or Dot classes.
     *    Adds the shape to the end of the collection.
     */
    public void addShape(double x1, double y1, double x2, double y2){
        Trace.printf("Drawing shape %s, at (%.2f, %.2f)-(%.2f, %.2f)\n",
            this.currentAction, x1, y1, x2, y2);  //for debugging
        /*# YOUR CODE HERE */
        if(this.currentAction.equals("Line")){
            Line K = new Line(x1, y1, x2, y2, this.currentColor);   
            this.shapes.add(K);
        }
        if(this.currentAction.equals("Rectangle")){
            Rectangle R = new Rectangle(x1, y1, x2-x1, y2-y1, this.currentColor);
            this.shapes.add(R);

        }
        if(this.currentAction.equals("Oval")){
            Oval G = new Oval(x1, y1, x2-x1, y2-y1, this.currentColor);
            this.shapes.add(G);
        }
        if(this.currentAction.equals("Dot")){
            Dot f = new Dot(x1,y1,this.currentColor);
            this.shapes.add(f);
        }
    }

    /** Moves the current shape (if there is one)
     *    to where the mouse was released.
     *    Ie, change its position by (toX-fromX) and (toY-fromY)
     */
    public void moveShape(double changeX, double changeY){
        Trace.printf("Moving shape by (%.2f, %.2f)\n", changeX, changeY);  //for debugging
        /*# YOUR CODE HERE */
        if(this.currentShape!=null){            
        this.pressedX+=changeX;
        this.pressedY+=changeY;
    }
    
    }
    /** Finds the shape that was under the mouseReleased position (x, y)
     *    and then removes it from the ArrayList of shapes. 
     *  If not pressed on any shape, then do nothing.
     */
    public void deleteShape(double x, double y){
        Trace.printf("Deleting shape under (%.2f, %.2f)\n", x, y);  //for debugging
        /*# YOUR CODE HERE */
         if(this.findShape(x,y)!=null){
            shapes.remove(this.findShape(x,y));
            
        }
    }

 
    // METHODS FOR THE COMPLETION PART
    /** Resizes the current shape. A simple way of doing it is to
     *    resize the shape by the amount that the mouse was moved
     *    (ie from (fromX, fromY) to (toX, toY)). 
     *    If the mouse is moved to the right, the shape should
     *    be made that much wider on each side; if the mouse is moved to
     *    the left, the shape should be made that much narrower on each side
     *    If the mouse is moved up, the shape should be made
     *    that much higher top and bottom; if the mouse is moved down, the shape 
     *    should be made that much shorter top and bottom.
     *    The effect is that if the user drags from the top right corner of
     *    the shape, the shape should be resized to whereever the dragged to.
     */
    public void resizeShape(double changeX, double changeY){
        Trace.printf("Changing size of shape by (%.2f, %.2f) \n", changeX, changeY);  //for debugging
        /*# YOUR CODE HERE */
       

    }

    /** Adds a polygon [challenge].
     *    If the currentPolygon is null, then create a new polygon with
     *    just the point x,y. Store it in currentPolygon, and add it to shapes.
     *    If the currentPolygon is not null, then add a new point to it.
     *    (Don't add it to shapes, since it is already there).
     *    Note, you need to reset currentPolygon to null every time a button is pressed
     */
    public void addPolygon(double x, double y){
        /*# YOUR CODE HERE */

    }

    /** Ask the user to select a file and save the current drawing to the file. */
    public void saveDrawing(){
        /*# YOUR CODE HERE */
       

    }

    /**
     * Ask the user for a file to open,
     * then read all the shape descriptions into the current drawing.
     * For each line of the file, it will read the first token to find out which
     * kind of shape and read the rest of the line into a string.
     * It will then call the appropriate constructor, passing the string as an argument.
     */
    public void openDrawing(){
        /*# YOUR CODE HERE */

    }

    public static void main(String args[]){
        new MiniDraw();
    }

}
