package spam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EKomarov on 25.10.2016.
 */

public interface SpamAnalyzerInterface <S> {
    S[] readRules(S rulesFile, S delim);
    Map<S,S> checkFile(S[] rules, Map<S,S> files);
}
