package by.epam.dedik.day8.validator;

import by.epam.dedik.day8.entity.CustomBookAuthor;

public class AuthorValidator {
    private static final String REGEX = "^\\p{Alpha}\\p{Lower}{1,10}$";

    public boolean isValid(CustomBookAuthor author) {
        return author != null &&
                author.getName() != null && author.getName().matches(REGEX) &&
                author.getSurname() != null && author.getSurname().matches(REGEX) &&
                author.getLastName() != null && author.getLastName().matches(REGEX);
    }
}
