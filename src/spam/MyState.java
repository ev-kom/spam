package spam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by EKomarov on 18.11.2016.
 */
public class MyState {

    public static String[][] addFilesState(int i, int j, File file, String[][] dirState){
        dirState[i][0] = file.getName();
        dirState[i][1] = String.valueOf(file.length());
        dirState[i][2] = String.valueOf(file.lastModified());
        return dirState;
    }

    public static void writeStateFile(String[][] dirState){
        try {
            PrintWriter writer = new PrintWriter(String.valueOf(Paths.get(System.getProperty("user.home"), "spamdir", "mySpam", "last_state.txt")));
            for (String[] str : dirState){
                writer.println(Arrays.toString(str));
            }
            writer.close();
        } catch (FileNotFoundException e) {e.printStackTrace();
        }
    }
}
