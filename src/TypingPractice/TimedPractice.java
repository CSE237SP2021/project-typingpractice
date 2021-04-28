package TypingPractice;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/*
 * Creates a game with a fixed length and prompts users to enter as many words as
 * they can before time runs out
 */


public class TimedPractice extends GameMode {

    private static final long serialVersionUID = 6529685098447757690L;
    public String username;
    public float gameLength;
    ArrayList<String> words;
    int correctWordsCounter = 0;
    String[] correctWords;
    String[] incorrectWords;


    public TimedPractice(String n, float l){
        super(n);
        this.username = n;
        this.gameLength = l;
    }
    
    public void run(){
	
    	//Sets the word set to be used by the player
    	try{
			this.words = chooseWordsPrompt();
		}
		catch(FileNotFoundException e){
			return;
		}

		System.out.println("INSTRUCTIONS: type each word that appears in the console exactly and press enter when finished. Work as quickly and accurately as possible. " +
                "Continue working until the time is up. \nPress ENTER when you are ready to begin.");
		Scanner in = new Scanner(System.in); 
		String s = in.nextLine(); 
		
        
		//While the user still has time, track correct and incorrect words
		ArrayList<String> correctWordsTemp= new ArrayList<String>();
        ArrayList<String> incorrectWordsTemp= new ArrayList<String>();
        long startTime = System.currentTimeMillis();
        processWords(in, correctWordsTemp, incorrectWordsTemp, startTime);
        
        // Gives a score report and allows the user to save their score 
        correctWordsCounter--;     
        this.correctWords = correctWordsTemp.toArray(new String[correctWordsTemp.size()]);
        this.incorrectWords = incorrectWordsTemp.toArray(new String[incorrectWordsTemp.size()]);
        this.print();
        s = in.nextLine(); 
        if (s.equalsIgnoreCase("save")){
            save();
        }
        return;
    }

	
    //Called by the run method, processWords() checks whether the game is still running and there are more words
    //and processes the user input and updates all class member variables accordingly 
    private void processWords(Scanner in, ArrayList<String> correctWordsTemp, ArrayList<String> incorrectWordsTemp,
			long startTime) {
		String s;
		for (int i = 0; i < this.words.size(); i++){
            if (System.currentTimeMillis() - startTime < 1000*this.gameLength)
            {
                System.out.println(this.words.get(i));
                s = in.nextLine(); 
                if (s.equals(this.words.get(i))){
                    correctWordsCounter++;
                    correctWordsTemp.add(s);
                }
                else{
                    incorrectWordsTemp.add(s);
                }
            }
        }
	}

    
    /* Asks the user if they would like to randomize the words they will use and
     * retrieves a corresponding set of practice words 
     */

	public ArrayList<String> chooseWordsPrompt() throws FileNotFoundException{
		Scanner in = new Scanner(System.in); 
        
        ArrayList<String> practiceWords = null;
        
        System.out.println("Would you like to randomize your word set? Yes/no");
        String s = in.nextLine();
        if (s.equalsIgnoreCase("yes")) {
	        try{
	            practiceWords = getPracticeWords(new Random().nextInt(2) + 1);
	            this.words = practiceWords;
	        } catch (FileNotFoundException e) {
	            System.out.println("Practice words not found");
	        }
        }
        else if (s.equalsIgnoreCase("no")) {
        	try{
                System.out.println("Enter the number of the word set you would like to use.");
                System.out.println("1. All Star");
                System.out.println("2. Never Gonna Give You Up");
        		int i = in.nextInt();
	            practiceWords = getPracticeWords(i);
	            this.words = practiceWords;
	        } catch (FileNotFoundException e) {
	            System.out.println("Practice words not found. Randomizing practice words");
	            
	            try{
		            practiceWords = getPracticeWords(new Random().nextInt(2) + 1);
		            this.words = practiceWords;
		        } catch (FileNotFoundException f) {
		            System.out.println("Practice words not found");
		        }
	            
	        }
        }
        else {
        	practiceWords = chooseWordsPrompt();
        }
		return practiceWords;
	}
	
	/*
	 *  Saves a game report for a timed game. Information included in the report is:
	 *  	user name
	 *  	time and date
	 *  	game length
	 *  	number of correct words
	 *  	number of incorrect words
	 *  Games are saved by serializing an object of TimedPractice 
	 */

    public void save(){
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-HH:mm:ss");
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

    /* Retrieves a practice set of words from src/resources corresponding to the integer
     * passed to the function. Practice words are text files with the naming convention:
     *		practice[int].txt
     */
    
    public ArrayList<String> getPracticeWords(int word_set) throws FileNotFoundException {


        File myObj = new File("src/resources/practice" + word_set + ".txt");


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
    
    
    
    public void print() {
    	System.out.println();
        System.out.println("GAME REPORT:");
        System.out.println("Name: " + this.username);
        System.out.println("Game mode: timed game");
        System.out.println("Game Length: " + this.gameLength);
        System.out.println("Number of correct words: " + this.correctWords.length);
        System.out.println("Number of incorrect words: " + this.incorrectWords.length);
    }

}

