package by.epam.dedik.day8.dao;

import by.epam.dedik.day8.dao.impl.SqlCustomBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUtil {
    public static void closeConnection(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // TODO: 26.07.2020 log
        }
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            closeConnection(connection, statement);
        } catch (SQLException e) {
            // TODO: 26.07.2020 log
        }
    }
}
