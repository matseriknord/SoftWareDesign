/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markov;

import java.util.*;

/**
 *
 * @author matnod
 */
public class MarkovOne {

    private String myText;
    private Random myRandom;

    public MarkovOne() {
            myRandom = new Random();
    }
    
    public ArrayList<String>getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        int index = 0;
        System.out.println("myText: " + myText);
        System.out.println("myText length : " + myText.length());
        System.out.println("key: " + key);
        System.out.println(myText.indexOf(key));
        while( pos < myText.length() - 1 &&  myText.indexOf(key, pos) != -1 ) {
            //System.out.println("Index: " + index);
            index = myText.indexOf(key, pos);
            //System.out.println("Index key: " + index);
            if ( index < myText.length() - key.length()) {
                //System.out.println("Index char: " + myText.substring(index + key.length(), index + key.length()+1));
                follows.add(myText.substring(index + key.length(), index + key.length() + 1));
            }
            pos = index + 1;
        }
        System.out.println("follows: " + follows);
        System.out.println("Length follows: " + follows.size());
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
            for(int k=0; k < numChars; k++){
                    int index = myRandom.nextInt(myText.length());
                    sb.append(myText.charAt(index));
            }

            return sb.toString();
    } 
}
