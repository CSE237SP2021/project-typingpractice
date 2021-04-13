package TypingPractice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HardPractice extends GameMode{
	
	
//	  private static final long serialVersionUID = 6529685098447757690L;
	    public String username;
	    public float gameLength;
	    public float wordTimer;
	    ArrayList<String> words;
	    int correctWordsCounter = 0;
	    String[] correctWords;
	    String incorrectWord;


	    public HardPractice(String n, float wt){
	        super(n);
	        this.username = n;
	        this.wordTimer = wt;
	    }
	    
	    public void run(){

			try{
				this.words = getPracticeWords();
			}
			catch(FileNotFoundException e){
				return;
			}

			System.out.println("INSTRUCTIONS: type each word that appears in the console exactly and press enter when finished. Work as quickly and accurately as possible. " +
	                "Continue working until the time is up. \nPress ENTER when you are ready to begin.");
			Scanner in = new Scanner(System.in); 
			String s = in.nextLine(); 
			
	        ArrayList<String> correctWordsTemp= new ArrayList<String>();
	        
	        
	        boolean playing = true;
	        int wordCounter = 0;
	        
        	while (playing) {
        		System.out.println(this.words.get(wordCounter));
        		long startTime = System.currentTimeMillis();
        		s = in.nextLine(); 

                if (!(s.equals(this.words.get(wordCounter)))){
                    playing = false;
                    this.incorrectWord = s;
                    System.out.println("Incorrect word entered");

                }
               
                if (System.currentTimeMillis() - startTime < 1000*this.wordTimer){
                    correctWordsCounter++;
                    correctWordsTemp.add(s);
                }
                else{
                	playing = false;
                    this.incorrectWord = s;
                    System.out.println("You took too long to enter the word");
                }
                wordCounter += 1;
        	}

	        this.correctWords = correctWordsTemp.toArray(new String[correctWordsTemp.size()]);
	        System.out.println("GAME OVER. You got " + correctWordsCounter + " words correct.");
	        System.out.println("Word interval was " + this.wordTimer + ".");
	        System.out.println("Enter 'save' to save a report of your score");
	        s = in.nextLine(); 
	        if (s.equalsIgnoreCase("save")){
	            save();
	        }
	        return;
    	}


	    private void save(){
	        try {
	            Calendar cal = Calendar.getInstance();
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
	            String strDate = sdf.format(cal.getTime());
	            FileOutputStream fileOut =
	                    new FileOutputStream("src/resources/game_reports/" + this.username + "-" + strDate);
	            ObjectOutputStream out = new ObjectOutputStream(fileOut);
	            out.writeObject(this);
	            out.close();
	            fileOut.close();
	            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	            String strDate1 = sdf1.format(cal.getTime());
	            System.out.printf("Game report for " + this.username + " saved at " + strDate1);
	        } catch (IOException i) {
	            i.printStackTrace();
	        }
	    }

	    public ArrayList<String> getPracticeWords() throws FileNotFoundException {


	        File myObj = new File("src/resources/hardPractice.txt");


	        Scanner myReader = new Scanner(myObj);
	        String line = "";
	        ArrayList<String> words = new ArrayList<String>();
	        while (myReader.hasNextLine())
	        {
	            line = myReader.nextLine();
	            String[] temp_words = line.split("\\s+");
	            words.addAll(Arrays.asList(temp_words));
	        }
	        return words;
	    }
	    
	    public float getGameLength() {
	    	return this.gameLength;
	    }
	    
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

	            TimedPractice e = null;
	            try {
	                FileInputStream fileIn = new FileInputStream(gameFile.toString());
	                ObjectInputStream inStream = new ObjectInputStream(fileIn);
	                e = (TimedPractice) inStream.readObject();
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

	            System.out.println();
	            System.out.println("Game Report:");
	            System.out.println("Name: " + e.username);
	            System.out.println("Game Length: " + e.gameLength);
	            System.out.println("Number of correct words: " + e.correctWords.length);
	            System.out.println("Number of incorrect words: " + e.incorrectWords.length);
	        }
	        else {
	        	System.out.println("Game report not found");
	        }
	    }


}
