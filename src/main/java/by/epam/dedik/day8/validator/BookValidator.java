package by.epam.dedik.day8.validator;

import by.epam.dedik.day8.entity.CustomBook;

import java.time.LocalDate;

public class BookValidator {
    private static final int FIRST_BOOK = -600;
    private static final int MAX_NAME_LENGTH = 100;

    public boolean isValidBook(CustomBook book) {
        return book != null &&
                book.getName() != null && book.getName().length() < MAX_NAME_LENGTH &&
                book.getYear() > FIRST_BOOK && book.getYear() < LocalDate.now().getYear() &&
                book.getNumberPages() != 0;
    }
}
