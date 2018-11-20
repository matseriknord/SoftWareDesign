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
        //System.out.println("myText: " + myText);
        //System.out.println("myText length : " + myText.length());
        //System.out.println("key: " + key);
        //System.out.println(myText.indexOf(key));
        while( pos < myText.length() - 1 &&  myText.indexOf(key, pos) != -1 ) { //until end of file or trying read beyond bound
            index = myText.indexOf(key, pos); //Get position of key
            if ( index < myText.length() - key.length()) { //check if index + key length is within bound off string
                follows.add(myText.substring(index + key.length(), index + key.length() + 1)); //add n+1 letter after key
            }
            pos = index + 1; //search from position after match with key
        }
        //System.out.println("follows: " + follows);
        //System.out.println("Length follows: " + follows.size());
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
            int index = myRandom.nextInt(myText.length() - 1); //Get random index from 0 to length of myText.
            String key = myText.substring(index, index + 1); //Get the key from position index.
            sb.append(key);//Add the key to the output string.
            for(int k=0; k < numChars - 1; k++){ //Get next character from follows.
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
