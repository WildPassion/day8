package epam.dedik.day8.dao;

import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.CustomBookField;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.DaoUtil;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import epam.dedik.day8.data.DataTransfer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomBookDaoTest {
    static final String DELETE_LINK_AUTHOR_BOOK = "DELETE FROM custom_book_author WHERE id_custom_book = ?";
    private static final String DELETE_BOOK = "DELETE FROM custom_book " +
            "WHERE name = ? AND year = ? AND  number_pages = ?";
    private static final String SELECT_ID_BOOK = "SELECT id FROM custom_book " +
            "WHERE name = ? AND year = ? AND  number_pages = ?";

    private CustomBookDao dao;

    @BeforeClass
    private void setBookListDao() {
        dao = new CustomBookDaoImpl();
    }

//    @AfterMethod
//    private void removeFromLibrary() {
//        int i = Library.getInstance().getBooks().size();
//        while (Library.getInstance().getBooks().size() > 0) {
//            Library.getInstance().removeBook(--i);
//        }
//    }
//

    private boolean clean(CustomBook book) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        book.setId(findId(book));
        if (book.getId() != -1) {
            try {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                statement = connection.prepareStatement(DELETE_LINK_AUTHOR_BOOK);
                statement.setInt(1, book.getId());
                statement.executeUpdate();
                statement.close();

                statement = connection.prepareStatement(DELETE_BOOK);
                statement.setString(1, book.getName());
                statement.setInt(2, book.getYear());
                statement.setInt(3, book.getNumberPages());
                result = statement.executeUpdate() > 0;
            } catch (SQLException | ConnectionException e) {
                e.printStackTrace();
                // TODO: 27.07.2020 log
            } finally {
                DaoUtil.closeConnection(connection, statement);
            }
        }
        return result;
    }

    private int findId(CustomBook book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID_BOOK);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getYear());
            preparedStatement.setInt(3, book.getNumberPages());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(CustomBookField.ID.getColumn());
            }
        } catch (SQLException | ConnectionException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return id;
    }

    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
    public void addBook_validBook_true(CustomBook book) throws DaoException {
        boolean actual = dao.addBook(book);
        clean(book);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
    public void deleteBook_validBook_true(CustomBook book) throws DaoException {
        dao.addBook(book);
        boolean actual = dao.deleteBook(book);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
    public void findByField_nameAndExistingBook_booksByName(List<CustomBook> books) throws DaoException {
        books.forEach(book -> {
            try {
                dao.addBook(book);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });

        List<Optional<CustomBook>> expected = new ArrayList<>();
        expected.add(Optional.of(new CustomBook("Book2", Arrays.asList(
                new CustomBookAuthor("Author31", "Surname", "LastName"),
                new CustomBookAuthor("Author32", "Surname", "LastName")),
                2004, 500)));
        List<Optional<CustomBook>> actual = dao.findByField(CustomBookField.NAME, String.valueOf(books.get(1).getName()));

        books.forEach(book -> {
            try {
                clean(book);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
    public void sortByField_fieldAndLimit_sortBooks(List<CustomBook> books) throws DaoException {

    }
}
