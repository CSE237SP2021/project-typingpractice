package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
    private String username = "test_user";
    private HardPractice hardPractice;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   
    
    @BeforeEach 
    void setOutput() {
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(username.getBytes()));
    	
        String input = "\nme\nwe\nhe\nshe\nher\nhim\nlet\nnew\nfile\nlol\nkk";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    	
        hardPractice = new HardPractice(username, 5);
    }
    
	@Test
    void timeToEnter() {
		assertEquals(hardPractice.wordTimer, 5);
    }
	
	@Test
    void testHardPracticeUsername(){
		assertEquals(hardPractice.username, "test_user");
	}

	@Test
    void testWordCounter(){
        hardPractice.run();
        assertEquals(hardPractice.correctWordsCounter, 9);
	}
	
	@Test
    void testIncorrectWord(){
        hardPractice.run();
        assertEquals(hardPractice.incorrectWord, "text");
	}
	@Test
    void testIncorrectAnswer(){
        hardPractice.run();
        assertEquals(hardPractice.incorrectAnswer, "lol");
	}
	
	
	

				
	
    
 
    

}
