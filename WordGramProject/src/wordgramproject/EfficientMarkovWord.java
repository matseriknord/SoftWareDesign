/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgramproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author matnod
 */
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> wordMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        wordMap = new HashMap<WordGram, ArrayList<String>>(); 
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
	myText = text.split("\\s+");
    }
    
    public void buildMap() {
        for (int k=0; k < myText.length -1;  k++ ) { //until end of file minus the rank of Markov model
            String next = ""; 
            WordGram wg = new WordGram(myText, k, myOrder); //Create a new WordGram object for each word
            
            if ( !wordMap.containsKey(wg) && k  == myText.length - myOrder) {
                ArrayList<String> wordString = new ArrayList<String>();
                wordMap.put(wg, wordString);
                next = "";
                //System.out.println("k = " + k + " : broken");
                break;   
            }
            if (wordMap.containsKey(wg) && k  + myOrder  < myText.length ) { //If key is present allready then add the next word to the 
                next = myText[k + myOrder]; //Then get the next word
                wordMap.get(wg).add(next); //ArrayList of Strings
            }
            else if (!wordMap.containsKey(wg) && k  + myOrder  < myText.length ){
                ArrayList<String> wordString = new ArrayList<String>(); //Create new ArrayList of Strings and add to HashMap
                wordString.add(myText[k + myOrder]);
                wordMap.put(wg, wordString);
            }
            //System.out.println("k : " + k + " WordGram: " + wg);
        }
    }
    
    public void printHashMapInfo() {
        int maxSize = 0;
        for ( WordGram wg : wordMap.keySet() ) {
            if (wordMap.get(wg).size() > maxSize ) {
                maxSize = wordMap.get(wg).size();
            }
            if ( wordMap.size() < 200 ) { //Only print for smalll maps
                System.out.println("Key: " + wg + " Value: \t" + wordMap.get(wg) + "\n"  );   
            }
        }
        System.out.println("Number of keys in the HashMap: " + wordMap.size());
        System.out.println("Size of largest list of characters in the HashMap: " + maxSize);
        System.out.println("Keys that have the maximum value: ");
        for ( WordGram wg : wordMap.keySet() ) {
            if (wordMap.get(wg).size() == maxSize ) {
                System.out.println("Key: " + wg + " Value: \t" + wordMap.get(wg) );
            }
        }
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
    
    private ArrayList<String> getFollows(WordGram wg) { //Only return corresponding WordGram in hash map
        return wordMap.get(wg);
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString()); // Add the key to string
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key); //Get the words that follow key
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
