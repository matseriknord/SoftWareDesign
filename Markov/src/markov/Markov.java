/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markov;

/**
 *
 * @author matnod
 */
public class Markov {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MarkovRunner mk = new MarkovRunner();
        //mk.runMarkovZero();
        mk.runMarkovOne();
        //Tester test = new Tester();
        //test.testGettFollows();
        //test.testGettFollowsWithFile();
    }
    
}
