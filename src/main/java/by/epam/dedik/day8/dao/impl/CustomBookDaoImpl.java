package by.epam.dedik.day8.dao.impl;

import by.epam.dedik.day8.dao.*;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import by.epam.dedik.day8.validator.BookValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomBookDaoImpl implements CustomBookDao {
    private BookValidator bookValidator = new BookValidator();
    private CustomBookAuthorDao authorDao = new CustomBookAuthorDaoImpl();

    private int findBookId(CustomBook book) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            statement = connection.prepareStatement(SqlCustomBook.SELECT_ID_BOOK);
            statement.setString(1, book.getName());
            statement.setInt(2, book.getYear());
            statement.setInt(3, book.getNumberPages());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(CustomBookField.ID.getColumn());
            }
        } catch (SQLException e) {
            throw new DaoException("Can not find book", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, statement, resultSet);
        }
        return id;
    }

    private List<Optional<CustomBook>> extractBookList(ResultSet resultSet) throws SQLException {
        Map<Integer, Optional<CustomBook>> map = new HashMap<>();
        while (resultSet.next()) {
            CustomBook book = new CustomBook();
            int bookId = resultSet.getInt(CustomBookField.ID.getColumn());
            book.setName(resultSet.getString(CustomBookField.NAME.getColumn()));
            book.setYear(resultSet.getInt(CustomBookField.YEAR.getColumn()));
            book.setNumberPages(resultSet.getInt(CustomBookField.NUMBER_PAGES.getColumn()));

            List<CustomBookAuthor> authors = new ArrayList<>();
            do {
                System.out.println(resultSet.getInt(CustomBookField.ID.getColumn()));
                if (bookId == resultSet.getInt(CustomBookField.ID.getColumn())) {
                    CustomBookAuthor author = new CustomBookAuthor();
                    author.setName(resultSet.getString(CustomBookAuthorField.NAME.getColumn()));
                    author.setSurname(resultSet.getString(CustomBookAuthorField.SURNAME.getColumn()));
                    author.setLastName(resultSet.getString(CustomBookAuthorField.LAST_NAME.getColumn()));
                    authors.add(author);
                } else {
                    break;
                }
            } while (resultSet.next());
            book.setAuthors(authors);
            map.put(book.getId(), Optional.of(book));
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean addBook(CustomBook book) throws DaoException {
        // TODO: 28.07.2020 validation
        boolean result = false;
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
        // TODO: 28.07.2020 validation
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

    @Override
    public List<Optional<CustomBook>> findByField(CustomBookField field, String value) throws DaoException {
        // TODO: 28.07.2020 validation
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Optional<CustomBook>> books = new ArrayList<>();
        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            statement = connection.prepareStatement(SqlCustomBook.SELECT_BOOKS_BY_FIELD_PART1 + field.getColumn() +
                    SqlCustomBook.SELECT_BOOK_BY_FIELD_PART2);
            statement.setString(1, value);
            resultSet = statement.executeQuery();

            extractBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can not find book by name", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, statement, resultSet);
        }
        return books;
    }

    @Override
    public List<Optional<CustomBook>> sortByField(CustomBookField field, int limit) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Optional<CustomBook>> books = new ArrayList<>();

        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            statement = connection.prepareStatement(SqlCustomBook.SELECT_BOOKS_ORDER_BY_FIELD +
                    field.getColumn() + SqlCustomBook.LIMIT);
            statement.setInt(1, limit);
            resultSet = statement.executeQuery();

            extractBookList(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can not extract books from database", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, statement, resultSet);
        }
        return books;
    }
}
