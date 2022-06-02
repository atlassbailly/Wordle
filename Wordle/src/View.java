import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//gui
public class View {

    //frame holds pan
    JFrame frame;
    //pan holds the character slots
    JPanel pan;
    //buttons are the character slots
    JButton[][] buttons;
    //keeps track of all guesses in a 5x6 array
    char[][] letters;
    //the current guess number
    int rowCurrent;
    //the current guess
    Guess _guess;
    //keycontroller
    Typer _type;
    //array of possible correct words
    char[][] words = {
            { 'H', 'E', 'L','L','O'},
            { 'P', 'Z', 'A','Z','Z'},
            { 'C', 'A', 'B','I','N'},
            { 'V', 'O', 'I','C','E'},
            { 'C', 'R', 'A','N','E'},
            { 'P', 'O', 'I','N','T'},
            { 'O', 'C', 'E','A','N'},
            { 'I', 'M', 'A','G','E'},
            { 'J', 'U', 'I','C','E'},
            { 'F', 'R', 'A','M','E'},
            { 'C', 'L', 'I','C','K'},
            { 'E', 'N', 'E','M','Y'},
            { 'F', 'O', 'R','U','M'},
            { 'F', 'A', 'L','S','E'},
            { 'G', 'O', 'I','N','G'},
            { 'H', 'A', 'R','R','Y'},
            { 'H', 'E', 'N','C','E'},
            { 'L', 'A', 'Y','E','R'},
            { 'L', 'E', 'A','S','E'},
            { 'M', 'I', 'N','O','R'},
            { 'M', 'A', 'N','O','R'},
            { 'N', 'E', 'V','E','R'},
            { 'O', 'C', 'C','U','R'},
            { 'O', 'T', 'H','E','R'},
            { 'P', 'L', 'A','C','E'},
            { 'Q', 'U', 'I','C','K'},
            { 'R', 'I', 'V','A','L'},
            { 'S', 'E', 'N','S','E'},
            { 'U', 'N', 'D','U','E'},
            { 'W', 'A', 'S','T','E'},
            { 'V', 'I', 'T','A','L'},
            { 'W', 'R', 'O','T','E'},
            { 'Y', 'I', 'E','L','D'},
            { 'Y', 'O', 'U','T','H'},
            { 'F', 'O', 'R','C','E'},
            { 'P', 'O', 'W','E','R'},
    };
    //the current word
    char[] word;

    //properly initializes all instance variables
    public View() throws IOException {
        _guess = new Guess();
        _type = new Typer(this, _guess);
        rowCurrent = 0;
        word = words[(int) (Math.random() * words.length)];

        buttons = new JButton[6][5];
        letters = new char[6][5];
        for(int r = 0; r < 6; r++){
            for(int c = 0; c < 5; c++){
                buttons[r][c] = new JButton("");
                letters[r][c] = '0';
            }
        }
        frame = new JFrame();
        pan = new JPanel();
        frame.setLayout(new GridLayout());
        pan.setLayout(new GridLayout(6, 5));
        frame.setSize(780, 650);
        pan.setSize(780, 650);

        for(int r = 0; r < 6; r++){
            for(int c = 0; c < 5; c++){
                buttons[r][c].setOpaque(true);
                buttons[r][c].setFont(new Font("TimesRoman", Font.PLAIN, 36));
                pan.add(buttons[r][c]);
            }
        }
        frame.addKeyListener(_type);
        frame.add(pan);
        frame.setDefaultCloseOperation(3);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //if the guess is a valid word, it compares the guess to the correct word, letter by letter, setting the appropriate color for each tile
    //checks win and lose conditions
    public void guess() throws FileNotFoundException {
        String s = "";
        for(int i = 0; i < 5; i++){
            s += _guess.getGuess()[i];
        }
        boolean win = false;
        if(Dictionary.isInDictionary(s, new Scanner(getClass().getResourceAsStream("/Dictionary.txt")))) {
            win = true;
            if(_guess.getIndex() == 4) {
                boolean found = false;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (_guess.getGuess()[i] == word[j])
                            found = true;
                    }
                    JButton b = buttons[rowCurrent][i];
                    if (_guess.getGuess()[i] == word[i]) {
                        b.setBackground(Color.GREEN);
                        b.setBorderPainted(false);
                    } else if (found) {
                        b.setBackground(Color.YELLOW);
                        b.setBorderPainted(false);
                        win = false;
                    } else {
                        b.setBackground(Color.GRAY);
                        b.setBorderPainted(false);
                        win = false;
                    }
                    found = false;
                }
                _guess = new Guess();
                _type.setGuess(_guess);
                rowCurrent++;
            }
        } else{
            JOptionPane.showMessageDialog(frame, "That's not a word! Try again :/");
        }
        if(win)
            win();
        else if(rowCurrent == 6)
            lose();
    }

    //updates the board using the letters array, called after the player types any letter
    public void reDraw(){
        frame.remove(pan);
        String s;
        for(int r = 0; r < 6; r++){
            for(int c = 0; c < 5; c++){
                if(letters[r][c] == '0') {
                    buttons[r][c].setText("");
                }
                else {
                    s = "";
                    s += letters[r][c];
                    buttons[r][c].setText(s);
                }
            }
        }
        frame.add(pan);
        frame.repaint();
        frame.setVisible(true);
    }

    //updates the letters array with the guess
    public void updateCurrent(){
        for(int c = 0; c < 5; c++){
            letters[rowCurrent][c] = _guess.getGuess()[c];
        }
    }

    //displays a win message and restarts the game
    public void win()  {
        JOptionPane.showMessageDialog(frame, "Wow... congrats. Five letters is big. You must feel very proud.  \n" +
                        "You know what else is big? Your " + (rowCurrent) + " guesses. Yeah.");
        restart();
    }

    //displays a lose message and restarts the game
    public void lose(){
        JOptionPane.showMessageDialog(frame, "Wow... you suck. There were more guesses than letters. How does that feel? ");
        restart();
    }

    //redoes the constructor
    public void restart() {
        _guess = new Guess();
        _type = new Typer(this, _guess);
        rowCurrent = 0;
        word = words[(int) (Math.random() * words.length)];

        buttons = new JButton[6][5];
        letters = new char[6][5];
        for(int r = 0; r < 6; r++){
            for(int c = 0; c < 5; c++){
                buttons[r][c] = new JButton("");
                letters[r][c] = '0';
            }
        }
        frame = new JFrame();
        pan = new JPanel();
        frame.setLayout(new GridLayout());
        pan.setLayout(new GridLayout(6, 5));
        frame.setSize(780, 650);
        pan.setSize(780, 650);

        for(int r = 0; r < 6; r++){
            for(int c = 0; c < 5; c++){
                buttons[r][c].setOpaque(true);
                buttons[r][c].setFont(new Font("TimesRoman", Font.PLAIN, 36));
                pan.add(buttons[r][c]);
            }
        }
        frame.addKeyListener(_type);
        frame.add(pan);
        frame.setDefaultCloseOperation(3);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
