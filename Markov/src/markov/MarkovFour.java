/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markov;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author matnod
 */
public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
            myRandom = new Random();
    }
    
    public ArrayList<String>getFollows(String key) {
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
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
                return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4); //Get random index from 0 to minus key-length of myText.
        String key = myText.substring(index, index + 4); //Get the key from position index.
        sb.append(key);//Add the key to the output string.
        for(int k=0; k < numChars - 4; k++){ //Get next character from follows.
            ArrayList<String> follows = new ArrayList<String>(getFollows(key));
            if ( follows.size() == 0 ) {
                break;
            }
            index = myRandom.nextInt(follows.size());//Get random index from follows.
            String next = follows.get(index);//Get character from position index.
            sb.append(next);//Add character after the key
            key = key.substring(1) + next;//Leap one step forward after key.
        }
        return sb.toString();
    } 
}
