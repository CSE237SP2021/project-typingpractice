package TypingPractice;

/*
 * Inherits from game mode and is called at the main menu to end a session
 */

public class QuitItem extends GameMode {
    public void run(){
        System.exit(0);
    }
    public QuitItem(String s){
        super(s);
    }
}