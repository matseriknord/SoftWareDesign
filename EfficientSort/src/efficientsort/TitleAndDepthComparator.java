package efficientsort;
/**
 * Write a description of class MagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String Title1 = qe1.getInfo();
        String Title2 = qe2.getInfo();
        if ( Title1.compareTo(Title2) < 0 ) {
            return -1;
        }
        if ( Title1.compareTo(Title2) > 0 ) {
            return 1;
        }
        if ( Title1.compareTo(Title2) == 0 ) {
            if ( qe1.getDepth() < qe2.getDepth() ) {
                    return -1;
                }
                if (qe1.getDepth() > qe2.getDepth()) {
                    return 1;
                }
        }
        return 0;
    }
    
}
