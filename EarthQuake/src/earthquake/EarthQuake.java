/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package earthquake;

/**
 *
 * @author matnod
 */
public class EarthQuake {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //EarthQuakeClient ec = new EarthQuakeClient();
       //ClosestQuakes cq = new ClosestQuakes();
       //ec.createCSV();
       //ec.bigQuakes();
       //cq.findClosestQuakes();
       LargestQuakes lq = new LargestQuakes();
       lq.findLargestQuakes();
       //ec.quakesOfDepth();
    }
    
}
