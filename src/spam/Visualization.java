package spam;

import javax.swing.*;
import java.awt.*;
import java.util.*;


/**
 * Created by EKomarov on 02.11.2016.
 */
public class Visualization implements VisualizationInterface <String,String> {

    @Override
    public String[][] convertData(Map<String,String> spamResult) {
        String[][] tableData = new String[spamResult.size()][2];

        int i = 0;
        for (Map.Entry<String,String> entry : spamResult.entrySet()){   //Запись HashMap в двумерный массив
            tableData[i][0] = entry.getKey();
            tableData[i][1] = entry.getValue();
            i++;
        }
//        System.out.println(Arrays.deepToString(table));

        Arrays.sort(tableData,
                Comparator.comparing((String[] entry) -> entry[0]));    //Сортировка массива

        return tableData;
    }

    @Override
    public void tableShow(String[][] tableData) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        String[] tableNames = {"File Name", "Spam Status"};
        JFrame frame = new JFrame("Spam table");
        JTable spamTable = new JTable(tableData, tableNames);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(spamTable);

        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
