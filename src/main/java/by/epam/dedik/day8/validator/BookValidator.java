package by.epam.dedik.day8.validator;

import by.epam.dedik.day8.entity.CustomBook;

import java.time.LocalDate;

public class BookValidator {
    private static final int FIRST_BOOK = -600;

    // TODO: 27.07.2020 rewrite for author
    public boolean isValidBook(CustomBook book) {
        return book.getName() != null && book.getAuthors() != null &&
                book.getYear() > FIRST_BOOK && book.getYear() < LocalDate.now().getYear() &&
                book.getNumberPages() != 0;
    }
}
