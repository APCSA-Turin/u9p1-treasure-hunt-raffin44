package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount = 0;
    private int numLives = 2;
    private boolean win = false;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
    }
    
    public int getTreasureCount(){ //getter method to get treasure count
        return treasureCount;      //returns number of treasures
    }
    
    public int getLives() {     //getter method to get number of lives
        return numLives;        //returns number of lives
    }
    
    public boolean getWin() {   //getter method to get win
        return win;   //displays win if true
    }

  
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        int newX = getX();         //new x coordinate of player
        int newY = getY();         //new y coordinate of player
    
        //checks each case 
        if (direction.equals("w")) {          //for w, Y will be added by one
            newY++; 
        } else if (direction.equals("s")) {  //for s, Y will be subtracted by one
            newY--;
        } else if (direction.equals("a")) {  //for a, X will be subtracted by one
            newX--;
        } else if (direction.equals("d")) {  ////for d, X will be added by one
            newX++;
        }
        setX(newX);             //update the x coordinate
        setY(newY);             //update the y coordinate
    }

    public String getCoords(){ //returns "Player:"+coordinates
        return "Player:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Player:"+row col
        return "Player:" + super.getRowCol(size);
    }

    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to
    if (isValid(size, direction)) {
        if (obj instanceof Treasure && !(obj instanceof Trophy)){
            treasureCount++; //Collects a treasure
        } 
        if (obj instanceof Enemy){
             numLives--; //Lose a life
        }
        if (obj instanceof Trophy && treasureCount >= numTreasures){
             win = true;
        }
        //numTreasures is the total treasures at the beginning of the game
    }
    }

    public boolean isValid(int size, String direction) { //check grid boundares
        // Start with using player's current position
        int newX = getX();
        int newY = getY();
    
        // Adjusting the coordinates based on the direction of the movement
        if (direction.equals("w")) { 
            newY++;  // Moving up increases Y by 1
        } else if (direction.equals("s")) { 
            newY--;  // Moving down decreases Y by 1
        } else if (direction.equals("a")) { 
            newX--;  // Moving left decreases X by 1
        } else if (direction.equals("d")) { 
            newX++;  // Moving right increases X by 1
        }
    
        // Checks if the new position is within the boundaries (Returns a Boolean value)
        return newX >= 0 && newX < size && newY >= 0 && newY < size;
    }
}



