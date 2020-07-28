package by.epam.dedik.day8.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUtil {
    private static Logger logger = Logger.getLogger(DaoUtil.class);

    private DaoUtil() {
    }

    public static void closeConnection(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            closeConnection(connection, statement);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
