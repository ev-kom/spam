package spam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EKomarov on 25.10.2016.
 */

public interface SpamAnalyzerInterface <S,M> {
    S[] readRules(S rulesFile, S delim);
    Map<S,M> checkFile(S[] rules, Map<S,M> files);
}
