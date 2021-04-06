package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import TypingPractice.GameMode;
import TypingPractice.TimedPractice;

public class GameModeTest {

	@Test
    void testGameModeUsername() {
        GameMode game = new TimedPractice("test_name", 60);
        assertEquals(game.getName(), "test_name");
    }
	

}
