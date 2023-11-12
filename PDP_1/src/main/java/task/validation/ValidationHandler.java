package task.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task.exceptions.InvalidDueDateException;
import task.exceptions.InvalidTextInputValidation;

import java.time.LocalDate;
import java.util.List;
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
}
