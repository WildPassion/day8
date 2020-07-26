package by.epam.dedik.day8.controller.command;

import by.epam.dedik.day8.controller.BookRequest;
import by.epam.dedik.day8.controller.BookResponse;

public interface BookCommand {
    void execute(BookRequest request, BookResponse response);
}
