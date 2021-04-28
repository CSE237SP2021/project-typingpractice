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

        String input = "e";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    	
    	  TypingPractice typingPractice = new TypingPractice();
    	
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
    
    @Test
    void testLogOutNewUser() { 
    	String newUsername = "b";
    	System.setIn(new ByteArrayInputStream(newUsername.getBytes()));
        typingPractice.logOut();
        assertEquals(typingPractice.username, newUsername);

    }
    
    @Test
    void testLogOutQuit() { 
    	String newUsername = "quit";
    	System.setIn(new ByteArrayInputStream(newUsername.getBytes()));
        typingPractice.logOut();
        assertEquals(outContent.toString().split("\n")[2], "Thanks for playing.");
    }
    


}