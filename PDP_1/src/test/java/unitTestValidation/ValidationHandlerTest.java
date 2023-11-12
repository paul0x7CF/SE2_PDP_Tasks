package unitTestValidation;

import org.junit.jupiter.api.Test;
import task.exceptions.InvalidTextInputValidation;
import task.validation.ValidationHandler;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationHandlerTest {

    @Test
    void validateTaskStringInput_ValidInput_ReturnsTrimmedInput() {
        final int MAX_TEXT_LENGTH = 50;
        String input = "   Valid Input   ";
        String fieldName = "Test Field";

        assertDoesNotThrow(() -> {
            String result = ValidationHandler.validateTaskStringInput(input, fieldName, MAX_TEXT_LENGTH);
            assertEquals("Valid Input", result);
        });
    }

    @Test
    void validateTaskStringInput_NullInput_ThrowsInvalidTextInputValidation() {
        final int MAX_TEXT_LENGTH = 50;
        String fieldName = "Test Field";

        assertThrows(InvalidTextInputValidation.class, () -> ValidationHandler.validateTaskStringInput(null, fieldName, MAX_TEXT_LENGTH));
    }

    @Test
    void validateTaskStringInput_ExceedsMaxLength_ThrowsInvalidTextInputValidation() {
        final int MAX_TEXT_LENGTH = 15;
        String input = "Too Long Input Was Entered";
        String fieldName = "Test Field";

        assertThrows(InvalidTextInputValidation.class, () -> ValidationHandler.validateTaskStringInput(input, fieldName, MAX_TEXT_LENGTH));
    }
}
