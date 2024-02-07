package FilmPida;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    FileHandlerNio fileHandler = new FileHandlerNio();
    private String filePath = "src/FilmPida/data.txt";
    private List<Film> splitValuesList;
    private List<String> catList;
    private View frame = new View();
    DefaultListModel<Film> listModel = new DefaultListModel<Film>();
    DefaultListModel<String> catModel = new DefaultListModel<String>();

    public Controller() {
        init();

    }

    private void init() {

        fileHandler.readLinesFromFile(filePath);
        splitValuesList = fileHandler.removeDuplicates();
        catList = fileHandler.removeDuplicatesCat();
        fill("asc");
        frame.getAbsteigend().addActionListener(e -> fill("asc"));
        frame.getAufsteigend().addActionListener(e -> fill("desc"));
        frame.getDuration().addActionListener(e -> fill("dur"));
        frame.getZurueck().addActionListener(e -> fill("asc"));
        frame.getSearch().addActionListener(e -> search(frame.getSearchBar().getText()));
        frame.getSearchBar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                search(frame.getSearchBar().getText());
            }
        });
        catList.forEach(s -> frame.getCatCombo().addItem(s));
        frame.getCatCombo().addActionListener(e -> getByCat(frame.getCatCombo().getSelectedItem().toString()));
        frame.getFilmList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                getImg(frame.getFilmList().getSelectedValue().toString());
            }
        });
    }

    private void fill(String sortingWay) {
        listModel.clear();
        if (splitValuesList != null) {
            List<Film> toSort = new ArrayList<>(splitValuesList);

            if (sortingWay.equals("asc")) {
                toSort.sort(Comparator.comparingInt(film -> Integer.parseInt(film.getId())));
            } else if (sortingWay.equals("desc")) {
                toSort.sort(Comparator.comparingInt(film -> Integer.parseInt(((Film) film).getId())).reversed());
            } else if (sortingWay.equals("dur")) {
                toSort.sort(Comparator.comparingInt(film -> Integer.parseInt(film.getDuration())));
            }

            for (Film film : toSort) {
                listModel.addElement(film);
            }

            frame.getFilmList().setModel(listModel);
        } else {
            System.err.println("Failed to read the file.");
        }
    }


    private void search(String word) {
        if (splitValuesList != null) {
            List<Film> filteredList = splitValuesList.stream()
                    .filter(item -> item.getName().toLowerCase().trim().contains(word.toLowerCase().trim()))
                    .toList();

            listModel.clear();
            for (Film film : filteredList) {
                listModel.addElement(film);
            }

            frame.getFilmList().setModel(listModel);;

        } else {
            System.err.println("Failed to read the file.");
        }
    }

    private void getByCat(String cat) {
        if (splitValuesList != null) {
            List<Film> filteredList = splitValuesList.stream()
                    .filter(item -> item.getCat().trim().equalsIgnoreCase(cat.trim()))
                    .collect(Collectors.toList());
            listModel.clear();
            for (Film film : filteredList) {
                listModel.addElement(film);
            }

            frame.getFilmList().setModel(listModel);
        } else {
            System.err.println("Failed to read the file.");
        }
    }

    public void getImg(String filmname) {
        String[] parts = filmname.split("\\|");
        String result = parts[1].trim();
        String URL = "https://api.themoviedb.org/3/search/movie?api_key=5588196a398d4fbbe172a130482f206a&query="
                + result;
        String response = new UrlToJson(URL).getResponse();
        String img = response.split("/")[2].split("g")[0] + "g";
        img ="https://image.tmdb.org/t/p/w500/" + img;
        try {
            java.net.URL imageUrl = new URL(img);
            Image image = ImageIO.read(imageUrl);
            ImageIcon lblimg = new ImageIcon(image.getScaledInstance(124, 138, Image.SCALE_SMOOTH));
            frame.getLabltoimg().setIcon(lblimg);
                frame.getLabltoimg().setBounds(500, 11, 124, 138);
            } catch (IOException e) {
                e.printStackTrace();
        }
        System.out.println(img);
    }
}