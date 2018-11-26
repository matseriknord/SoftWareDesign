package wordngram;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
	myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key: " + key + " follows: " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }
    private int indexOf(String[] words, String target, int start) {
        for ( int k=start; k<words.length; k++ ) {
            if ( words[k].equals(target) ) {
                return k;
            }
        }
        return -1;
    }

    public void testIndexOf() {
        String[] testString = {"this","is","just","a","test","yes","this","is","a","simple","test"};
        System.out.println("Word: this in position 0 " + indexOf(testString, "this", 0) );
        System.out.println("Word: this in position 3 " + indexOf(testString, "this", 3) );
        System.out.println("Word: frog in position 0 " + indexOf(testString, "frog", 0) );
        System.out.println("Word: frog in position 5 " + indexOf(testString, "frog", 5) );
        System.out.println("Word: simple in position 2 " + indexOf(testString, "simple", 2) );
        System.out.println("Word: test in position 5 " + indexOf(testString, "test", 5) );

    }
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText,key, 0); //Get position of key
        while( index != -1 ) { //until end of file
            follows.add(myText[index + 1]);
            index = indexOf(myText, key, index + 1); //search from position after match with key
        }
    return follows;
    }
}