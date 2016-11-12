package spam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by EKomarov on 25.10.2016.
 */

public interface FileReaderInterface {
    List listOfFiles(String directory);
    HashMap<String,String> filesReader(List fileNames, String directory);
}
