package spam;

import java.nio.file.Paths;
import java.util.*;

/**
 * Created by EKomarov on 25.10.2016.
 */

public class Maker {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("os.name"));

        String directory = Paths.get(System.getProperty("user.home"),".spam","input").toString();
        System.out.println(directory); //Директория с проверяемыми файлами
        String rulesFile = "D:\\java\\spamdir\\rules\\spam.txt";   //Путь к файлу с правилами
        String delim = ";";   //Разделитель (в файле с правилами)
        List fileNames = new ArrayList(); //Список названий файлов в директории

        FileReaderInterface fileReader;
        fileReader = new FileReader();

        fileNames = fileReader.listOfFiles(directory);  //Получаем список файлов в директории
        System.out.println(fileNames);
        HashMap<String,String> files = fileReader.filesReader(fileNames,directory);     //Записываем в HashMap ключ - имя файла, значение - содержание файла
        System.out.println(files);

        SpamAnalyzerInterface spamAnalyzer;
        spamAnalyzer = new SpamAnalyzer();
        System.out.println(Arrays.toString(spamAnalyzer.readRules(rulesFile, delim)));  //Считываем файл с правилами
        HashMap<String,String> spamResult = spamAnalyzer.checkFile(spamAnalyzer.readRules(rulesFile,delim),files);  //Возвращаем HashMap ключ - имя файл, значение - Спам/неСпам
        System.out.println(spamResult);

        VisualizationInterface visualization;
        visualization = new Visualization();
        visualization.tableShow(visualization.convertData(spamResult));  //Конвертируем HashMap в двумерный массив и показываем


        }
    }


