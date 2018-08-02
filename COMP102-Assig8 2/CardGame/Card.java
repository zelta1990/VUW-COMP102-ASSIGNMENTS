// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 8 
 * Name: monique
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * Card
 * Represent individual Cards
 * The constructor will return a new Card with a random rank (1-13) and a 
 *    random suit ("clubs", "diamonds" ,"hearts" ,or "spades")
 * Methods:
 *   draw(double left, double top)  draws the card at a position
 *   getRank() and getSuit() return the rank and the suit of the card
 *
 */

public class Card {

    /* Fields representing the state of a card */
    public static final String[] suitNames = {"clubs", "diamonds" ,"hearts" ,"spades"};

    // The image of the back of all the cards
    private String cardBack = "cards/back.png";

    private int rank = 1;               // From 1 to 13
    private String suit = suitNames[0];
    private String image;

    /** Construct a new Card object with a random rank and suit */
    public Card(){
        this.rank = (int)(Math.random()*13)+1; // between 1 and 13 inclusive
        int index = (int)(Math.random()*4);    // between 0 and 3 inclusive
        this.suit = suitNames[index];
        this.image = getImage();
    }

    /**
     * Return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Return the rank of the card
     */
    public int getRank() {
        return rank;
    }

    /**
     * Return the name of the image of the card
     */
    public String getImage() {
        if (this.rank == 1)       return "cards/ace_of_" + this.suit + ".png";
        else if (this.rank < 11)  return "cards/" + this.rank+ "_of_" + this.suit + ".png";
        else if (this.rank == 11) return "cards/jack_of_"  + this.suit + ".png";
        else if (this.rank == 12) return "cards/queen_of_" + this.suit + ".png";
        else                      return "cards/king_of_"  + this.suit + ".png";
    }

    /**
     * Draw the card at the position x, y
     */
    public void draw(double x, double y){
        UI.drawImage(this.image, x, y);
    }
}
