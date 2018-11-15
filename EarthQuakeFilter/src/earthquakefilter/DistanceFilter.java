package earthquakefilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matnod
 */
public class DistanceFilter implements Filter {
    Location location;
    double distMax;
    private String Name;
    
    public DistanceFilter(Location loc, double max, String name) {
        location = loc;
        distMax = max;
        Name = name;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < distMax;
    }
    public String getName(){
		return Name;
    }
    
}
