package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import TypingPractice.TimedPractice;

public class TimedPracticeTest {

	
   
    @Test
    void testTimedPracticeUsername() {
        TimedPractice game = new TimedPractice("test_name", 60);
        assertEquals(game.getName(), "test_name");
    }
    
    @Test
    void testTimedPracticeGameLength() {
        TimedPractice game = new TimedPractice("test_name", 60);
        assertEquals(game.getGameLength(), 60.0);
    }
    
    
	@Test
    void testTimedPracticeChooseWords() {

		ArrayList<String> words;
		String input = "no\n1\n\nSomebody\ne\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        TimedPractice game = new TimedPractice("test_name", 1);
        try{
        	words = game.chooseWordsPrompt();
        }
        catch(FileNotFoundException e) {
        	return;
        }
        assertEquals(words.get(0), "Somebody");
    }
	
}
