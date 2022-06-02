import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readAllBytes;

//keeps a list of english words, and has a method to check if a given word is on that list
public class Dictionary {

    //no argument constructor, class only has a static method
    public Dictionary() {

    }

    //returns true if the parameter word is a real english word (if it is on the dictionary list_
    public static boolean isInDictionary(String word, Scanner dictionary) {

        List<String> dictionaryList = new ArrayList<String>();
        for (int i = 0; dictionary.hasNextLine() != false; i++) {
            dictionaryList.add(dictionary.nextLine());
            if (dictionaryList.get(i).equalsIgnoreCase(word)) {
                return true;
            }
        }

        return false;

    }
}