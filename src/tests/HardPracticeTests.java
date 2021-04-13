package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;


import TypingPractice.TimedPractice;
import TypingPractice.TypingPractice;


public class HardPracticeTests {
	private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   
    
    ///fix to apply to hardPractice mode
	@Test
    void testUsernamePrompt(){
        System.setOut(new PrintStream(outContent));
        
        String input = "e";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
		
    	TypingPractice typingPractice = new TypingPractice();
    	
        assertEquals(outContent.toString(), "Please enter your current or desired username\n");
    }
	
	
	@Test
    void testMenuLength(){

        String input = "e";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

    	
    	TypingPractice typingPractice = new TypingPractice();
    	
        assertEquals(typingPractice.menu.length, 5);
    }
    
    @Test
    void testTypingPracticeUsername(){

        String input = "e";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

    	
    	TypingPractice typingPractice = new TypingPractice();
    	
        assertEquals(typingPractice.username, "e");
    }
    

}
