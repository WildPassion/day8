package by.epam.dedik.day8.controller.command.impl;

import by.epam.dedik.day8.controller.BookResponse;
import by.epam.dedik.day8.controller.Params;
import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.CustomBookField;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SortByTagCommand implements BookCommand {
    private static final String SUCCESS = "Extracting sort list was successful";
    private static final String FAIL = "Extracting sort list did not happen";
    private Logger logger = Logger.getLogger(SortByTagCommand.class);

    @Override
    public void execute(Map<String, Object> request, BookResponse response) {
        CustomBookDao dao = new CustomBookDaoImpl();
        Object first = request.get(Params.FIELD);
        Object second = request.get(Params.FIELD_VALUE);
        if (first != null && first.getClass() == CustomBookField.class &&
                second != null && second.getClass() == Integer.class) {
            CustomBookField field = (CustomBookField) first;
            int value = Integer.parseInt(String.valueOf(second));
            try {
                List<Optional<CustomBook>> books = dao.sortByField(field, value);
                if (books.size() > value) {
                    response.setError(true);
                    response.setMessage(FAIL);
                } else {
                    response.setMessage(SUCCESS);
                    response.setBooks(books);
                }
            } catch (DaoException e) {
                logger.error(e);
                response.setError(true);
                response.setMessage(e.getMessage());
            }
        }
    }
}
