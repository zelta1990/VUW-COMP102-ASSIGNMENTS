// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 5
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Renders plain ppm images onto the graphics panel
ppm images are the simplest possible colour image format.
 */

public class ImageRenderer{
    public static final int top = 20;   // top edge of the image
    public static final int left = 20;  // left edge of the image
    public static final int pixelSize = 2;  

    // Constructor
    public ImageRenderer() {
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Render (core)", this::renderImage );
        UI.addButton("Render (compl)", this::renderAnimatedImage );
        UI.addButton("Quit", UI::quit );
        UI.setWindowSize(850, 700);
        UI.setDivider(0.0);
    }

    /** Core:
     * Renders a ppm image file.
     * Asks for the name of the file, then renders the image at position (left, top).
     * Each pixel of the image  is rendered by a square of size pixelSize
     * Assumes that
     * - the colour depth is 255,
     * - there is just one image in the file (not "animated"), and
     * - there are no comments in the file.
     * The first four tokens are P3, number of columns, number of rows, 255
     * The remaining tokens are the pixel values (red, green, blue for each pixel)
     */
    public void renderImage(){
        /*# YOUR CODE HERE */
        try{
            Scanner scan=new Scanner(new File(UIFileChooser.open("Please choose a file to render")));

            int r=0;
            int c=0;
            int x=left;
            int y=top;
            scan.next();
            while(scan.hasNext()){
                String magicNumber=scan.nextLine();
                int cols=scan.nextInt();
                int rows=scan.nextInt();
                int depth=scan.nextInt();

                while(r<rows){
                    c=0;
                    x=left;
                    while(c<cols){
                        int R=scan.nextInt();
                        int G=scan.nextInt();
                        int B=scan.nextInt();
                        Color newColor=new Color(R,G,B);
                        UI.setColor(newColor);
                        UI.fillRect(x,y,2,2);
                        c++;
                        x=x+2;
                    }
                    r++;
                    y=y+2;

                }
            }
        }catch(Exception e){UI.printf("File failurez %s\n",e);}
    }

    /** Completion and Challenge
     * Renders a ppm image file which may be animated (multiple images in the file)
     * Asks for the name of the file, then renders the image at position (left, top).
     * Each pixel of the image  is rendered by a square of size pixelSize
     * Renders each image in the file in turn with 200 mSec delay.
     * Repeats the sequence 3 times.
     * Challenge:
     * Ignores comments (starting with # and occuring after the 1st, 2nd, or 3rd token) 
     * The colour depth (max colour value) may be different from 255 (scales the
     * colour values appropriately)
     */
    public void renderAnimatedImage(){
        /*# YOUR CODE HERE */
        try{
            File anime=new File(UIFileChooser.open("Please choose a file to render"));
            for(int i=0;i<3;i++){      
                Scanner input=new Scanner(anime);
                this.renderImageCompletion(input);
                
            }

        }catch(Exception e){UI.printf("File failurez %s\n",e);}
    }

    public void renderImageCompletion(Scanner input){
        /*# YOUR CODE HERE */

        //Scanner input=new Scanner(new File(UIFileChooser.open("Please choose a file to render")));
        int r=0;
        int c=0;
        int x=left;
        int y=top;

        while(input.hasNext()){  
            String magicNumber=input.next();
            int cols=input.nextInt();
            int rows=input.nextInt();
            int depth=input.nextInt();

            while(r<rows){
                c=0;
                x=left;
                while(c<cols){
                    int R=input.nextInt();
                    int G=input.nextInt();
                    int B=input.nextInt();
                    Color newColor=new Color(R,G,B);
                    UI.setColor(newColor);
                    UI.fillRect(x,y,2,2);
                    c++;
                    x=x+2;
                }
                r++;
                y=y+2;
            }
            //UI.println(input.next());
            UI.sleep(200);
            r=0;
            c=0;
            x=left;
            y=top;
        }

        // }catch(Exception e){UI.printf("File failurez %s\n",e);}
    }

}

