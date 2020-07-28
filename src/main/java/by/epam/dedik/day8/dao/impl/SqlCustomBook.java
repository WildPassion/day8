package by.epam.dedik.day8.dao.impl;

public class SqlCustomBook {
    static final String SELECT_ID_BOOK = "SELECT id FROM custom_book WHERE name = ? AND year = ? AND  number_pages = ?";
    private static final String SELECT_BOOKS = "SELECT custom_book.id, custom_book.name, custom_book.year, " +
            "custom_book.number_pages, author.name, author.surname, author.last_name " +
            "FROM custom_book " +
            "INNER JOIN custom_book_author ON custom_book_author.id_custom_book = custom_book.id " +
            "INNER JOIN author ON author.id = custom_book_author.id_author ";
    static final String SELECT_BOOKS_BY_FIELD_PART1 = SELECT_BOOKS + "WHERE ";
    static final String SELECT_BOOK_BY_FIELD_PART2 = " = ?";
    static final String SELECT_BOOKS_ORDER_BY_FIELD = SELECT_BOOKS + "ORDER BY ";
    static final String LIMIT = " LIMIT ?";

    static final String INSERT_BOOK = "INSERT INTO custom_book (name, year, number_pages) VALUES (?, ?, ?)";
    static final String INSERT_LINK_AUTHOR_BOOK = "INSERT INTO custom_book_author " +
            "(id_custom_book, id_author) VALUES (?, ?)";

    static final String DELETE_BOOK = "DELETE FROM custom_book WHERE name = ? AND year = ? AND  number_pages = ?";
    static final String DELETE_LINK_AUTHOR_BOOK = "DELETE FROM custom_book_author WHERE id_custom_book = ?";

    private SqlCustomBook() {
    }
}
