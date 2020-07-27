package by.epam.dedik.day8.dao.impl;

public class SqlCustomBookAuthor {
    static final String INSERT_AUTHOR = "INSERT INTO author (name, surname, last_name) VALUES (?, ?, ?)";
    static final String DELETE_AUTHOR = "DELETE FROM author WHERE name = ? AND surname = ? AND  last_name = ?";
    static final String UPDATE_AUTHOR_BY_ID = "UPDATE author SET name = ?, surname = ?,  last_name = ? WHERE id = ?";
    static final String FIND_AUTHOR = "SELECT id, name, surname, last_name FROM author " +
            "WHERE name = ? AND surname = ? AND last_name = ?";

    private SqlCustomBookAuthor() {
    }
}
