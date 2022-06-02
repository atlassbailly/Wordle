//encapsulates a guess by the player
public class Guess {

    //the current guess (the characters currently typed in the bar)
    private char[] guess;
    //the index of the player's cursor, from 0 to 4.
    private int index;

    //returns the current cursor index
    public int getIndex() { return index; }
    //returns the current guess
    public char[] getGuess() { return guess; }

    //initializes cursor index at -1 and populates the guess array with 0
    public Guess(){
        guess = new char[5];
        for(int i = 0; i < 5; i++){
            guess[i] = '0';
        }
        index = -1;
    }

    //adds a character to the current guess, and updates the value of index
    public void add(char c){
        if(index < 4) {
            index++;
            guess[index] = c;
        }
    }

    //removes the last character of the current guess (replaces it with 0), and decrements the index
    public void backspace() {
        if (index > -1){
            guess[index] = '0';
            index--;
        }
    }
}