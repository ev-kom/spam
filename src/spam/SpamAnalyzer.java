package spam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by EKomarov on 31.10.2016.
 */

public class SpamAnalyzer implements SpamAnalyzerInterface {


    @Override
    public String[] readRules(String rulesFile, String delim) {   //Реализует считывание правил из файла. rulesFile - путь к файлу
        File spam = new File(rulesFile);
        if (!spam.exists()){ System.out.println("File with the rules was not found"); System.exit(0);}

        String spamRules = null;
        StringBuilder sb = new StringBuilder();
        try {       //Считывание правил из файла с помощью Files.newBufferedReader, запись в массив строк
            Path path = Paths.get(rulesFile);
            BufferedReader br = Files.newBufferedReader(path);    //Считывает файл построчно, и записывает в файл
            while((spamRules = br.readLine()) != null) sb.append(spamRules);
            spamRules = sb.toString();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spamRules != null ? spamRules.split(delim) : new String[0];
    }

    @Override
    public HashMap<String,String> checkFile(String[] rules, HashMap<String,String> files) {
        boolean flag = false;
        for (Map.Entry<String,String> entry : files.entrySet()){    //Перезапись значений HashMap files на true/false
            flag = false;
//            System.out.println(entry.getKey() + "/" + entry.getValue());
            for (String ruleText : rules)
                if (entry.getValue().contains(ruleText)){ entry.setValue("true"); flag = true; break; } //flag = true при выполнении условия в файле спам
                if (!flag) entry.setValue("false");     //Если не одно условие не нашлось в строке, то flag = false и в файлне не спам
            }
        return files;
        }

    }

