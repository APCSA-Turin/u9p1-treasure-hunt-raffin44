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
        grid.display();
        Scanner scanner = new Scanner(System.in);
        while(player.getLives() > 0 && player.getTreasureCount() < 2){
            System.out.println("Which way would you like to move?");
            String d = scanner.nextLine();

            if(d.equals("w")){
                player.move("w");
            }
            if(d.equals("a")){
                player.move("a");
            }
            if(d.equals("s")){
                player.move("s");
            }
            if(d.equals("d")){
                player.move("d");
            }
            if (player.getLives() == 0) {
                grid.gameover();
            } 
        }


        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

     
            }
            
     
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
