/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package earthquakefilter;
import java.util.*;
/**
 *
 * @author matnod
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    public void addFilter(Filter filter) {
        filters.add(filter);
    }
    public String getName() {
        String FilterNames = "";
        for ( Filter f : filters ) {
            FilterNames = FilterNames + f.getName() + " ";
        }
    return FilterNames;
    }
    public boolean satisfies(QuakeEntry qe) {
        for ( Filter f: filters ) {
            if ( !f.satisfies(qe) ) {
                return false;
            }
        }
        return true;
    }
}
