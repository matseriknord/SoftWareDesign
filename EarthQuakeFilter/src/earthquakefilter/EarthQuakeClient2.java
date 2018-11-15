package earthquakefilter;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "/Users/matnod/NetBeansProjects/EarthQuakeFilter/src/data/nov20quakedatasmall.atom";
        String source = "/Users/matnod/NetBeansProjects/EarthQuakeFilter/src/data/nov20quakedata.atom";
        //System.out.println(source);
        ArrayList<QuakeEntry> list  = parser.read(source); 
        System.out.println("read data for "+list.size()+" quakes");
        Location Tokyo = new Location(35.42, 139.43);
        //Filter f = new MinMagFilter(4.0);
        Filter fMag = new MagnitudeFilter(4.0, 5.0,"Magnitude");
        Filter fDep = new DepthFilter(-35000, -12000, "Depth");
        //ArrayList<QuakeEntry> m7  = filter(list, f);
        //ArrayList<QuakeEntry> m6  = filter(list, fMag);
        //ArrayList<QuakeEntry> m7  = filter(m6, fDep);
        //Location Tokio = new Location(35.42, 139.43);
        //Filter fPhrase = new PhraseFilter("end", "Japan","Phrase");
        //Filter fDist = new DistanceFilter(Tokyo, 10000000,"Distance");
        ArrayList<QuakeEntry> m6  = filter(list, fMag);
        ArrayList<QuakeEntry> m7  = filter(m6, fDep);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        System.out.println("# quakes = "+m7.size());
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "/Users/matnod/NetBeansProjects/EarthQuakeFilter/src/data/nov20quakedatasmall.atom";
        String source = "/Users/matnod/NetBeansProjects/EarthQuakeFilter/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source); 
        System.out.println("read data for "+list.size()+" quakes");
        //dumpCSV(list);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0, "Depth"));
        //maf.addFilter(new DistanceFilter(Tulsa, 10000000));
        maf.addFilter(new PhraseFilter("any", "a", "Phrase"));
        System.out.println(maf.toString());
        ArrayList<QuakeEntry> answer = filter(list, maf);
        dumpCSV(answer);
        System.out.println(maf.getName());
        System.out.println("# quakes = "+answer.size());
        
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "/Users/matnod/NetBeansProjects/EarthQuakeFilter/src/data/nov20quakedatasmall.atom";
        String source = "/Users/matnod/NetBeansProjects/EarthQuakeFilter/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source); 
        System.out.println("read data for "+list.size()+" quakes");
        //dumpCSV(list);
        Location Bilund = new Location(55.7308, 9.1153);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "Magnitude"));
        //maf.addFilter(new DepthFilter(-180000.0, -30000.0, "Depth"));
        maf.addFilter(new DistanceFilter(Bilund, 3000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "e", "Phrase"));
        //System.out.println(maf.toString());
        ArrayList<QuakeEntry> answer = filter(list, maf);
        dumpCSV(answer);
        System.out.println("Filters used: " + maf.getName());
        System.out.println("# quakes = "+answer.size());
        
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
