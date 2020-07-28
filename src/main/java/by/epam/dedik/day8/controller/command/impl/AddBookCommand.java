package by.epam.dedik.day8.controller.command.impl;

import by.epam.dedik.day8.controller.BookResponse;
import by.epam.dedik.day8.controller.Params;
import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;
import org.apache.log4j.Logger;

import java.util.Map;


public class AddBookCommand implements BookCommand {
    private static final String FAIL = "Adding did not happen";
    private static final String SUCCESS = "Adding was successful";
    private Logger logger = Logger.getLogger(AddBookCommand.class);

    @Override
    public void execute(Map<String, Object> request, BookResponse response) {
        CustomBookDao dao = new CustomBookDaoImpl();
        Object o = request.get(Params.BOOK);
        if (o != null && o.getClass() == CustomBook.class) {
            CustomBook book = (CustomBook) o;
            try {
                if (!dao.addBook(book)) {
                    response.setError(true);
                    response.setMessage(FAIL);
                } else {
                    response.setError(false);
                    response.setMessage(SUCCESS);
                }
            } catch (DaoException e) {
                logger.error(e);
                response.setError(true);
                response.setMessage(e.getMessage());
            }
        }
    }
}
