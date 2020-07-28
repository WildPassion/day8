package epam.dedik.day8.dao;

import by.epam.dedik.day8.dao.CustomBookAuthorDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.DaoUtil;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.dao.impl.CustomBookAuthorDaoImpl;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomBookAuthorDaoImplTest {
    private static final String DELETE_AUTHOR = "DELETE FROM author WHERE name = ? AND surname = ? AND  last_name = ?";

    private CustomBookAuthorDao dao = new CustomBookAuthorDaoImpl();
    private Logger logger = Logger.getLogger(CustomBookAuthorDaoImplTest.class);

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
            logger.debug(e);
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    @Test
    public void addAuthor_author_true() throws DaoException, ConnectionException {
        CustomBookAuthor author = new CustomBookAuthor("name", "surname", "lastName");
        boolean actual = dao.addAuthor(author);
        if (!clean(author)) {
            logger.debug("Data base clean did not happen");
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void deleteAuthor_author_true() throws DaoException, ConnectionException {
        boolean actual;
        CustomBookAuthor author = new CustomBookAuthor("name", "surname", "lastName");
        if (!dao.addAuthor(author)) {
            logger.debug(dao.getClass() + ".addAuthor() return false but expected true ");
        }
        actual = dao.deleteAuthor(author);
        if (!actual) {
            clean(author);
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void findAuthor_author_author() throws ConnectionException, DaoException {
        CustomBookAuthor expected = new CustomBookAuthor("name", "surname", "lastName");
        if (!dao.addAuthor(expected)) {
            logger.debug(dao.getClass() + ".addAuthor() return false but expected true ");
        }
        CustomBookAuthor actual = dao.findAuthor(expected).orElse(new CustomBookAuthor());
        if (!clean(actual)) {
            logger.debug("Data base clean did not happen");
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void updateAuthor_author_true() throws ConnectionException, DaoException {
        CustomBookAuthor source = new CustomBookAuthor("name", "surname", "lastName");
        CustomBookAuthor newAuthor = new CustomBookAuthor("name1", "surname", "lastName");
        if (!dao.addAuthor(source)) {
            logger.debug(dao.getClass() + ".addAuthor() return false but expected true ");
        }
        boolean actual = dao.updateAuthor(source, newAuthor);
        if (!clean(newAuthor)) {
            logger.debug("Data base clean did not happen");
        }
        Assert.assertTrue(actual);
    }
}
