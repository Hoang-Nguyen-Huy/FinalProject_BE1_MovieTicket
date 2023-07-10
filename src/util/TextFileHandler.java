package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TextFileHandler extends File {
    final static String DATE_FORMAT = "dd/MM/yyyy";
    public TextFileHandler(String path) {
        super(path);
    }

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
            reader.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return list;
    }
    public void writeFilm(FilmManager filmList, String filmID)  {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(filmList.get(filmID).getFilmId());
            writer.write(",");
            writer.write(filmList.get(filmID).getName());
            writer.write(",");
            writer.write(filmList.get(filmID).getDirector());
            writer.write(",");
            writer.write(String.valueOf(filmList.get(filmID).getDuration()));
            writer.write(",");
            // format Date, only show dd/MM/yyyy
            writer.write(new SimpleDateFormat(DATE_FORMAT).format(filmList.get(filmID).getDate()));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

