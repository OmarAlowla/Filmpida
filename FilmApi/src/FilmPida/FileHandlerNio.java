package FilmPida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileHandlerNio {

    private List<Film> filmList;
    private List<String> catList;

    public FileHandlerNio() {
        filmList = new ArrayList<>();
        catList = new ArrayList<>();
    }

    public void readLinesFromFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            List<String> fileContents = Files.readAllLines(path);
            filmList = parseLines(fileContents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Film> parseLines(List<String> lines) {
        List<Film> films = new ArrayList<>();

        for (String line : lines) {
            String[] values = line.split(";");
            if (values.length >= 4) {
                Film film = new Film(values[0], values[1], values[2], values[3]);
                films.add(film);
                catList.add(values[2]);
            } else {
                System.out.println("Invalid line: " + line);
            }
        }

        return films;
    }

    public List<Film> removeDuplicates() {
        if (filmList != null) {
            Set<Film> uniqueFilms = new HashSet<>(filmList);
            filmList.clear();
            filmList.addAll(uniqueFilms);
        }
        return filmList;
    }

    public List<String> removeDuplicatesCat() {
        if (catList != null) {
            Set<String> uniqueCategories = new HashSet<>(catList);
            catList.clear();
            catList.addAll(uniqueCategories);
        }
        return catList;
    }
}
