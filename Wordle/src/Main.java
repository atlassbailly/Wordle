import java.io.IOException;
import javax.swing.*;

public class Main {

    //main method, gives JOptionPane instructions pop up
    public static void main(String[] args) throws IOException {
        JOptionPane.showMessageDialog(null, "Hi there. Mr. Dorco.  \n" +
                "This is the word-guessing-game-that-totally-wasn't-someone-else's-idea.  \n" +
                "The goal is to guess the five letter word.  \n" +
                "Type five letters into the row using letter keys and backspace, then hit spacebar to guess the word.  \n" +
                "The letters in your guess will change color. Here's a guide to the colors:  \n" +
                "   Gray - letter isn't in the word  \n" +
                "   Yellow - letter is somewhere else in the word  \n" +
                "   Green - letter is in the same position in the word  \n" +
                "You get six chances to get it right - GOOD LUCK " +
                "ALSO DONT CLICK THE BUTTONS");
        View v = new View();
    }

}
