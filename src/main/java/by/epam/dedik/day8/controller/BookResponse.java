package by.epam.dedik.day8.controller;

import by.epam.dedik.day8.entity.CustomBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookResponse {
    private boolean error;
    private String message;
    private List<Optional<CustomBook>> books;

    public BookResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
        books = new ArrayList<>();
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Optional<CustomBook>> getBooks() {
        return books;
    }

    public void setBooks(List<Optional<CustomBook>> books) {
        this.books = books;
    }
}
