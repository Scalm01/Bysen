import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        List<String> messages = new ArrayList<>();
        messages.add("Test message");
        game.setMessages(messages);
    }

    @Test
    void getMessages() {
        assertNotNull(game.getMessages());
        assertEquals(1, game.getMessages().size());
    }

    @Test
    void setMessages() {
        List<String> messages = new ArrayList<>();
        messages.add("Test message 2");
        game.setMessages(messages);
        assertEquals(messages, game.getMessages());
    }

//    @Test
//    public void startNewGame() {
//        game.startNewGame();
//        assertFalse(game.gameOver);
//        game.setPlayerName("Test");
//        assertNotNull(game.getPlayerName());
//        assertEquals("Test", game.getPlayerName());
//    }
}