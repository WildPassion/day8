package by.epam.dedik.day8.entity;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class CustomBook {
    private int id;
    private String name;
    private List<String> authors;
    private int year;
    private int numberPages;

    public CustomBook() {
    }

    public CustomBook(String name, List<String> authors, int year, int numberPages) {
        this.name = name;
        this.authors = authors;
        this.year = year;
        this.numberPages = numberPages;
    }

    public CustomBook(int id, String name, List<String> authors, int year, int numberPages) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.year = year;
        this.numberPages = numberPages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomBook book = (CustomBook) o;

        if (year != book.year) return false;
        if (numberPages != book.numberPages) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        return authors != null ? authors.equals(book.authors) : book.authors == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + numberPages;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomBook.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("name=\"" + name + "\"")
                .add("authors=" + authors)
                .add("year=" + year)
                .add("numberPages=" + numberPages)
                .toString();
    }
}
