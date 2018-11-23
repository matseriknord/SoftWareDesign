package MarkovInterface;


/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 
import java.text.DecimalFormat;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
	}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	int size = 200;
        int seed = 32;
		
        MarkovZero mz = new MarkovZero();
        mz.toString(0);
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        mz.toString(1);
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        mz.toString(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        mz.toString(4);
        runModel(mFour, st, size, seed);
    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
	st = st.replace('\n', ' ');
	int size = 50;
        int seed = 42;
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        EfficentMarkovModel mTwo = new EfficentMarkovModel(2);
        mTwo.toString(2);
        mTwo.setTraining(st);
        mTwo.buildMap();
        mTwo.printHashMapInfo();
        mTwo.setRandom(seed);
        
        System.out.println("running with " + mTwo);
        for(int k=0; k < 3; k++){
            String out= mTwo.getRandomText(size);
            printOut(out);
	}
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
	st = st.replace('\n', ' ');
	int size = 1000;
        int seed = 42;
        long start = System.nanoTime();
        EfficentMarkovModel mTwo = new EfficentMarkovModel(2);
        mTwo.toString(2);
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
        MarkovModel markov = new MarkovModel(2);
        markov.toString(2);
        markov.setTraining(st);
        markov.setRandom(seed);
        
        System.out.println("running with " + mTwo);
        for(int k=0; k < 3; k++){
            String out= markov.getRandomText(size);
            printOut(out);
	}
        long finish2 = System.nanoTime();
        long timeElapsed2 = finish2 - start2;
       
        System.out.println(mTwo.toString() + " Time elapsed. " + timeElapsed + " nSeconds");
        System.out.println(markov.toString() + " Time elapsed. " + timeElapsed2 + " nSeconds");
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
