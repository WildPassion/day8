package by.epam.dedik.day8.controller;

import by.epam.dedik.day8.entity.CustomBook;

import java.util.List;

public class BookResponse {
    private String message;
    private List<CustomBook> books;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CustomBook> getBooks() {
        return books;
    }

    public void setBooks(List<CustomBook> books) {
        this.books = books;
    }
}
