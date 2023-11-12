package task.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task.exceptions.InvalidTextInputValidation;

public class ValidateTextLength implements ITaskStringInputValidation{

    private static final Logger logger = LogManager.getLogger(ValidateTextInput.class);

    private final int MAX_TEXT_LENGTH;

    public ValidateTextLength(final int MAX_TEXT_LENGTH) {
        assert MAX_TEXT_LENGTH > 10 : "MAX_TEXT_LENGTH should be greater than 10";
        this.MAX_TEXT_LENGTH = MAX_TEXT_LENGTH;
    }
    @Override
    public void validateTaskStringInput(String input, String fieldName) throws InvalidTextInputValidation {
        if (input.length() > MAX_TEXT_LENGTH) {
            throw new InvalidTextInputValidation(fieldName + " should not be more than " + MAX_TEXT_LENGTH + " characters");
        }
        logger.debug("Input is within the characters limit");
    }
}
