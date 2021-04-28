package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TypingPractice.HardPractice;
import TypingPractice.HighScores;

class HighScoresTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String username = "e";
    private HighScores highScores;
   
    
    @BeforeEach 
    void setOutput() {
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(username.getBytes()));
        highScores = new HighScores();
    }
    
    
	@Test
    void testDisplayEmpty(){
        highScores.print();
		assertEquals(outContent.toString(), "No High Scores Currently Saved\n");
    }
    
    @Test
    void testAddScorePrintFormatting(){
    	HardPractice game = new HardPractice(username,1);
    	highScores.addScore(game);
    	highScores.print();
		assertEquals(outContent.toString(), "High Scores for Hard Game Mode:"
				+ "\n----------------------------------------------\n1.   e    0\n");
    }
    
    @Test
    void testAddMultiple(){
    	HardPractice game = new HardPractice(username,1);
    	HardPractice game1 = new HardPractice(username+"1",1);
    	highScores.addScore(game);
    	highScores.addScore(game1);
    	highScores.print();
		assertEquals(outContent.toString(), "High Scores for Hard Game Mode:"
				+ "\n----------------------------------------------\n1.   e    0\n2.   e1    0\n");
    }
    
    @Test
    void testAddMultipleOrdered(){
    	HardPractice game = new HardPractice(username,1);
    	
		String input = "\nme\nwe\nhe\nshe\nher\nhim\nlet\nnew\nfile\nlol\nkk";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        HardPractice game1 = new HardPractice("test_user", 5);
        game1.run();
    	
    	highScores.addScore(game);
    	highScores.addScore(game1);
    	highScores.print();
    	
		assertEquals(outContent.toString().split("\n")[18], "1.   test_user    9");
		assertEquals(outContent.toString().split("\n")[19], "2.   e    0");

    }
	
	
	
    


}