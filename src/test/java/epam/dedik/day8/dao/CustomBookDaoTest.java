package epam.dedik.day8.dao;

import by.epam.dedik.day8.dao.CustomBookDao;
import by.epam.dedik.day8.dao.DaoException;
import by.epam.dedik.day8.dao.impl.CustomBookDaoImpl;
import by.epam.dedik.day8.entity.CustomBook;
import by.epam.dedik.day8.service.SortType;
import epam.dedik.day8.data.DataTransfer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CustomBookDaoTest {
    private CustomBookDao customBookDao;

    @BeforeClass
    private void setBookListDao() {
        customBookDao = new CustomBookDaoImpl();
    }

//    @AfterMethod
//    private void removeFromLibrary() {
//        int i = Library.getInstance().getBooks().size();
//        while (Library.getInstance().getBooks().size() > 0) {
//            Library.getInstance().removeBook(--i);
//        }
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void addBook_validBook_true(CustomBook book) throws DaoException {
//        book.setId(0);
//        customBookDao.addBook(book);
//        Assert.assertTrue(Library.getInstance().getBooks().contains(book));
//    }
//
//    @Test(dataProvider = "getInvalidBook", dataProviderClass = DataTransfer.class,
//            expectedExceptions = DaoException.class, expectedExceptionsMessageRegExp = "Invalid book")
//    public void addBook_invalidBook_false(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void removeBook_validBook_true(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        customBookDao.removeBook(book);
//        Assert.assertFalse(Library.getInstance().getBooks().contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class,
//            expectedExceptions = DaoException.class, expectedExceptionsMessageRegExp = "Invalid book")
//    public void removeBook_invalidBook_false(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        book.setYear(-2000);
//        customBookDao.removeBook(book);
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByName_existingBook_true(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByName(book.getName());
//        Assert.assertTrue(found.contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByName_nonexistentBook_false(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByName("qwe");
//        Assert.assertFalse(found.contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByAuthor_existingBook_true(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByAuthor(book.getAuthors().get(0));
//        Assert.assertTrue(found.contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByAuthor_nonexistentBook_false(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByAuthor("qwe");
//        Assert.assertFalse(found.contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByYear_existingBook_true(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByYear(book.getYear());
//        Assert.assertTrue(found.contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByYear_nonexistentBook_false(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByYear(0);
//        Assert.assertFalse(found.contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByNumberPages_existingBook_true(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByNumberPages(book.getNumberPages());
//        Assert.assertTrue(found.contains(book));
//    }
//
//    @Test(dataProvider = "getValidBook", dataProviderClass = DataTransfer.class)
//    public void findByNumberPages_nonexistentBook_false(CustomBook book) throws DaoException {
//        customBookDao.addBook(book);
//        List<CustomBook> found = customBookDao.findByNumberPages(0);
//        Assert.assertFalse(found.contains(book));
//    }
//
//    @Test(dataProvider = "getBooks", dataProviderClass = DataTransfer.class)
//    public void sortByName_books_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        List<CustomBook> actual = customBookDao.sortByTag(SortType.NAME);
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
//    public void sortByAuthor_books_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        List<CustomBook> actual = customBookDao.sortByTag(SortType.AUTHOR);
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
//    public void sortByYear_books_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        List<CustomBook> actual = customBookDao.sortByTag(SortType.YEAR);
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
//    public void sortByNumberPages_books_sortedBooks(List<CustomBook> books) {
//        books.forEach(book -> Library.getInstance().addBook(book));
//        List<CustomBook> actual = customBookDao.sortByTag(SortType.NUMBER_PAGES);
//        List<CustomBook> expected = Arrays.asList(
//                new CustomBook("Book4", Arrays.asList("Author51", "Author52"), 2005, 100),
//                new CustomBook("Book3", Arrays.asList("Author41", "Author42"), 2001, 200),
//                new CustomBook("Book5", Arrays.asList("Author11", "Author12"), 2002, 300),
//                new CustomBook("Book1", Arrays.asList("Author21", "Author22"), 2003, 400),
//                new CustomBook("Book2", Arrays.asList("Author31", "Author32"), 2004, 500));
//        Assert.assertEquals(actual, expected);
//    }
}
