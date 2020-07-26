package by.epam.dedik.day8.dao.impl;

import by.epam.dedik.day8.dao.CustomBookAuthorColumn;
import by.epam.dedik.day8.dao.CustomBookAuthorDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.DaoUtil;
import by.epam.dedik.day8.dao.connection.ConnectionException;
import by.epam.dedik.day8.dao.connection.DataSourceFactory;
import by.epam.dedik.day8.entity.CustomBookAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomBookAuthorDaoImpl implements CustomBookAuthorDao {
    private static final String INSERT_AUTHOR = "INSERT INTO author (name, surname, last_name) VALUES (?, ?, ?)";
    private static final String DELETE_AUTHOR = "DELETE FROM author WHERE name = ? AND surname = ? AND  last_name = ?";
    private static final String UPDATE_AUTHOR_BY_ID = "UPDATE author SET name = ?, surname = ?,  last_name = ? WHERE id = ?";
    private static final String FIND_AUTHOR = "SELECT id, name, surname, last_name FROM author WHERE name = ? AND surname = ? AND last_name = ?";

    @Override
    public boolean addAuthor(CustomBookAuthor author) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_AUTHOR);

            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getLastName());

            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Can not add author", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    @Override
    public boolean deleteAuthor(CustomBookAuthor author) throws DaoException {
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
            throw new DaoException("Can not delete author", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    @Override
    public boolean updateAuthor(CustomBookAuthor oldAuthor, CustomBookAuthor newAuthor) throws DaoException {
        boolean result = true;
        Optional<CustomBookAuthor> optionalAuthor = findAuthor(oldAuthor);
        if (optionalAuthor.isEmpty()) {
            result = false;
        } else {
            CustomBookAuthor author = optionalAuthor.get();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = DataSourceFactory.createMysqlDataSource().getConnection();
                preparedStatement = connection.prepareStatement(UPDATE_AUTHOR_BY_ID);

                preparedStatement.setString(1, newAuthor.getName());
                preparedStatement.setString(2, newAuthor.getSurname());
                preparedStatement.setString(3, newAuthor.getLastName());
                preparedStatement.setInt(4, author.getId());

                if (preparedStatement.executeUpdate() < 1) {
                    result = false;
                }
            } catch (SQLException e) {
                throw new DaoException("Can not update author", e);
            } catch (ConnectionException e) {
                throw new DaoException("Can not create data source", e);
            } finally {
                DaoUtil.closeConnection(connection, preparedStatement);
            }
        }
        return result;
    }

    @Override
    public Optional<CustomBookAuthor> findAuthor(CustomBookAuthor author) throws DaoException {
        Optional<CustomBookAuthor> result = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceFactory.createMysqlDataSource().getConnection();
            preparedStatement = connection.prepareStatement(FIND_AUTHOR);

            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getLastName());
            resultSet = preparedStatement.executeQuery();

            System.out.println(CustomBookAuthorColumn.LAST_NAME.getColumn());
            if (resultSet.next()) {
                CustomBookAuthor findAuthor =
                        new CustomBookAuthor(resultSet.getInt(CustomBookAuthorColumn.ID.getColumn()),
                                resultSet.getString(CustomBookAuthorColumn.NAME.getColumn()),
                                resultSet.getString(CustomBookAuthorColumn.SURNAME.getColumn()),
                                resultSet.getString(CustomBookAuthorColumn.LAST_NAME.getColumn()));

                result = Optional.of(findAuthor);
            }
        } catch (SQLException e) {
            throw new DaoException("Can not find author", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can not create data source", e);
        } finally {
            DaoUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return result;
    }
}
