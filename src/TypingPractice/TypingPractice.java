package TypingPractice;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;


public class TypingPractice {

    public String[] menu;
    public String username;
    private HighScores highScores; 


    /*
     * Reads user input and selects the appropriate matching game mode or quits
     */
    public static void main(String[] args) {
        TypingPractice typingPractice = new TypingPractice();
        typingPractice.highScores = new HighScores();
        boolean quit = false;
        while (!quit) {
        	typingPractice.display();
            Scanner in = new Scanner(System.in);
            System.out.println("What would you like to do?");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                quit = true;
                System.out.println("Thanks for playing.");
            } else if (input.equalsIgnoreCase("Custom")) {
                GameMode game = new TimedPractice(typingPractice.username, typingPractice.getGameLength());
                game.run();
            }
            else if (input.equalsIgnoreCase("Log Out")) {
            	if(typingPractice.logOut()) {
            		quit = true;
            	}
            	
            }
            else if (input.equalsIgnoreCase("Hard")) {
                float intervalLength = 5;
            	GameMode game = new HardPractice(typingPractice.username, intervalLength);
                game.run();
            	typingPractice.highScores.addScore((HardPractice)game);
            }
            else if (input.equalsIgnoreCase("1 Minute")) {
                GameMode game = new TimedPractice(typingPractice.username, 60);
                game.run();
            }
            else if (input.equalsIgnoreCase("3 Minute")) {
                GameMode game = new TimedPractice(typingPractice.username, 180);
                game.run();
            }
            else if (input.equalsIgnoreCase("View Hard Game High Scores")) {
            	typingPractice.highScores.print();
            }
            else if (input.equalsIgnoreCase("View Personal Scores")) {
            	typingPractice.retrieveGame();
            }
        }
    }

    public TypingPractice(){
        this.menu = getMenu();
        this.username = getUser();
    }
    
    public TypingPractice(String name){
        this.menu = getMenu();
        this.username = name;
    }
    
    
    public void loadHighScores(){

       Object e = null;
       try {
    	   	File myObj = new File("src/resources/highScores");
            FileInputStream fileIn = new FileInputStream(myObj);
            ObjectInputStream inStream = new ObjectInputStream(fileIn);
            e = inStream.readObject();
            inStream.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Game not found");
            c.printStackTrace();
            return;
        }
       this.highScores = (HighScores) e; 
    }

    /*
     * Keeps track of which items are to be displayed on the menu
     */
    public String[] getMenu(){
        String[] mainMenu = {"1 Minute", "3 Minutes", "Custom", "Hard", "View High Scores","View Personal Scores", "Log Out","Quit"};
        return mainMenu;
    }

    public String getUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your current or desired username");
        String s = in.nextLine();
        return s;
    }



    /* 
     * Creates the menu that allows the user to select their game mode or quit the game.
     */
    public void display(){
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("                     MENU                     ");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < this.menu.length; i++)
        {
            System.out.println();
            System.out.println(this.menu[i]);
            System.out.println();
            if (i != this.menu.length-1){
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println();

    }

    /*
     * Deserializes the saved game report object corresponding to the time and 
     * date chosen by the user and then displays the information to the console.
     * The method retrieves the game as an object then checks what class it is an instance 
     * of and casts it. Then the game's print() method is called to display the game report.
     */

    public void retrieveGame(){

        File dir = new File("src/resources/game_reports/");
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(username+"-"));
        for (int i = 0; i < files.length; i++){
            System.out.println(i+1 + ".    " + files[i].getName());
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Which game report would you like to view?");
        int s = in.nextInt();
        if (s > 0 || s <= files.length){
            File gameFile = files[s-1];

            Object e = null;
            try {
                FileInputStream fileIn = new FileInputStream(gameFile.toString());
                ObjectInputStream inStream = new ObjectInputStream(fileIn);
                e = inStream.readObject();
                inStream.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Game not found");
                c.printStackTrace();
                return;
            }
            
            
            // Checks what type of object the saved game was and casts to the correct type
            if (e instanceof TimedPractice) {
            	TimedPractice tp = (TimedPractice) e;
            	tp.print();
            }
            
            else if (e instanceof HardPractice) {
            	HardPractice tp = (HardPractice) e;
            	tp.print();
            }
            
            else {
            	System.out.println("Game report failed to load");
            }
            
        }
        else {
        	System.out.println("Game report not found");
        }
    }

    
    public float getGameLength(){
        float s = 0;
        while (s <= 0 || s > 120){
            Scanner in = new Scanner(System.in);
            System.out.println("How many seconds would you like this round to last? Enter a number between 0-120");
            s = in.nextFloat();
        }
        return s;
    }
    
   
    //Logs out the current user and prompts them to enter a new user name
    public boolean logOut() {
        Scanner in = new Scanner(System.in);
    	System.out.println("You have been logged out. Enter a new user name to log back in or "
    			+ "'quit' if you wish to stop playing");
    	String response = in.nextLine();
    	if (response.equalsIgnoreCase("quit")) {
            System.out.println("Thanks for playing.");
    		return true;
    	}
    	else {
            System.out.println("You are now logged in as: " + response);
    		this.username = response;
    	}
    	return false;
    }

    
    
    
}
