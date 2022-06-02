import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import javafx.scene.input.*;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_SPACE;

//manages user input from the keyboard
public class Typer implements KeyListener {

    //the current guess
    private Guess _guess;
    //the view to which this keylistener is assigned
    private View _view;

    //sets a new current guess
    public void setGuess(Guess g){
        _guess = g;
    }

    //initializes with the relevant guess and view objects
    public Typer(View v, Guess g){
        _view = v;
        _guess = g;
    }

    //nothing
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //nothing
    @Override
    public void keyPressed(KeyEvent e) {

    }

    //adds a typed letter to guess, also handles backspace and guess functionality
    @Override
    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == VK_SPACE){
            try {
                _view.guess();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (key.getKeyCode() == 8){
            _guess.backspace();
        } else if (key.getKeyCode() == 65) {
            _guess.add('A');
        } else if (key.getKeyCode() == 66) {
            _guess.add('B');
        } else if (key.getKeyCode() == 67) {
            _guess.add('C');
        } else if (key.getKeyCode() == 68) {
            _guess.add('D');
        } else if (key.getKeyCode() == 69) {
            _guess.add('E');
        } else if (key.getKeyCode() == 70) {
            _guess.add('F');
        } else if (key.getKeyCode() == 71) {
            _guess.add('G');
        } else if (key.getKeyCode() == 72) {
            _guess.add('H');
        } else if (key.getKeyCode() == 73) {
            _guess.add('I');
        } else if (key.getKeyCode() == 74) {
            _guess.add('J');
        } else if (key.getKeyCode() == 75) {
            _guess.add('K');
        } else if (key.getKeyCode() == 76) {
            _guess.add('L');
        } else if (key.getKeyCode() == 77) {
            _guess.add('M');
        } else if (key.getKeyCode() == 78) {
            _guess.add('N');
        } else if (key.getKeyCode() == 79) {
            _guess.add('O');
        } else if (key.getKeyCode() == 80) {
            _guess.add('P');
        } else if (key.getKeyCode() == 81) {
            _guess.add('Q');
        } else if (key.getKeyCode() == 82) {
            _guess.add('R');
        } else if (key.getKeyCode() == 83) {
            _guess.add('S');
        } else if (key.getKeyCode() == 84) {
            _guess.add('T');
        } else if (key.getKeyCode() == 85) {
            _guess.add('U');
        } else if (key.getKeyCode() == 86) {
            _guess.add('V');
        } else if (key.getKeyCode() == 87) {
            _guess.add('W');
        } else if (key.getKeyCode() == 88) {
            _guess.add('X');
        } else if (key.getKeyCode() == 89) {
            _guess.add('Y');
        } else if (key.getKeyCode() == 90) {
            _guess.add('Z');
        }

        _view.updateCurrent();
        _view.reDraw();
    }
}
