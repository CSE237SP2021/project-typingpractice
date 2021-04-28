package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import TypingPractice.HardPractice;
import TypingPractice.TimedPractice;
import TypingPractice.TypingPractice;


public class HardPracticeTests {
	private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   
    
	@Test
    void timeToEnter() {
		HardPractice game =  new HardPractice("test_user",5);
		assertEquals(game.wordTimer, 5);
    }
	
	@Test
    void testHardPracticeUsername(){
		HardPractice game = new HardPractice("John", 5);
		assertEquals(game.username, "John");
	}

	@Test
    void testWordCounter(){
		String input = "\nme\nwe\nhe\nshe\nher\nhim\nlet\nnew\nfile\nlol\nkk";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        HardPractice game = new HardPractice("test_user", 5);
        game.run();

        assertEquals(game.correctWordsCounter, 9);
        
	}
	
	
	
	@Test
    void testIncorrectWord(){
	    String input = "\nme\nwe\nhe\nshe\nher\nhim\nlet\nnew\nfile\nlol";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        HardPractice game = new HardPractice("test_user", 5);
        game.run();
        
        assertEquals(game.incorrectWord, "lol");
	}
	
	
	

				
	
    
 
    

}
