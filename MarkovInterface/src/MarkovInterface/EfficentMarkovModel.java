/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MarkovInterface;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matnod
 */
public class EfficentMarkovModel extends AbstractMarkovModel {
    
    private int NumN;
    private HashMap<String, ArrayList<String>> wordMap;

    public EfficentMarkovModel(int Nmodel) {
            myRandom = new Random();
            NumN = Nmodel;
            wordMap = new HashMap();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        System.out.println("myText: " + myText);
    }
    
    public void buildMap() {
        System.out.println("myText: " + myText);
        
        int pos = 0;
        for (int k=0; k < myText.length() - NumN; k++ ) { //until end of file
            String key = myText.substring(k, k + NumN); //Get the key from position index.
            String next = myText.substring(k + key.length(), k + key.length() + 1); //add n+1 letter after key
            //String next = myText.substring(k , k + NumN); //add n+1 letter after key
            //System.out.println("key: " + key);
            //System.out.println("next: " + next);
            if ( !wordMap.containsKey(key) ) {
                wordMap.put(key, new ArrayList<String>());
            }
            wordMap.get(key).add(next);
            //System.out.println("myMap: " + wordMap);
        }
    }
    
    public ArrayList<String> getFollows(String key) {
        System.out.println("Efficent method " + wordMap.get(key));
        return wordMap.get(key);
    }
    
    public void printHashMapInfo() {
        int maxWords = 0;
        int numKeys = wordMap.size();
        ArrayList<String> maxKeys = new ArrayList<String>();
        for ( Map.Entry <String, ArrayList<String>> ee : wordMap.entrySet() ) {
            String key = ee.getKey();
            ArrayList words = ee.getValue();
            if ( words.size() > maxWords ) {
                maxWords = words.size();
            }
            System.out.println(key + " " + words.toString());
        }
        for ( Map.Entry <String, ArrayList<String>> ee : wordMap.entrySet() ) {
            if ( ee.getValue().size() == maxWords ) {
                maxKeys.add(ee.getKey());
            }
        }
        System.out.println("Number of keys: " + numKeys);
        System.out.println("Size of largest Key list: " + maxWords);
        System.out.println("Keys of Maximum size: " + maxKeys);
    }
    public String getRandomText(int numChars){
        if (myText == null){
                return "";
        }
        HashMap<String, ArrayList<String>> wordMap = new HashMap<String, ArrayList<String>>();
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
