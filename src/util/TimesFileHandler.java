package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimesFileHandler extends File {
    public TimesFileHandler(String path) {
        super(path);
    }

    public List<String[]> readShowTimes() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                list.add(splitLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
