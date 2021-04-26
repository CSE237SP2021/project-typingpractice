package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TypingPractice.TypingPractice;

class TypingPracticeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String username = "e";
    private TypingPractice typingPractice;
   
    
    @BeforeEach 
    void setOutput() {
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(username.getBytes()));
    	typingPractice = new TypingPractice();
    }
    
    
	@Test
    void testUsernamePrompt(){
        assertEquals(outContent.toString(), "Please enter your current or desired username\n");
    }
	
	
	@Test
    void testMenuLength(){
        assertEquals(typingPractice.menu.length, 7);
    }
    
    @Test
    void testTypingPracticeUsername(){    	
        assertEquals(typingPractice.username, username);
    }
    
    
    @Test
    void testQuit () {    	
        assertEquals(typingPractice.getMenu()[6], "Quit");
    }
    


}