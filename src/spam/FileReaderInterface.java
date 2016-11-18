package spam;
import java.util.*;

/**
 * Created by EKomarov on 25.10.2016.
 */

public interface FileReaderInterface <S,M> {
    List<S> firstStartList(S directory);
    Map<S,M> filesReader(List<S> fileNames, S directory);
}