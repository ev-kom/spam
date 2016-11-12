package spam;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by EKomarov on 02.11.2016.
 */

public interface VisualizationInterface {
    String[][] convertData(HashMap<String,String> spamResult);
    void tableShow(String[][] data);
}
