package spam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EKomarov on 02.11.2016.
 */

public interface VisualizationInterface <S,M> {
    S[][] convertData(Map<S,M> spamResult);
    void tableShow(S[][] data);
}
