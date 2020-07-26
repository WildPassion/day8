package epam.dedik.day8.dao;

import by.epam.dedik.day8.dao.CustomBookAuthorDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.DaoUtil;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.dao.impl.CustomBookAuthorDaoImpl;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomBookAuthorDaoImplTest {
    private static final String INSERT_AUTHOR = "INSERT INTO author (name, surname, last_name) VALUES (?, ?, ?)";
    private static final String DELETE_AUTHOR = "DELETE FROM author WHERE name = ? AND surname = ? AND  last_name = ?";
    private CustomBookAuthorDao dao = new CustomBookAuthorDaoImpl();

    //    @BeforeClass
//    public void setDao(CustomBookAuthorDao dao) {
//        this.dao = new CustomBookAuthorDaoImpl();
//    }
    private boolean clean(CustomBookAuthor author) throws ConnectionException {
        boolean result = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_AUTHOR);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getLastName());
            if (preparedStatement.executeUpdate() < 1) {
                result = false;
            }
        } catch (SQLException e) {
            // TODO: 26.07.2020 log
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    private boolean add(CustomBookAuthor author) throws ConnectionException {
        boolean result = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_AUTHOR);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getLastName());
            if (preparedStatement.executeUpdate() < 1) {
                result = false;
            }
        } catch (SQLException e) {
            // TODO: 26.07.2020 log
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    @Test
    public void addAuthor_author_true() throws DaoException, ConnectionException {
        CustomBookAuthor author = new CustomBookAuthor("name", "surname", "lastName");
        boolean expected = dao.addAuthor(author);
        if (!clean(author)) {
            // TODO: 26.07.2020 log
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void deleteAuthor_author_true() throws DaoException, ConnectionException {
        boolean expected;
        CustomBookAuthor author = new CustomBookAuthor("name", "surname", "lastName");
        if (!add(author)) {
            // TODO: 26.07.2020 log
        }
        expected = dao.deleteAuthor(author);
        if (!expected) {
            clean(author);
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void findAuthor_author_author() throws ConnectionException, DaoException {
        CustomBookAuthor expected = new CustomBookAuthor("name", "surname", "lastName");
        if (!add(expected)) {
            // TODO: 26.07.2020 log
        }
        CustomBookAuthor actual = dao.findAuthor(expected).orElse(new CustomBookAuthor());
        if (!clean(actual)) {
            // TODO: 27.07.2020 log
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void updateAuthor_author_true() throws ConnectionException, DaoException {
        CustomBookAuthor actual = new CustomBookAuthor("name", "surname", "lastName");
        CustomBookAuthor expected = new CustomBookAuthor("name1", "surname", "lastName");
        if (!add(actual)) {
            // TODO: 26.07.2020 log
        }
        boolean result = dao.updateAuthor(actual, expected);
        if (!clean(expected)) {
            // TODO: 27.07.2020 log
        }
        Assert.assertTrue(result);
    }
}
