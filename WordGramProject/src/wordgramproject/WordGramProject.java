/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgramproject;

/**
 *
 * @author matnod
 */
public class WordGramProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //WordGramTester wg = new WordGramTester();
       MarkovRunner mr = new MarkovRunner();
       mr.runMarkov();
    }
    
}
