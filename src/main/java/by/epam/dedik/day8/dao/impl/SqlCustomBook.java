package by.epam.dedik.day8.dao.impl;

public class SqlCustomBook {
    static final String INSERT_BOOK = "INSERT INTO custom_book (name, year, number_pages) VALUES (?, ?, ?)";
    static final String SELECT_ID_BOOK = "SELECT id FROM custom_book WHERE name = ? AND year = ? AND  number_pages = ?";
    static final String DELETE_BOOK = "DELETE FROM custom_book WHERE name = ? AND year = ? AND  number_pages = ?";
    static final String DELETE_LINK_AUTHOR_BOOK = "DELETE FROM custom_book_author WHERE id_custom_book = ?";
    static final String INSERT_LINK_AUTHOR_BOOK = "INSERT INTO custom_book_author " +
            "(id_custom_book, id_author) VALUES (?, ?)";

    private SqlCustomBook() {
    }
}
