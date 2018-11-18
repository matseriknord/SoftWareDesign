package efficientsort;
/**
 * Write a description of class MagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        /* Get last word in the String title */
        String LastTitle1 = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ") + 1);
        String LastTitle2 = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ") + 1);
        if ( LastTitle1. compareTo(LastTitle2) < 0 ) {
            return -1;
        }
        if ( LastTitle1.compareTo(LastTitle2) > 0 ) {
            return 1;
        }
        if ( LastTitle1.compareTo(LastTitle2) == 0 ) {
            if ( qe1.getMagnitude() < qe2.getMagnitude() ) {
                    return -1;
                }
                if ( qe1.getMagnitude() > qe2.getMagnitude() ) {
                    return 1;
                }
        }
        return 0;
    }
    
}
