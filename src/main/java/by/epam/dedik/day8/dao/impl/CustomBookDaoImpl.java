package by.epam.dedik.day8.dao.impl;

import by.epam.dedik.day8.dao.*;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import by.epam.dedik.day8.service.SortType;
import by.epam.dedik.day8.validator.BookValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomBookDaoImpl implements CustomBookDao {
    private BookValidator bookValidator = new BookValidator();
    private CustomBookAuthorDao authorDao = new CustomBookAuthorDaoImpl();

    @Override
    public boolean addBook(CustomBook book) throws DaoException {
        boolean result = false;
//        if (bookValidator.isValidBook(book)) {
        Connection connection = null;
        PreparedStatement statement = null;
        if (findBookId(book) == -1) {
            try {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();

                for (CustomBookAuthor author : book.getAuthors()) {
                    if (authorDao.findAuthor(author).isEmpty()) {
                        authorDao.addAuthor(author);
                    }
                }

                statement = connection.prepareStatement(SqlCustomBook.INSERT_BOOK);
                statement.setString(1, book.getName());
                statement.setInt(2, book.getYear());
                statement.setInt(3, book.getNumberPages());
                statement.executeUpdate();
                statement.close();

                int bookId = findBookId(book);

                if (bookId > 0) {
                    book.setId(bookId);
                    List<Integer> authorIds = new ArrayList<>();
                    for (CustomBookAuthor author : book.getAuthors()) {
                        authorIds.add(authorDao.findAuthor(author)
                                .orElse(new CustomBookAuthor(-1, "", "", "")).getId());

                    }
                    statement = connection.prepareStatement(SqlCustomBook.INSERT_LINK_AUTHOR_BOOK);
                    for (int id : authorIds) {
                        result = false;
                        statement.setInt(1, bookId);
                        statement.setInt(2, id);
                        if (statement.executeUpdate() > 0) {
                            result = true;
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DaoException("Can not add book", e);
            } catch (ConnectionException e) {
                throw new DaoException("Can not create data source", e);
            } finally {
                DaoUtil.closeConnection(connection, statement);
            }
        }
        return result;
    }

    @Override
    public boolean deleteBook(CustomBook book) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        book.setId(findBookId(book));
        if (book.getId() != -1) {
            try {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                statement = connection.prepareStatement(SqlCustomBook.DELETE_LINK_AUTHOR_BOOK);
                statement.setInt(1, book.getId());
                statement.executeUpdate();
                statement.close();

                statement = connection.prepareStatement(SqlCustomBook.DELETE_BOOK);
                statement.setString(1, book.getName());
                statement.setInt(2, book.getYear());
                statement.setInt(3, book.getNumberPages());
                result = statement.executeUpdate() > 0;
            } catch (SQLException e) {
                throw new DaoException("Can not delete book", e);
            } catch (ConnectionException e) {
                throw new DaoException("Can not create data source", e);
            } finally {
                DaoUtil.closeConnection(connection, statement);
            }
        }
        return result;
    }

    private int findBookId(CustomBook book) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            preparedStatement = connection.prepareStatement(SqlCustomBook.SELECT_ID_BOOK);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getYear());
            preparedStatement.setInt(3, book.getNumberPages());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(CustomBookColumn.ID.getColumn());
            }
        } catch (SQLException e) {
            throw new DaoException("Can not find book", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return id;
    }

    @Override
    public List<CustomBook> findByName(String name) {
        return null; // TODO: 26.07.2020 findByName
//        return Library.getInstance().getBooks().stream().
//                filter(book -> book.getName().equals(name)).
//                collect(Collectors.toList());
    }

    @Override
    public List<CustomBook> findByAuthor(String author) {
        return null; // TODO: 26.07.2020 findByAuthor
//        return Library.getInstance().getBooks().stream().
//                filter(book -> book.getAuthors().contains(author)).
//                collect(Collectors.toList());
    }

    @Override
    public List<CustomBook> findByYear(int year) {
        return null; // TODO: 26.07.2020 findByYear
//        return Library.getInstance().getBooks().stream().
//                filter(book -> book.getYear() == year).
//                collect(Collectors.toList());
    }

    @Override
    public List<CustomBook> findByNumberPages(int numberPages) {
        return null; // TODO: 26.07.2020 findByNumberPages
//        return Library.getInstance().getBooks().stream().
//                filter(book -> book.getNumberPages() == numberPages).
//                collect(Collectors.toList());
    }

    @Override
    public List<CustomBook> sortByTag(SortType sortType) {
        return null; // TODO: 26.07.2020 sortByTag
//        return Library.getInstance().getBooks().stream()
//                .sorted(sortType.getComparator())
//                .collect(Collectors.toList());
    }
}
