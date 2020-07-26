package by.epam.dedik.day8.controller.command.impl;

import by.epam.dedik.day8.controller.BookRequest;
import by.epam.dedik.day8.controller.BookResponse;
import by.epam.dedik.day8.controller.Params;
import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;

import java.util.List;

public class FindByNameCommand implements BookCommand {
    @Override
    public void execute(BookRequest request, BookResponse response) {
        CustomBookDao dao = new CustomBookDaoImpl();
        String name = request.getParameter(Params.NAME);
        List<CustomBook> books = null;
        try {
            books = dao.findByName(name);
        } catch (DaoException e) {
            // TODO: 26.07.2020 exception in response
        }
        response.setBooks(books);
    }
}
