package spam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public static void readState(){
        String stateDir = String.valueOf(Paths.get(System.getProperty("user.home"), "spamdir", "mySpam", "last_state.txt"));
        File state = new File(stateDir);
        if (!state.exists()){ System.out.println("Last state was not found"); System.exit(0);} //Дописать первый запуск при отсутствии файла с состоянием

        String lastState = null;
        ArrayList<String[]> sb = new ArrayList<>();
        try {       //Считывание правил из файла с помощью Files.newBufferedReader, запись в массив строк

            Path path = Paths.get(stateDir);
            BufferedReader br = Files.newBufferedReader(path);    //Считывает файл построчно
            while((lastState = br.readLine()) != null) {
                lastState = lastState.replaceAll("\\s+|\\[|\\]","");
                System.out.println(Arrays.toString(lastState.split(",")));
                sb.add(lastState.split(",|\\[|\\]"));
            }
            br.close();

            String[][] myState = new String [sb.size()][];
            for (int i = 0;  i < sb.size(); i++){
                String[] row = sb.get(i);
                myState[i] = row;
            }
            System.out.println(Arrays.deepToString(myState));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
