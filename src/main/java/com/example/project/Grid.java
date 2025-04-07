package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        this.grid = new Sprite[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
              grid[i][j] = new Dot(i, j);
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - 1 - s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { // place sprite in a new spot based on direction
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] instanceof Player) {
                    grid[i][j] = new Dot(i, j); // Clear previous position
                }
            }
        }
        
        //Place the sprite first
        placeSprite(s);
    
        //Initialize Dot object
        Dot chefDot = new Dot(s.getX(), s.getY());

        // Modify the Dot's position based on the movement direction
        if (direction.equals("w")) {
            chefDot.setY(chefDot.getY() - 1);
        } else if (direction.equals("s")) {
            chefDot.setY(chefDot.getY() + 1);
        } else if (direction.equals("a")) {
            chefDot.setX(chefDot.getX() + 1);
        } else if (direction.equals("d")) {
            chefDot.setX(chefDot.getX() - 1);
     }
    
        // Place the dot at the modified position
        placeSprite(chefDot);
    }
    

    public void display() { //print out the current grid to the screen 
        for (Sprite[] row : grid) {
            for (Sprite cell : row) {
                if (cell instanceof Player){          //checks if it is player
                    System.out.print("👻 ");       
                } else if (cell instanceof Enemy){    //checks if it is enemy 
                    System.out.print("🦂 ");
                } else if (cell instanceof Treasure) { //checks if it is treasure
                    System.out.print("🍬 ");
                } else if (cell instanceof Trophy){   //checks if it is trophy
                    System.out.print("🎃 ");
                }else{                            
                    System.out.print("⬜ ");   //otherwise prints dot
                }
            }
            System.out.println();
        }
    }

    public void gameover() {  // use this method to display a loss
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("🦂 ");   //prints enemies 
            }
            System.out.println();
        }
        System.out.println("Game Over!");  //displays loss message
    }
    
    public void win() {  // use this method to display a win
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("🎃 ");   //prints trophies 
            }
            System.out.println();
        }
        System.out.print("👻 ");           //displays character next to win message
        System.out.println("You Win!");    //displays win message
    }
    


}