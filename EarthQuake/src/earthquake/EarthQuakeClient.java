package earthquake;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for ( QuakeEntry qe : quakeData) {
            if ( qe.getMagnitude() > magMin ) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for ( int k = 0; k < copy.size(); k++) {
            QuakeEntry quake = copy.get(k);
            Location loc = quake.getLocation();
            double distance = loc.distanceTo(from)/1000;
            //System.out.println(quake.getInfo() + " Distance: " +distance);
            if ( loc.distanceTo(from)/1000 < 
                     distMax) {
                answer.add(quake);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if ( qe.getDepth() < maxDepth && qe.getDepth() > minDepth ) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            if ( where == "start" && info.startsWith(phrase)) {
                answer.add(qe);
            }
            if ( where == "end" && info.endsWith(phrase)) {
                answer.add(qe);
            }
            if ( where == "any" ) {
                if ( info.indexOf(phrase) >= 1 && info.indexOf(phrase) <= info.length()-1 ) {
                answer.add(qe);
                }
            }
        }
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> result = filterByDepth(list, minDepth, maxDepth);
        for(QuakeEntry qe : result) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+ result.size() + " quakes that match criteria" );

    }
    
    
    public void dumpCSV(ArrayList<QuakeEntry> list, Location from){
        System.out.println("Latitude,Longitude,Magnitude,Info,Distance");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s,4.7f\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo(),
                qe.getLocation().distanceTo(from)/1000);
        }
    }
        
    public void dumpDistance(ArrayList<QuakeEntry> list, Location from){
        System.out.println("Distance,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.7f,%s\n",
                qe.getLocation().distanceTo(from)/1000,
                qe.getInfo());
        }
        System.out.println("Found " + list.size() + " quakes that match that criteria");
    }

        
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for ( QuakeEntry qe : listBig ) {
            System.out.println(qe);
        }
        System.out.println("Found "+ listBig.size() + " quakes that match criteria" );

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //System.out.println("read data for "+list.size()+" quakes");
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        Location bridgeport = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000, bridgeport );
        dumpDistance(answer, bridgeport);
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
//        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
          ArrayList<QuakeEntry> list  = parser.read(source);
          String where = "any";
          String phrase = "Can";
          ArrayList<QuakeEntry> answer = filterByPhrase(list, where, phrase);
         System.out.println("read data for "+list.size()+" quakes");
        //quakesOfDepth();
        //closeToMe();
        //Location city =  new Location(38.17, -118.82);
        //dumpCSV(list, city);
        //System.out.println("# quakes read: " + list.size());
        //dumpCSV(answer);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        System.out.println("Found "+ answer.size() + " quakes that match " + phrase + " at " + where );
    }
    
}
