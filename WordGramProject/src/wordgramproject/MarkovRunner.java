package wordgramproject;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.concurrent.TimeUnit;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord5 = new MarkovWord(5); 
        runModel(markovWord5, st, 200, 844); 
    }
    
    public void testHashMap() {
        //String st = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord mk2 = new EfficientMarkovWord(2);
        mk2.setTraining(st);
        mk2.setRandom(65);
        mk2.buildMap();
        mk2.printHashMapInfo();
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
	st = st.replace('\n', ' ');
	int size = 100;
        int seed = 42;
        long start = System.nanoTime();
        EfficientMarkovWord mTwo = new EfficientMarkovWord(2);
        mTwo.setTraining(st);
        mTwo.buildMap();
        //mTwo.printHashMapInfo();
        mTwo.setRandom(seed);
        
        System.out.println("running with " + mTwo);
        for(int k=0; k < 3; k++){
            String out= mTwo.getRandomText(size);
            printOut(out);
	}
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        
        
        long start2 = System.nanoTime();
        MarkovWord markov = new MarkovWord(2);
        markov.setTraining(st);
        markov.setRandom(seed);
        
        System.out.println("running with " + mTwo);
        for(int k=0; k < 3; k++){
            String out= markov.getRandomText(size);
            printOut(out);
	}
        long finish2 = System.nanoTime();
        long timeElapsed2 = finish2 - start2;
       
        System.out.println(mTwo.toString() + " Time elapsed. " + TimeUnit.SECONDS.convert(timeElapsed, TimeUnit.MILLISECONDS) + " mSeconds");
        System.out.println(markov.toString() + " Time elapsed. " + TimeUnit.SECONDS.convert(timeElapsed2, TimeUnit.MILLISECONDS) + " mSeconds");
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
