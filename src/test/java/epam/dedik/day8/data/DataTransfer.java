package epam.dedik.day8.data;

import by.epam.dedik.day8.entity.CustomBook;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataTransfer {
    @DataProvider
    public Object[][] getBooks() {
        return new Object[][]{{Arrays.asList(
                new CustomBook("Book1", Arrays.asList("Author21", "Author22"), 2003, 400),
                new CustomBook("Book2", Arrays.asList("Author31", "Author32"), 2004, 500),
                new CustomBook("Book3", Arrays.asList("Author41", "Author42"), 2001, 200),
                new CustomBook("Book4", Arrays.asList("Author51", "Author52"), 2005, 100),
                new CustomBook("Book5", Arrays.asList("Author11", "Author12"), 2002, 300))
        }};
    }

    @DataProvider
    public Object[][] getValidBook() {
        return new Object[][]{{
                new CustomBook("Book1", Arrays.asList("Author1", "Author2"), 1999, 700),
        }};
    }

    @DataProvider
    public Object[][] getInvalidBook() {
        return new Object[][]{{
                new CustomBook("", Arrays.asList("Author1", "Author2"), -1999, 700),
        }};
    }

    // TODO: 26.07.2020 getAuthor
}
