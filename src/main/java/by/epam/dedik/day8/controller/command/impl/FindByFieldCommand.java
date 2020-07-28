package by.epam.dedik.day8.controller.command.impl;

import by.epam.dedik.day8.controller.BookResponse;
import by.epam.dedik.day8.controller.Params;
import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.CustomBookField;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FindByFieldCommand implements BookCommand {
    public static final String SUCCESS = "Adding was successful";

    @Override
    public void execute(Map<String, Object> request, BookResponse response) {
        CustomBookDao dao = new CustomBookDaoImpl();
        Object first = request.get(Params.FIELD);
        Object second = request.get(Params.FIELD_VALUE);
        if (first != null && first.getClass() == CustomBookField.class &&
                second != null && second.getClass() == String.class) {
            CustomBookField field = (CustomBookField) first;
            String value = String.valueOf(second);
            try {
                List<Optional<CustomBook>> books = dao.findByField(field, value);
                response.setMessage(SUCCESS);
                response.setBooks(books);
            } catch (DaoException e) {
                // TODO: 28.07.2020 log
                response.setError(true);
                response.setMessage(e.getMessage());
            }
        }
    }
}