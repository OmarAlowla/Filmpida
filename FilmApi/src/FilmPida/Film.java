package FilmPida;

import java.util.Objects;

public class Film {



    private String id;
    private String name;
    private String cat;
    private String duration;

    @Override
    public String toString() {
        return id+" | " + name + " |";
    }

    public Film(String id, String name, String cat, String duration) {
        this.id = id;
        this.name = name;
        this.cat = cat;
        this.duration = duration;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film film)) return false;
        return Objects.equals(id, film.id) && Objects.equals(duration, film.duration) && Objects.equals(name, film.name) && Objects.equals(cat, film.cat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, cat, duration);
    }


}
