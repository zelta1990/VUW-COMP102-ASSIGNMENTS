// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 6
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */
//I worked out this part with Niharika Kondori
import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class MiniPaint{   

    // fields to remember:
    //  - the shape that will be drawn when the mouse is next released.
    //  - whether the shape should be filled or not
    //  - the position the mouse was pressed, 
    //  - the name of the image file
    /*# YOUR CODE HERE */
    private String shape;
    private Boolean fill;
    private String filename;
    private double startX;
    private double startY;
    private double x;
    private double y;

    //Constructor
    /** Sets up the user interface - mouselistener and buttons */
    public MiniPaint(){
        /*# YOUR CODE HERE */
        UI.setMouseListener (this::doMouse);
        UI.addButton("Line", this :: drawLine);
        UI.addButton("Rect", this :: drawRect);
        UI.addButton("Oval", this::drawOval);
        UI.addButton("Image", this::drawImage);
        UI.addButton("Rings", this :: drawRings);
        UI.addButton("Color", this :: doChooseColor);
        UI.addButton ("Fill/Nofill", this ::doFill);
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Quit", UI::quit);

    }

    public void drawLine (){         
        shape ="line";
    }

    public void drawRect () {
        shape ="rect";
    } 

    public void drawOval () {
        shape ="oval";
    }

    public void drawImage () {
        shape ="image";
        this.filename = UIFileChooser.open("choose a file");
    }

    public void drawRings (){
        shape="rings";
    }

    public void doChooseColor (){
        shape="color";
    }
    public void doFill(){
        shape="fill/noFill";
         if(this.fill){
            this.fill = false;
        }
        else {
            this.fill = true;
        }
    }

    public void clearGraphics () {
    }

    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw the current shape using the pressed position
     *  and the released position.
     * Uses the value in the field to determine which kind of shape to draw.
     * Although you could do all the drawing in this method,
     *  it may be better to call some helper methods for the more
     *  complex actions (and then define the helper methods!)
     */
    public void doMouse(String action, double x, double y) {
        /*# YOUR CODE HERE */
         
        if(action.equals ("pressed")){
            
            this.startX =x;
            this.startY =y;
            this.fill=true;

        } 
        if(action.equals("released")){

            if(shape == "line") {
                UI.drawLine(this.startX, this.startY, x, y);
            }
            if(shape == "rect") {

                this.drawRect(x,y);  
            }
            if(shape == "oval") {
                this.drawOval(x,y); 
            }
            if(shape == "image") {
                this.drawImage(x,y);

            }
            if(shape=="color"){
                this.doChooseColor();
               Color color=JColorChooser.showDialog(null,"JColorChooser", Color.black);
                UI.setColor(color);
            }
            if(shape=="rings"){
                this.drawRings(x,y);
            }
         
    }
   
        
}

    /* Helper methods for drawing the shapes, if you choose to define them 
    I used the following methods:*/

    public void drawRect(double x, double y){
        if(!this.fill){
        if(x<this.startX){
            if(y>this.startY){
                UI.drawRect(x,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.drawRect(x,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }
        else if(x>this.startX){
            if(y>this.startY){
                UI.drawRect(this.startX,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.drawRect(this.startX,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }       
    }  
else
  if(this.fill){
        if(x<this.startX){
            if(y>this.startY){
                UI.fillRect(x,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.fillRect(x,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }
        else if(x>this.startX){
            if(y>this.startY){
                UI.fillRect(this.startX,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.fillRect(this.startX,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }       
    }
}

    // draws a Rectangle between the mouse pressed and mouse released points.

    public void drawOval(double x, double y){
        if(x<this.startX){
            if(y>this.startY){
                UI.drawOval(x,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.drawOval(x,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }
        else if(x>this.startX){
            if(y>this.startY){
                UI.drawOval(this.startX,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.drawOval(this.startX,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }       
    }  
    // draws a Rectangle between the mouse pressed and mouse released points.

    public void drawImage(double x, double y){
        if(x<this.startX){
            if(y>this.startY){
                UI.drawImage(this.filename,x,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.drawImage(this.filename,x,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }
        else if(x>this.startX){
            if(y>this.startY){
                UI.drawImage(this.filename,this.startX,this.startY,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
            else if(y<this.startY){
                UI.drawImage(this.filename,this.startX,y,Math.abs(x-this.startX),Math.abs(y-this.startY));
            }
        }       
    }// draws an image at the mouse released point.

        
    public void drawRings(double x, double y){
        for(int i=1;i<11;i++){
        UI.drawOval(x-(i-1)*5,y-(i-1)*5,i*10,i*10);
    }
    }
    // draws concentric rings around the point x,y

    /*# YOUR CODE HERE */
    // Main:  constructs a new MiniPaint object
    public static void main(String[] arguments){
        new MiniPaint();
    }        

}
