package TypingPractice;

public class QuitItem extends GameMode {
    public void run(){
        System.exit(0);
    }
    public QuitItem(String s){
        super(s);
    }
}