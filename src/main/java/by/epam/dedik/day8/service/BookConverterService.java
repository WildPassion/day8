package by.epam.dedik.day8.service;

import by.epam.dedik.day8.entity.CustomBook;
import com.google.gson.Gson;

public class BookConverterService {
    private BookConverterService() {
    }

    public static CustomBook toBook(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CustomBook.class);
    }

    public static String toJson(CustomBook book) {
        Gson gson = new Gson();
        return gson.toJson(book);
    }
}
