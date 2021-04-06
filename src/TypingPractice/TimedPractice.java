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
        try{
            ArrayList<String> practiceWords = getPracticeWords();
            this.words = practiceWords;
        } catch (FileNotFoundException e) {
            System.out.println("Practice words not found");
            return;
        }
        System.out.println("INSTRUCTIONS: type each word that appears in the console exactly and press enter when finished. Work as quickly and accurately as possible. " +
                "Continue working until the time is up. \nPress ENTER when you are ready to begin.");
        Scanner in = new Scanner(System.in); 
        String s = in.nextLine();
        ArrayList<String> correctWordsTemp= new ArrayList<String>();
        ArrayList<String> incorrectWordsTemp= new ArrayList<String>();
        long startTime = System.currentTimeMillis();
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
        this.correctWords = correctWordsTemp.toArray(new String[correctWordsTemp.size()]);
        this.incorrectWords = incorrectWordsTemp.toArray(new String[incorrectWordsTemp.size()]);
        System.out.println("Time's up! You got " + correctWordsCounter + " words correct in " + gameLength + " seconds.");
        System.out.println("Enter 'save' to save a report of your score");
        s = in.nextLine(); 
        if (s.equalsIgnoreCase("save")){
            save();
        }

    }

    public void save(){
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

        
        int random = new Random().nextInt(2) + 1;
        //String text_path = "Practice_Words/practice" + random + ".txt";
        //File myObj = new File(text_path);


        File myObj = new File("src/resources/practice" + random + ".txt");

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

    public float getGameLength(){
        float s = 0;
        while (s <= 0 || s > 120){
            Scanner in = new Scanner(System.in);
            System.out.println("How many seconds would you like this round to last? Enter a number between 0-120");
            s = in.nextFloat();
        }
        return s;
    }

}

