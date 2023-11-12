package task.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task.exceptions.InvalidTextInputValidation;

public class ValidateTextInput implements ITaskStringInputValidation{

    private static final Logger logger = LogManager.getLogger(ValidateTextInput.class);

    @Override
    public void validateTaskStringInput(String input, String fieldName) throws InvalidTextInputValidation {
        if (input == null || input.isEmpty()) {
            throw new InvalidTextInputValidation(fieldName + " should not be null or empty");
        }
        logger.debug("Input is present");
    }
}
