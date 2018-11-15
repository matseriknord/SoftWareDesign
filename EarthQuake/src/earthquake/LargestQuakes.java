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
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        //for ( QuakeEntry qe : list ) {
        //    System.out.println(qe);
        //}
        //int indexLargest = indexOfLargest(list);
        //System.out.println("index of largest magnitude: " + indexLargest);
        //System.out.println(list.get(indexLargest).toString());
        //System.out.println(list.get(indexLargest).toString());
        ArrayList<QuakeEntry> answer = getLargest(list, 50);
        for ( QuakeEntry qe : answer ) {
            System.out.println(qe);
        }
    } 

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int indexLargest = 0;
        double  maxMagnitude = data.get(0).getMagnitude();
        //System.out.println(maxMagnitude);
        for (int k=1; k < data.size(); k++ ) {
            QuakeEntry qe = data.get(k);
            //System.out.println(k + " : " + qe.getMagnitude());
            if ( qe.getMagnitude() > maxMagnitude ) {
                indexLargest = k;
                maxMagnitude = data.get(k).getMagnitude();
            }
            //System.out.println("maxMagnitude : " + maxMagnitude);
            //System.out.println("IndexLargest : " + indexLargest);
        }
        return indexLargest;
    }
    public ArrayList<QuakeEntry>getLargest(ArrayList<QuakeEntry> data, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        for ( int k=0; k< howMany; k++ ) {
            int indexLargest = indexOfLargest(copy);
            QuakeEntry qe = copy.get(indexLargest);
            ret.add(qe);
            copy.remove(indexLargest);
        }
        return ret;    
    }
}
