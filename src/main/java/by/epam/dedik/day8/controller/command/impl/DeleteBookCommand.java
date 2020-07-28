package by.epam.dedik.day8.controller.command.impl;

import by.epam.dedik.day8.controller.BookResponse;
import by.epam.dedik.day8.controller.Params;
import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.CustomBookService;
import by.epam.dedik.day8.service.ServiceException;
import by.epam.dedik.day8.service.impl.CustomBookServiceImpl;
import org.apache.log4j.Logger;

import java.util.Map;

public class DeleteBookCommand implements BookCommand {
    private static final String SUCCESS = "Removing was successful";
    private Logger logger = Logger.getLogger(DeleteBookCommand.class);

    @Override
    public void execute(Map<String, Object> request, BookResponse response) {
        CustomBookService service = new CustomBookServiceImpl();
        Object o = request.get(Params.BOOK);
        if (o != null && o.getClass() == CustomBook.class) {
            CustomBook book = (CustomBook) o;
            try {
                service.deleteBook(book);
                response.setError(false);
                response.setMessage(SUCCESS);
            } catch (ServiceException e) {
                logger.error(e);
                response.setError(true);
                response.setMessage(e.getMessage());
            }
        }
    }
}
