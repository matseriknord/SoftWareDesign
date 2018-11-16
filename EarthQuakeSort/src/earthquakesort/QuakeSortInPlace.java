package earthquakesort;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;



public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        for(int i = from +1; i < quakeData.size(); i++) {
            if ( quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth() ) {
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for ( int i=0; i< in.size(); i++ ) {
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry qTemp = in.get(i);
            QuakeEntry qMax = in.get(maxIndex);
            in.set(i, qMax);
            in.set(maxIndex, qTemp);
        }
    }
    
    public boolean checkInSorted(ArrayList<QuakeEntry> quakes) {
        for( int i=0; i<quakes.size(); i++ ) {
            if ( quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude() ) {
                return false;
            }
        }
        return true;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        //System.out.println("numSorted: " + numSorted);
        for( int i=0; i < quakeData.size() - 1 - numSorted; i++ ) {
            if( quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude() ) {
                //System.out.println("Changed i: " + quakeData.get(i).getMagnitude() + " to " + quakeData.get(i + 1).getMagnitude());
                QuakeEntry tempQe = quakeData.get(i);
                quakeData.set(i, quakeData.get(i +1 ));
                quakeData.set(i + 1 , tempQe);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for( int i=0 ;i<in.size() - 1; i++ ) {
            onePassBubbleSort(in, i);
            System.out.println("Printing quakes after pass: " + i);
            for (QuakeEntry qe: in) { 
            System.out.println(qe);
        } 
        }
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/matnod/NetBeansProjects/EarthQuakeSort/src/earthquakesort/data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        //sortByLargestDepth(list); 
        sortByMagnitudeWithBubbleSort(list);
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        System.out.println("EarthQuakes in sorted order:");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "/Users/matnod/NetBeansProjects/EarthQuakeSort/src/earthquakesort/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
