package spam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by EKomarov on 25.10.2016.
 */

public interface SpamAnalyzerInterface {
    String[] readRules(String rulesFile, String delim);
    HashMap<String,String> checkFile(String[] rules, HashMap<String,String> files);
}
