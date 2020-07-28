package epam.dedik.day8.validator;

import by.epam.dedik.day8.entity.CustomBookAuthor;
import by.epam.dedik.day8.validator.AuthorValidator;
import epam.dedik.day8.data.DataTransfer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthorValidatorTest {
    private AuthorValidator validator;

    @BeforeClass
    private void setValidator() {
        validator = new AuthorValidator();
    }

    @Test(dataProvider = "getValidAuthor", dataProviderClass = DataTransfer.class)
    public void isValidBook_validBook_true(CustomBookAuthor author) {
        Assert.assertTrue(validator.isValid(author));
    }

    @Test(dataProvider = "getInvalidAuthor", dataProviderClass = DataTransfer.class)
    public void isValidBook_invalidBook_false(CustomBookAuthor author) {
        Assert.assertFalse(validator.isValid(author));
    }
}
