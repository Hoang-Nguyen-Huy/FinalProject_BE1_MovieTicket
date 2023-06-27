package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileHandler extends File {




    public TextFileHandler(String path) {
        super(path);
    }

//    public String readAccounts() throws IOException {
//
//
//        while ((line = reader.readLine()) != null) {
//            stringBuilder.append(line);
//            stringBuilder.append(System.lineSeparator());
//        }
//
//        reader.close();
//        return stringBuilder.toString();
//    }

    public List<String[]> readFilms() {
        List<String[]> list = new ArrayList();
        String[] line = {};
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this));
            String fString = reader.readLine();
            while (fString != null) {
                line = fString.split(",");
                list.add(line);
                fString = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return list;
    }
//    public void write(String content) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(this));
//        writer.write(content);
//        writer.close();
//    }
}

