package spam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Created by EKomarov on 25.10.2016.
 */

public class Maker {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("os.name"));

        String directory = Paths.get(System.getProperty("user.home"),"spamdir").toString();
        System.out.println(directory); //Директория с проверяемыми файлами
        String rulesFile = Paths.get(System.getProperty("user.home"),"spamdir","rules.txt").toString();  //Путь к файлу с правилами
        String delim = String.valueOf(MyProperties.getProperties().getProperty("delim"));   //Разделитель (в файле с правилами)

        List<String> fileNames; //Список названий файлов в директории

        FileReaderInterface<String,String> fileReader;
        fileReader = new FileReader();

        fileNames = fileReader.firstStartList(directory);  //Получаем список файлов в директории
        System.out.println(fileNames);
        Map<String,String> files = fileReader.filesReader(fileNames,directory);     //Записываем в HashMap ключ - имя файла, значение - содержание файла
        System.out.println(files);

        SpamAnalyzerInterface<String,String> spamAnalyzer;
        spamAnalyzer = new SpamAnalyzer();
        System.out.println(Arrays.toString(spamAnalyzer.readRules(rulesFile, delim)));  //Считываем файл с правилами
        Map<String,String> spamResult = spamAnalyzer.checkFile(spamAnalyzer.readRules(rulesFile,delim),files);  //Возвращаем HashMap ключ - имя файл, значение - Спам/неСпам
        System.out.println(spamResult);

        VisualizationInterface<String,String> visualization;
        visualization = new Visualization();
        visualization.tableShow(visualization.convertData(spamResult));  //Конвертируем HashMap в двумерный массив и показываем

        WatchFileChanges watcher = new WatchFileChanges();
        try {
            watcher.watchService(directory);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
    }


