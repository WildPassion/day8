package by.epam.dedik.day8.service.impl;

import by.epam.dedik.day8.dao.CustomBookAuthorDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookAuthorDaoImpl;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import by.epam.dedik.day8.service.CustomBookAuthorService;
import by.epam.dedik.day8.service.ServiceException;
import by.epam.dedik.day8.validator.AuthorValidator;

import java.util.Optional;

public class CustomBookAuthorServiceImpl implements CustomBookAuthorService {
    private CustomBookAuthorDao dao = new CustomBookAuthorDaoImpl();
    private AuthorValidator validator = new AuthorValidator();

    @Override
    public boolean addAuthor(CustomBookAuthor author) throws ServiceException {
        boolean result;
        if (validator.isValid(author)) {
            try {
                result = dao.addAuthor(author);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Author invalid");
        }
        return result;
    }

    @Override
    public boolean deleteAuthor(CustomBookAuthor author) throws ServiceException {
        boolean result;
        if (validator.isValid(author)) {
            try {
                result = dao.deleteAuthor(author);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Author invalid");
        }
        return result;
    }

    @Override
    public boolean updateAuthor(CustomBookAuthor oldAuthor, CustomBookAuthor newAuthor) throws ServiceException {
        boolean result;
        if (validator.isValid(oldAuthor) && validator.isValid(newAuthor)) {
            try {
                result = dao.updateAuthor(oldAuthor, newAuthor);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Author invalid");
        }
        return result;
    }

    @Override
    public Optional<CustomBookAuthor> findAuthor(CustomBookAuthor author) throws ServiceException {
        Optional<CustomBookAuthor> result;
        if (validator.isValid(author)) {
            try {
                result = dao.findAuthor(author);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Author invalid");
        }
        return result;
    }
}
