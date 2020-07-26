package by.epam.dedik.day8.controller.command.impl;

import by.epam.dedik.day8.controller.BookRequest;
import by.epam.dedik.day8.controller.BookResponse;
import by.epam.dedik.day8.controller.Params;
import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.BookConverterService;

public class AddBookCommand implements BookCommand {
    public static final String SUCCESS = "Adding was successful";

    @Override
    public void execute(BookRequest request, BookResponse response) {
        CustomBookDao dao = new CustomBookDaoImpl();
        CustomBook book = BookConverterService.toBook(request.getParameter(Params.BOOK));
        try {
            dao.addBook(book);
            response.setMessage(SUCCESS);
        } catch (DaoException e) {
            response.setMessage(e.getMessage());
        }
    }
}
