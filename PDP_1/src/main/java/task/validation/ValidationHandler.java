package task.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task.exceptions.InvalidDueDateException;
import task.exceptions.InvalidTextInputValidation;
import task.exceptions.InvalidUserInputException;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ValidationHandler {

    private static final Logger logger = LogManager.getLogger(ValidationHandler.class);


    public static String validateTaskStringInput(String input, String fieldName, final int MAX_TEXT_LENGTH) throws InvalidTextInputValidation {
        input = input.trim();
        List<ITaskStringInputValidation> stringInputValidations = Stream.of(
                new ValidateTextInput(),
                new ValidateTextLength(MAX_TEXT_LENGTH)
        ).toList();

        for (ITaskStringInputValidation eachValidation : stringInputValidations) {
            eachValidation.validateTaskStringInput(input, fieldName);
        }
        return input;
    }


    public static LocalDate validateDueDate(LocalDate dueDate) throws InvalidDueDateException {
        if (dueDate == null) {
            throw new InvalidDueDateException("Due date should not be null");
        }

        if (dueDate.isBefore(LocalDate.now())) {
            throw new InvalidDueDateException("Due date can not be in the past");
        }
        logger.debug("Due date is valid");
        return dueDate;
    }

    // Validate input for SQL injection prevention
    public static void validateUserInput(String input) throws InvalidUserInputException {
        logger.debug("Validating following user input: {}", input);
        if(Pattern.matches("^[a-zA-Z0-9\\s]+$", input)) {
            logger.debug("User Input is valid");
        }
        else throw new InvalidUserInputException("Input is invalid and contains special characters");
    }
}
