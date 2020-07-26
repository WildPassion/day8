package epam.dedik.day8.controller;

import by.epam.dedik.day8.controller.BookController;
import by.epam.dedik.day8.controller.BookRequest;
import by.epam.dedik.day8.controller.BookResponse;
import by.epam.dedik.day8.controller.command.CommandName;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.SortType;
import epam.dedik.day8.data.DataTransfer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookControllerTest {
    private BookController controller;

    @BeforeClass
    private void setController() {
        controller = new BookController();
    }

//    @AfterMethod
//    private void removeFromLibrary() {
//        int i = Library.getInstance().getBooks().size();
//        while (Library.getInstance().getBooks().size() > 0) {
//            Library.getInstance().removeBook(--i);
//        }
//    }
//
//    private void addAll(List<CustomBook> books) {
//        int i = 0;
//        while (i < books.size()) {
//            Library.getInstance().addBook(books.get(i++));
//        }
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void doSomething_addBookCommand_successMessage(CustomBook book) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        request.setBook(book);
//        request.setCommand(CommandName.ADD_BOOK);
//        controller.doSomething(request, response);
//        String actual = response.getMessage();
//        String expected = "Adding was successful";
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getInvalidBook", dataProviderClass = DataTransfer.class)
//    public void doSomething_addBookCommand_exceptionMessage(CustomBook book) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        request.setBook(book);
//        request.setCommand(CommandName.ADD_BOOK);
//        controller.doSomething(request, response);
//        String actual = response.getMessage();
//        String expected = "Invalid book";
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_existAuthor_listByAuthor(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setAuthor("Author21");
//        request.setCommand(CommandName.FIND_BY_AUTHOR);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        expected.add(new CustomBook("Book1", Arrays.asList("Author21", "Author22"),
//                2003, 400));
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_nonExistentAuthor_emptyList(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setAuthor("");
//        request.setCommand(CommandName.FIND_BY_AUTHOR);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_existName_listByName(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setName("Book1");
//        request.setCommand(CommandName.FIND_BY_NAME);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        expected.add(new CustomBook("Book1", Arrays.asList("Author21", "Author22"),
//                2003, 400));
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_nonExistentName_emptyList(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setName("");
//        request.setCommand(CommandName.FIND_BY_NAME);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_existNumberPages_listByName(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setNumberPages(400);
//        request.setCommand(CommandName.FIND_BY_NUMBER_PAGES);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        expected.add(new CustomBook("Book1", Arrays.asList("Author21", "Author22"),
//                2003, 400));
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_nonExistentNumberPages_emptyList(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setNumberPages(0);
//        request.setCommand(CommandName.FIND_BY_NUMBER_PAGES);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_existYear_listByName(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setYear(2003);
//        request.setCommand(CommandName.FIND_BY_YEAR);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        expected.add(new CustomBook("Book1", Arrays.asList("Author21", "Author22"),
//                2003, 400));
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_nonExistentYear_emptyList(List<CustomBook> books) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        addAll(books);
//        request.setYear(0);
//        request.setCommand(CommandName.FIND_BY_YEAR);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = new ArrayList<>();
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void doSomething_removeCommand_successMessage(CustomBook book) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        Library.getInstance().addBook(book);
//        request.setBook(book);
//        request.setCommand(CommandName.REMOVE_BOOK);
//        controller.doSomething(request, response);
//        String actual = response.getMessage();
//        String expected = "Removing was successful";
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getInvalidBook", dataProviderClass = DataTransfer.class)
//    public void doSomething_removeCommand_exceptionMessage(CustomBook book) {
//        BookRequest request = new BookRequest();
//        BookResponse response = new BookResponse();
//        request.setBook(book);
//        request.setCommand(CommandName.REMOVE_BOOK);
//        controller.doSomething(request, response);
//        String actual = response.getMessage();
//        String expected = "Invalid book";
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_booksAndName_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        BookResponse response = new BookResponse();
//        BookRequest request = new BookRequest();
//        request.setSortType(SortType.NAME);
//        request.setCommand(CommandName.SORT_BY_TAG);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = Arrays.asList(
//                new CustomBook("Book1", Arrays.asList("Author21", "Author22"), 2003, 400),
//                new CustomBook("Book2", Arrays.asList("Author31", "Author32"), 2004, 500),
//                new CustomBook("Book3", Arrays.asList("Author41", "Author42"), 2001, 200),
//                new CustomBook("Book4", Arrays.asList("Author51", "Author52"), 2005, 100),
//                new CustomBook("Book5", Arrays.asList("Author11", "Author12"), 2002, 300));
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_booksAndAuthor_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        BookResponse response = new BookResponse();
//        BookRequest request = new BookRequest();
//        request.setSortType(SortType.AUTHOR);
//        request.setCommand(CommandName.SORT_BY_TAG);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = Arrays.asList(
//                new CustomBook("Book5", Arrays.asList("Author11", "Author12"), 2002, 300),
//                new CustomBook("Book1", Arrays.asList("Author21", "Author22"), 2003, 400),
//                new CustomBook("Book2", Arrays.asList("Author31", "Author32"), 2004, 500),
//                new CustomBook("Book3", Arrays.asList("Author41", "Author42"), 2001, 200),
//                new CustomBook("Book4", Arrays.asList("Author51", "Author52"), 2005, 100));
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_booksAndYear_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        BookResponse response = new BookResponse();
//        BookRequest request = new BookRequest();
//        request.setSortType(SortType.YEAR);
//        request.setCommand(CommandName.SORT_BY_TAG);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = Arrays.asList(
//                new CustomBook("Book3", Arrays.asList("Author41", "Author42"), 2001, 200),
//                new CustomBook("Book5", Arrays.asList("Author11", "Author12"), 2002, 300),
//                new CustomBook("Book1", Arrays.asList("Author21", "Author22"), 2003, 400),
//                new CustomBook("Book2", Arrays.asList("Author31", "Author32"), 2004, 500),
//                new CustomBook("Book4", Arrays.asList("Author51", "Author52"), 2005, 100));
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void doSomething_booksAndNumberPages_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        BookResponse response = new BookResponse();
//        BookRequest request = new BookRequest();
//        request.setSortType(SortType.NUMBER_PAGES);
//        request.setCommand(CommandName.SORT_BY_TAG);
//        controller.doSomething(request, response);
//        List<CustomBook> actual = response.getBooks();
//        List<CustomBook> expected = Arrays.asList(
//                new CustomBook("Book4", Arrays.asList("Author51", "Author52"), 2005, 100),
//                new CustomBook("Book3", Arrays.asList("Author41", "Author42"), 2001, 200),
//                new CustomBook("Book5", Arrays.asList("Author11", "Author12"), 2002, 300),
//                new CustomBook("Book1", Arrays.asList("Author21", "Author22"), 2003, 400),
//                new CustomBook("Book2", Arrays.asList("Author31", "Author32"), 2004, 500));
//        Assert.assertEquals(actual, expected);
//    }
}
