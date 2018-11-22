/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MarkovInterface;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author matnod
 */
public class MarkovModel extends AbstractMarkovModel {
    
    private int NumN;

    public MarkovModel(int Nmodel) {
            myRandom = new Random();
            NumN = Nmodel;
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
        int index = myRandom.nextInt(myText.length() - NumN); //Get random index from 0 to minus key-length of myText.
        String key = myText.substring(index, index + NumN); //Get the key from position index.
        sb.append(key);//Add the key to the output string.
        for(int k=0; k < numChars - NumN; k++){ //Get next character from follows.
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
