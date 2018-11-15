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
public class PhraseFilter implements Filter {
    private String filtWhere;
    private String filtPhrase;
    private String Name;
    
    public PhraseFilter(String where, String phrase, String name ) {
        filtWhere = where;
        filtPhrase = phrase;
        Name = name;
    }
    public String getName(){
		return Name;
    }
    public boolean satisfies(QuakeEntry qe ) {
        String info = qe.getInfo();
        if ( filtWhere == "start" && info.startsWith(filtPhrase)) {
                return(true);
            }
            if ( filtWhere == "end" && info.endsWith(filtPhrase)) {
                return(true);
            }
            if ( filtWhere == "any" ) {
                if ( info.indexOf(filtPhrase) >= 1 && info.indexOf(filtPhrase) <= info.length()-1 ) {
                return(true);
                }
            }
    return(false);
    }
}
