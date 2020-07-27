package by.epam.dedik.day8.dao;

import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.SortType;

import java.util.List;

public interface CustomBookDao {
    boolean addBook(CustomBook book) throws DaoException;

    boolean deleteBook(CustomBook book) throws DaoException;

    List<CustomBook> findByName(String name) throws DaoException;

    List<CustomBook> findByAuthor(String author) throws DaoException;

    List<CustomBook> findByYear(int year) throws DaoException;

    List<CustomBook> findByNumberPages(int numberPages) throws DaoException;

    List<CustomBook> sortByTag(SortType sortType) throws DaoException;
}
