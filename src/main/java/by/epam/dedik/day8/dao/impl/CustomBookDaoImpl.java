package by.epam.dedik.day8.dao.impl;

import by.epam.dedik.day8.dao.CustomBookColumn;
import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.SortType;
import by.epam.dedik.day8.validator.BookValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomBookDaoImpl implements CustomBookDao {
    private static final String INSERT_CUSTOM_BOOK = "INSERT INTO custom_book (id, name, year, number_pages) VALUES (?, ?, ?, ?)";
//    private static final String INSERT_AUTHOR = "INSERT INTO author (id, name) VALUE (?, ?)";
    private static final String INSERT_CUSTOM_BOOK_AUTHOR = "INSERT INTO custom_book_author (id_custom_book, id_author) VALUES (?, ?)";
    private static final String SELECT_AUTHOR_BY_NAME = "SELECT name FROM author WHERE name = ?";
    private BookValidator bookValidator = new BookValidator();

    @Override
    public boolean addBook(CustomBook book) throws DaoException {
        if (bookValidator.isValidBook(book)) {
            Connection connection = null;
            PreparedStatement selectAuthorStatement = null;
            PreparedStatement insertAuthorStatement = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                connection.setAutoCommit(false);

                selectAuthorStatement = connection.prepareStatement(SELECT_AUTHOR_BY_NAME);
//                insertAuthorStatement = connection.prepareStatement(INSERT_AUTHOR);
                for (String author : book.getAuthors()) {
                    selectAuthorStatement.setString(1, author);
                    ResultSet resultSet = selectAuthorStatement.executeQuery();
                    if (resultSet.getString(CustomBookColumn.NAME.getColumn()) == null) { // TODO: 26.07.2020 book name replace by author name
                        insertAuthorStatement.setString(1, author);
                        if (insertAuthorStatement.executeUpdate() < 1) {
                            connection.rollback();
                            resultSet.close();
                            selectAuthorStatement.close();
                            insertAuthorStatement.close();
                            connection.setAutoCommit(true);
                            connection.close();
                            throw new DaoException("Can not insert author '" + author + "' in to table author");
                        }
                    }
                    resultSet.close();
                }
                selectAuthorStatement.close();
                insertAuthorStatement.close();

                preparedStatement = connection.prepareStatement(INSERT_CUSTOM_BOOK);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setInt(2, book.getYear());
                preparedStatement.setInt(3, book.getNumberPages());
                if (preparedStatement.executeUpdate() < 1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    connection.close();
                    throw new DaoException("Can not insert CustomBook '" + book.toString() + "' in to table custom_book");
                }
                preparedStatement.close();

                preparedStatement = connection.prepareStatement(INSERT_CUSTOM_BOOK_AUTHOR);
//                preparedStatement.setInt(1, );


                connection.commit();
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
//                connection.setAutoCommit(true);
//                selectAuthorStatement.close();
//                insertAuthorStatement.close();
//                bookStatement.close();
            }
        } else {
            throw new DaoException("Invalid book");
        }
        return false;
    }

    @Override
    public boolean removeBook(CustomBook book) {
        // TODO: 26.07.2020 removeBook
//        if (bookValidator.isValidBook(book)) {
//            Library.getInstance().removeBook(book);
//        } else {
//            throw new DaoException("Invalid book");
//        }
        return false;
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
