package by.epam.dedik.day8.service.impl;

import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.CustomBookField;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.CustomBookService;
import by.epam.dedik.day8.service.ServiceException;
import by.epam.dedik.day8.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class CustomBookServiceImpl implements CustomBookService {
    private CustomBookDao dao = new CustomBookDaoImpl();
    private BookValidator validator = new BookValidator();

    @Override
    public boolean addBook(CustomBook book) throws ServiceException {
        boolean result;
        if (validator.isValid(book)) {
            try {
                result = dao.addBook(book);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Invalid book");
        }
        return result;
    }

    @Override
    public boolean deleteBook(CustomBook book) throws ServiceException {
        boolean result;
        if (validator.isValid(book)) {
            try {
                result = dao.deleteBook(book);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Invalid book");
        }
        return result;
    }

    @Override
    public List<Optional<CustomBook>> findByField(CustomBookField field, String value) throws ServiceException {
        List<Optional<CustomBook>> result;
        try {
            result = dao.findByField(field, value);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Optional<CustomBook>> sortByField(CustomBookField field, int limit) throws ServiceException {
        List<Optional<CustomBook>> result;
        try {
            result = dao.sortByField(field, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
