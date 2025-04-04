package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        boolean toPlay = true;
    
        while (toPlay && player.getLives() > 0) {
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            clearScreen(); // Clear screen at the beginning of the loop
            System.out.println();
            grid.display();
    
            //Display Player Stats
            System.out.println("Player Coordinate: " + player.getCoords());
            System.out.println("Player Grid Position: " + player.getRowCol(size));
            System.out.println("Number of treasures acquired: " + player.getTreasureCount());
            System.out.println("Number of treasures needed: " + treasures.length);
            System.out.println("Number of Lives: " + player.getLives());
            System.out.println("Enter direction (w/a/s/d): ");

            //Prevent Scanner crash
                if (!scanner.hasNextLine()) {
                    break;  // Exit if no more input
                }
    
            String direction = scanner.nextLine().toLowerCase();
    
            //Check if input is a valid move
            if (direction.equals("w") || direction.equals("a") || 
                direction.equals("s") || direction.equals("d")) {
    
                if (player.isValid(size, direction)) {
                    int newX = player.getX();
                    int newY = player.getY();
    
                    // Calculate new position based on movement
                    if (direction.equals("w")) {
                        newY++;
                    }
                    if (direction.equals("a")) {
                        newX--;
                    }
                    if (direction.equals("s")) {
                        newY--;
                    }
                    if (direction.equals("d")) {
                        newX++;
                    }
    
                    Sprite sprite = grid.getGrid()[size - 1 - newY][newX];
                    player.interact(size, direction, treasures.length, sprite);
    
                    if (sprite instanceof Treasure && !(sprite instanceof Trophy)) {
                        grid.placeSprite(new Dot(newX, newY));
                    }
    
                    if (sprite instanceof Dot || sprite instanceof Enemy) {
                        player.move(direction);
                        grid.placeSprite(player, direction);
                    }
    
                    if (sprite instanceof Trophy) {
                        if (player.getWin()) {
                            clearScreen();
                            grid.win();
                            toPlay = false;
                            break;
                        } else {
                            System.out.println("Collect more treasures!");
                        }
                    }
                }
            }
        }   
    
        //Displays end-of-game message
        clearScreen();
        if (player.getLives() <= 0) {
            grid.gameover();
        } else{
            grid.win();
        }
        scanner.close();  
    }

    public void initialize(){
        grid = new Grid(size);
        player = new Player(0, 0);
        trophy = new Trophy(9, 9);
        enemies = new Enemy[] { new Enemy(4, 5), new Enemy(6, 3) };
        treasures = new Treasure[] { new Treasure(2, 2), new Treasure(5, 5), new Treasure(7, 7) };
        
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        for (Enemy e : enemies) {
            grid.placeSprite(e);
        }
        for (Treasure t : treasures) {
            grid.placeSprite(t);
        }
    }

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid

    public static void main(String[] args) {
        Game Curry = new Game(10);
        Curry.play();
    }
}
