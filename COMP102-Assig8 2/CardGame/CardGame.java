// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 8
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 *  Lets a player play a simple Solitaire cards game.
 *  The player has a "hand" which can contain up to ten cards.
 *  They can reorder the cards in their hand, they can place cards
 *  from their hand onto the table, and they can pick up more cards from a bag
 *  to fill the gaps in their "hand".
 *  The core and completion do not involve any of the matching and scoring
 *  of real cards games. 
 *
 *  PROGRAM DESIGN
 *  The cards are represented by objects of the Card class.
 *  The Card constructor will construct a new, random card.
 *  Cards have a draw(double x, double y) method that will draw the
 *  Card on the graphics pane at the specified position.
 *  
 *  The program has two key fields:
 *    hand:  an array that can hold 10 Cards. 
 *    table: an arrayList of the Cards that have been placed on the table.
 *    
 *  The hand should be displayed near the top of the Graphics pane with a
 *   rectangular border and each card drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected card (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected card
 *  (or the space) to the left or the right, in which case the card is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a card, the user
 *  can use the "Place" button to move the selected card to the table.
 *  
 *  If there are any empty positions on the hand, the user can use the
 *  "Pickup" button to get a new (random) card which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of cards.
 *  At the beginning of the game the table should be empty, and the hand
 *    should have 10 cards
 *  Cards should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class CardGame{
    public static final int NUM_HAND = 10;    // Number of cards in hand

    // Fields
    private Card[] hand;            // the hand (fixed size array of Cards)
    private ArrayList<Card> table;  // the table (variable sized list of Cards)

    private int selectedPos = 0;    //  selected position in the hand.

    // (You shouldn't add any more fields for core or completion)

    // constants for the layout
    public static final int HAND_LEFT = 60; // x-position of the leftmost Card in the hand
    public static final int HAND_TOP = 5;   // y-Position of all the Cards in the hand 
    public static final int CARD_SPACING = 80; 
    //spacing is the distance from left side of Card to left side of next card
    public static final int CARD_HEIGHT = 110; 

    public static final int TABLE_LEFT = 10;                
    public static final int TABLE_TOP = 130;   

    /**  Constructor:
     * Initialise the hand field to have an array that will hold NUM_HAND Cards
     * Initialise the table field to have an ArrayList of Cards,
     * set up the GUI (buttons and mouse)
     *  restart the game
     */
    public CardGame(){
        /*# YOUR CODE HERE */   
        this.hand=new Card[NUM_HAND];
        this.table=new ArrayList<Card>();

        UI.setMouseListener(this::doMouse);
        UI.addButton("Pickup",this::pickupCard);
        UI.addButton("Place",this::placeCard);
        UI.addButton("Replace",this::replaceCard);
        UI.addButton("Left", this::moveLeft);
        UI.addButton("Right",this::moveRight);
        UI.addButton("Restart",this::restart);
        UI.addButton("Quit",UI::quit);
        this.redraw();
    }

    /**
     * Restart the game:
     *  set the table to be empty,
     *  set the hand to have 10 cards
     */
    public void restart(){  // I worked out this method with Niharika Konduri
        /*# YOUR CODE HERE */
        this.table = new ArrayList<Card>();
        this.hand = new Card[NUM_HAND];        
        for(int i=0; i<NUM_HAND; i ++){
            Card c = new Card ();
            this.hand[i]=c;

        }
        this.redraw();
    }

    /**
     * If there is at least one empty position on the hand, then
     * create a new random card and put it into the first empty position on the hand.
     * (needs to search along the array for an empty position.)
     */
    public void pickupCard(){
        /*# YOUR CODE HERE */
        for(int i=0;i<NUM_HAND;i++){
            if(this.hand[i]==null){
                Card c=new Card();
                this.hand[i]=c;
                break;
            }
        }
        this.redraw();
    }

    /**
     * Move card from selected position on hand (if there is card there) to the table
     * The selectedPos field contains the index of the selected card.
     */
    public void placeCard(){
        /*# YOUR CODE HERE */
        if(this.hand[this.selectedPos]!=null){
            this.table.add(this.hand[this.selectedPos]);
            this.hand[this.selectedPos]=null;
            this.selectedPos++;
        }
        this.redraw();
    }

    /**
     * If there is a card at the selected position in the hand, 
     * replace it by another card.
     */
    public void replaceCard(){
        /*# YOUR CODE HERE */
        if(this.hand[this.selectedPos]!=null){
            Card c=new Card();
            this.hand[this.selectedPos]=c;
        }
        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     * position on its left (if there is such a position)
     * and also decrement the selected position to follow the card 
     */
    public void moveLeft(){
        /*# YOUR CODE HERE */
        if(this.selectedPos>0){
            Card temp=this.hand[this.selectedPos];
            this.hand[this.selectedPos]=this.hand[this.selectedPos-1];
            this.hand[this.selectedPos-1]=temp;
            this.selectedPos--;
        }
        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     *  position on its right (if there is such a position)
     *  and also increment the selected position to follow the card 
     */
    public void moveRight(){
        /*# YOUR CODE HERE */
        if(this.selectedPos<NUM_HAND){
            Card temp=this.hand[this.selectedPos];
            this.hand[this.selectedPos]=this.hand[this.selectedPos+1];
            this.hand[this.selectedPos+1]=temp;
            this.selectedPos++;
        }
        this.redraw();
    }

    /** Allows the user to select a position in the hand using the mouse.
     * If the mouse is released over the hand, then sets  selectedPos
     * to be the index into the hand array.
     * Redraws the hand and table */
    public void doMouse(String action, double x, double y){
        if (action.equals("released")){
            if (y >= HAND_TOP && y <= HAND_TOP+CARD_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*CARD_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/CARD_SPACING);
                UI.clearText();UI.println("selected "+this.selectedPos);
                this.redraw();
            }
        }
    }

    /**
     *  Redraw the table and the hand.
     *  To work with the code above, this needs to use the constants:
     *   - CARD_SPACING, HAND_HEIGHT, HAND_LEFT, HAND_TOP, TABLE_LEFT, TABLE_TOP
     *   See the descriptions where these fields are defined.
     *  Needs to clear the graphics pane,
     *  then draw the hand with all its cards, 
     *  then outline the selected position on the hand
     *  then draw the rows of cards on the table.
     */
    public void redraw(){
        UI.clearGraphics();
        this.drawHand();
        this.drawTable();
    }

    /**
     * Draws the outline of the hand,
     * draws all the Cards in the hand,
     * highlights the selected position in some way
     */
    public void drawHand(){
        /*# YOUR CODE HERE */
        UI.setColor(Color.black);
        UI.drawRect(HAND_LEFT,HAND_TOP,(CARD_SPACING)*10,CARD_HEIGHT);
        for(int i=0;i<NUM_HAND;i++){
                if(this.hand[i]!=null){
                Card c=this.hand[i]; 
                c.draw(HAND_LEFT+i*CARD_SPACING,HAND_TOP);
                }	
            }
            UI.setColor(Color.green);
            UI.drawRect(HAND_LEFT+(this.selectedPos)*CARD_SPACING,HAND_TOP,CARD_SPACING,CARD_HEIGHT);

        }

        /**
         * Draws the list of Cards on the table, 10 to a row
         * Note, has to wrap around to a new row when it gets to the
         * edge of the table
         */
        public void drawTable(){
            /*# YOUR CODE HERE */
            for(int i=0;i<table.size();i++){
                if(i<=9){
                    this.table.get(i).draw(TABLE_LEFT+i*CARD_SPACING,TABLE_TOP);}
                else if(i>9){
                    this.table.get(i).draw(TABLE_LEFT+(i%10)*CARD_SPACING,TABLE_TOP+(CARD_HEIGHT)*(i/10));
                }
            }
        }

        public static void main(String[] args){
        CardGame obj = new CardGame();
    }   

}
