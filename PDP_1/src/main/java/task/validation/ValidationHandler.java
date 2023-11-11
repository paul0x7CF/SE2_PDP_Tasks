package task.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import task.exceptions.InvalidDueDateException;
import task.exceptions.InvalidFieldLengthException;
import task.exceptions.InvalidTextInputValidation;

import java.time.LocalDate;

public class ValidationHandler {

    private static final Logger logger = LogManager.getLogger(ValidationHandler.class);

    public static String validateStringInput(String input, String fieldName) throws InvalidTextInputValidation {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidTextInputValidation(fieldName + " should not be null or empty");
        }
        logger.debug("Input is present");
        return input.trim();
    }

    public static boolean validateInputLength(String input, int maxLength, String fieldName) throws InvalidFieldLengthException {
        assert maxLength > 0 : "maxLength should be greater than 0";
        assert input != null && input.length() > 0 : "The input to validate length should not be null or empty";

        if(input.length() > maxLength) {
            throw new InvalidFieldLengthException(fieldName + " should not have more characters than " + maxLength);
        }
        logger.debug("Input length is valid");
        return true;
    }

    public static boolean validateDueDate(LocalDate dueDate) throws InvalidDueDateException {
        if (dueDate == null) {
            throw new InvalidDueDateException("Due date should not be null");
        }

        if(dueDate.isBefore(LocalDate.now())) {
            throw new InvalidDueDateException("Due date can not be in the past");
        }
        logger.debug("Due date is valid");
        return true;
    }
}
