package by.epam.dedik.day8.controller.command;

import by.epam.dedik.day8.controller.BookResponse;

import java.util.Map;

public interface BookCommand {
    void execute(Map<String, Object> request, BookResponse response);
}
