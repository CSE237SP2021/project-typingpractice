package TypingPractice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HighScores implements Serializable{
	private static final long serialVersionUID = 1078075037442457709L;
	ArrayList<HardPractice> highScores;
	
	public HighScores() {
		this.highScores = new ArrayList<HardPractice>();
	}
	
	public void print() {
		for (int i=0; i < this.highScores.size(); i++) {
			System.out.println((i+1) + ".   " +this.highScores.get(i).getName() + "    " + this.highScores.get(i).correctWordsCounter);
		}
	}
	
	public void addScore(HardPractice game) {
		boolean added = false;
		if (this.highScores.size() < 5) {
			for (int i=0; i < this.highScores.size(); i++) {
				if (game.correctWordsCounter > this.highScores.get(i).correctWordsCounter) {
					this.highScores.add(i, game);
					added = true;
					break;
				}
			}
			if (!added) {
				this.highScores.add(game);
			}
		}
		else {
			for (int i=0; i < this.highScores.size(); i++) {
				if (game.correctWordsCounter > this.highScores.get(i).correctWordsCounter) {
					this.highScores.add(i, game);
					added = true;
					break;
				}
			}
		}
		if (this.highScores.size() > 5) {
			this.highScores.remove(5);
		}
		this.save();
	}
	
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("src/resources/highScores");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(this);
	        out.close();
	        fileOut.close();
	        System.out.printf("High Score Saved!");
    	} catch (IOException i) {
    		i.printStackTrace();
    	}
 
	}
	
}
