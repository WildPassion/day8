package by.epam.dedik.day8.dao;

import by.epam.dedik.day8.entity.CustomBookAuthor;

import java.util.Optional;

public interface CustomBookAuthorDao {
    boolean addAuthor(CustomBookAuthor author) throws DaoException;

    boolean deleteAuthor(CustomBookAuthor author) throws DaoException;

    boolean updateAuthor(CustomBookAuthor oldAuthor, CustomBookAuthor newAuthor) throws DaoException;

    Optional<CustomBookAuthor> findAuthor(CustomBookAuthor author) throws DaoException;
}
