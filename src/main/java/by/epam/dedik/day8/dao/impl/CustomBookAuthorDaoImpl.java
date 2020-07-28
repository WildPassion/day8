package by.epam.dedik.day8.dao.impl;

import by.epam.dedik.day8.dao.CustomBookAuthorDao;
import by.epam.dedik.day8.dao.CustomBookAuthorField;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.DaoUtil;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import by.epam.dedik.day8.validator.AuthorValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomBookAuthorDaoImpl implements CustomBookAuthorDao {
    private AuthorValidator validator = new AuthorValidator();

    @Override
    public boolean addAuthor(CustomBookAuthor author) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (validator.isValid(author)) {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                statement = connection.prepareStatement(SqlCustomBookAuthor.INSERT_AUTHOR);

                statement.setString(1, author.getName());
                statement.setString(2, author.getSurname());
                statement.setString(3, author.getLastName());

                if (statement.executeUpdate() > 0) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not add author", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
        return result;
    }

    @Override
    public boolean deleteAuthor(CustomBookAuthor author) throws DaoException {
        boolean result = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (validator.isValid(author)) {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                statement = connection.prepareStatement(SqlCustomBookAuthor.DELETE_AUTHOR);

                statement.setString(1, author.getName());
                statement.setString(2, author.getSurname());
                statement.setString(3, author.getLastName());

                if (statement.executeUpdate() < 1) {
                    result = false;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not delete author", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
        return result;
    }

    @Override
    public boolean updateAuthor(CustomBookAuthor oldAuthor, CustomBookAuthor newAuthor) throws DaoException {
        boolean result = false;
        Optional<CustomBookAuthor> optionalAuthor = findAuthor(oldAuthor);
        CustomBookAuthor author = optionalAuthor.orElse(new CustomBookAuthor());
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (validator.isValid(newAuthor)) {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                statement = connection.prepareStatement(SqlCustomBookAuthor.UPDATE_AUTHOR_BY_ID);

                statement.setString(1, newAuthor.getName());
                statement.setString(2, newAuthor.getSurname());
                statement.setString(3, newAuthor.getLastName());
                statement.setInt(4, author.getId());

                result = statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DaoException("Can not update author", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }

        return result;
    }

    @Override
    public Optional<CustomBookAuthor> findAuthor(CustomBookAuthor author) throws DaoException {
        Optional<CustomBookAuthor> result = Optional.empty();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            if (validator.isValid(author)) {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                statement = connection.prepareStatement(SqlCustomBookAuthor.SELECT_AUTHOR);

                statement.setString(1, author.getName());
                statement.setString(2, author.getSurname());
                statement.setString(3, author.getLastName());
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    CustomBookAuthor findAuthor =
                            new CustomBookAuthor(resultSet.getInt(CustomBookAuthorField.ID.getColumn()),
                                    resultSet.getString(CustomBookAuthorField.NAME.getColumn()),
                                    resultSet.getString(CustomBookAuthorField.SURNAME.getColumn()),
                                    resultSet.getString(CustomBookAuthorField.LAST_NAME.getColumn()));

                    result = Optional.of(findAuthor);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not find author", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, statement, resultSet);
        }
        return result;
    }
}
