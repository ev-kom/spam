package spam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by EKomarov on 25.10.2016.
 */

class FileReader implements FileReaderInterface<String,String> {
    
    public List<String> listOfFiles(String directory){
        File dir = new File(directory);
        if (!dir.exists()) System.exit(0);

            File[] listFiles = dir.listFiles();
            if (listFiles == null) return null;

            List<String> fileNames = new ArrayList<String>();
        for (File file : listFiles) {   //Запись имён файлов в директории в список List
                if (file.isFile() && !Objects.equals(file.getName(), "rules.txt")) {
//                System.out.println(file.getName());
                    fileNames.add(file.getName());
                }
            }
        return fileNames;
    }

    @Override
    public Map<String,String> filesReader(List<String> fileNames, String directory) {
        if (fileNames == null) return null;
        HashMap<String, String> filesData = new HashMap<>();

        for (String file : fileNames){
//            System.out.println(directory + "\\" + file);
            String path = directory + "\\" + file;
            try {   //Считывание данных из файлов с списке fileNames и запись их в HashMap
                filesData.put(file, new String(Files.readAllBytes(Paths.get(path))));    //С помощью Files.readAllBytes - всё содержимое файла выкачивается в память
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filesData;
    }


}
