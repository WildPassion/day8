package by.epam.dedik.day8.dao;

import by.epam.dedik.day8.entity.CustomBook;

import java.util.List;
import java.util.Optional;

public interface CustomBookDao {
    boolean addBook(CustomBook book) throws DaoException;

    boolean deleteBook(CustomBook book) throws DaoException;

    List<Optional<CustomBook>> findByField(CustomBookField field, String value) throws DaoException;

    List<Optional<CustomBook>> sortByField(CustomBookField field, int limit) throws DaoException;
}
