package unitTestValidation;

import org.junit.jupiter.api.Test;
import task.validation.ValidateTextLength;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateTextLengthTest {

    @Test
    void constructor_InvalidMaxLength_ThrowsAssertionError() {

        int invalidMaxLength = 10;

        assertThrows(AssertionError.class, () -> new ValidateTextLength(invalidMaxLength));
    }
}
