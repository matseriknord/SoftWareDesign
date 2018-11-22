package MarkovInterface;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String>getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while( pos < myText.length() ) { //until end of file
            int start = myText.indexOf(key, pos); //Get position of key
            if ( start == -1 ) {
                break;
            }
            if ( start + key.length() > myText.length() - key.length()) { //check if index + key length is within bound off string
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1); //add n+1 letter after key
            follows.add(next);
            pos = start + key.length(); //search from position after match with key
        }
        return follows;
    }
}
