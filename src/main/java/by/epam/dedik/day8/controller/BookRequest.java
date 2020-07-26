package by.epam.dedik.day8.controller;

import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.BookConverterService;
import by.epam.dedik.day8.service.SortType;

public class BookRequest {
    private static final String EMPTY = "";
    private int year;
    private int numberPages;
    private CustomBook book;
    private String name;
    private String author;
    private SortType sortType;
    private String command;

    public String getParameter(Params params) {
        String parameter = EMPTY;
        switch (params) {
            case BOOK -> parameter = book == null ? EMPTY : BookConverterService.toJson(book);
            case NAME -> parameter = name == null ? EMPTY : name;
            case AUTHOR -> parameter = author == null ? EMPTY : author;
            case YEAR -> parameter = String.valueOf(year);
            case NUMBER_PAGES -> parameter = String.valueOf(numberPages);
            case SORT_TYPE -> parameter = String.valueOf(sortType);
            case COMMAND -> parameter = command;
        }
        return parameter;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBook(CustomBook book) {
        this.book = book;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
