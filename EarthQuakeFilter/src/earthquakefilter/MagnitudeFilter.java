package earthquakefilter;


import earthquakefilter.Filter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matnod
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    private String Name;
    
    public MagnitudeFilter(double min, double max, String name) {
        magMin = min;
        magMax = max;
        Name = name;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }
    public String getName(){
		return Name;
	}
}
