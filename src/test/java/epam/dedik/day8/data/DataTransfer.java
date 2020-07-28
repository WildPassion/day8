package epam.dedik.day8.data;

import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.entity.CustomBookAuthor;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataTransfer {
    @DataProvider
    public Object[][] getBooks() {
        return new Object[][]{{Arrays.asList(
                new CustomBook("Book1", Arrays.asList(
                        new CustomBookAuthor("Author21", "Surname", "LastName"),
                        new CustomBookAuthor("Author22", "Surname", "LastName")),
                        2003, 400),
                new CustomBook("Book2", Arrays.asList(
                        new CustomBookAuthor("Author31", "Surname", "LastName"),
                        new CustomBookAuthor("Author32", "Surname", "LastName")),
                        2004, 500),
                new CustomBook("Book3", Arrays.asList(
                        new CustomBookAuthor("Author41", "Surname", "LastName"),
                        new CustomBookAuthor("Author42", "Surname", "LastName")),
                        2001, 200),
                new CustomBook("Book4", Arrays.asList(
                        new CustomBookAuthor("Author51", "Surname", "LastName"),
                        new CustomBookAuthor("Author52", "Surname", "LastName")),
                        2005, 100),
                new CustomBook("Book5", Arrays.asList(
                        new CustomBookAuthor("Author11", "Surname", "LastName"),
                        new CustomBookAuthor("Author12", "Surname", "LastName")),
                        2002, 300))
        }};
    }

    @DataProvider
    public Object[][] getValidBook() {
        return new Object[][]{{
                new CustomBook("Book1", Arrays.asList(
                        new CustomBookAuthor("Author1", "Surname", "LastName"),
                        new CustomBookAuthor("Author2", "Surname", "LastName")),
                        1999, 700)
        }};
    }

    @DataProvider
    public Object[][] getInvalidBook() {
        return new Object[][]{{
                new CustomBook("", Arrays.asList(
                        new CustomBookAuthor("Author1", "Surname", "LastName"),
                        new CustomBookAuthor("Author2", "Surname", "LastName")),
                        -1999, 700)
        }};
    }

    @DataProvider
    public Object[][] getValidAuthor(){
        return new Object[][]{{
                new CustomBookAuthor("Name", "Surname", "Lastname")
        }};
    }
}
