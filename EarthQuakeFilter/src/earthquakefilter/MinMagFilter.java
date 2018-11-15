package earthquakefilter;


import earthquakefilter.Filter;


/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin;
    private String Name;
    
    public MinMagFilter(double min, String name) { 
        magMin = min;
        Name = name;
    }
    
    public String getName(){
		return Name;
    }

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 

}
