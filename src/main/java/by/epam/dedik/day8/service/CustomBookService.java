package by.epam.dedik.day8.service;

import by.epam.dedik.day8.dao.CustomBookField;
import by.epam.dedik.day8.entity.CustomBook;

import java.util.List;
import java.util.Optional;

public interface CustomBookService {
    boolean addBook(CustomBook book) throws ServiceException;

    boolean deleteBook(CustomBook book) throws ServiceException;

    List<Optional<CustomBook>> findByField(CustomBookField field, String value) throws ServiceException;

    List<Optional<CustomBook>> sortByField(CustomBookField field, int limit) throws ServiceException;
}
