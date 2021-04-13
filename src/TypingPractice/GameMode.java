package TypingPractice;

import java.io.Serializable;

/*
 * Abstract class that provides a structure for game modes 
 */


public abstract class GameMode implements Serializable{

    public String username;

    public GameMode(String n){
        this.username = n;
    }

    public String getName(){
        return this.username;
    }

    public abstract void run();

}