/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markov;

import edu.duke.FileResource;

/**
 *
 * @author matnod
 */
public class Tester {
    public void testGettFollows() {
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markov.setTraining(st);
        markov.getFollows("es");
    }
    
    public void testGettFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        String key = "he";
        System.out.println("Number of characters that follows key " + key + " is: " + markov.getFollows(key).size());
    }
}
