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
public class DepthFilter implements Filter {
    private double depMin;
    private double depMax;
    private String Name;
    
    public DepthFilter(double min, double max, String name) {
        depMin = min;
        depMax = max;
        Name = name;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth()>= depMin && qe.getDepth()<= depMax;
    }
    public String getName(){
		return Name;
    }
}
