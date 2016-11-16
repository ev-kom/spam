package spam;
import java.util.*;

/**
 * Created by EKomarov on 25.10.2016.
 */

public interface FileReaderInterface <S> {
    List<S> listOfFiles(S directory);
    Map<S,S> filesReader(List<S> fileNames, S directory);
}