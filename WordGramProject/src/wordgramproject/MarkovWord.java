/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgramproject;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author matnod
 */
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
	myText = text.split("\\s+");
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for ( int k=start; k<words.length - myOrder; k++ ) {
            WordGram wg = new WordGram(words, k, myOrder);
            if ( wg.equals(target) ) {
                return k;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram wg) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, wg, 0); //Get position of key
        while( index != -1 ) { //until end of file
            follows.add(myText[index + myOrder]);
            index = indexOf(myText, wg, index + 1); //search the next index after match with key
        }
    return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString()); // Add the key to string
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key); //Get the words yhat follow key
            //System.out.println("key: " + key.toString() + " follows: " + follows);
            if (follows.size() == 0 || follows.size() == 0 ) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}
