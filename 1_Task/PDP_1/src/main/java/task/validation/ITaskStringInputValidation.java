package task.validation;

import task.exceptions.InvalidTextInputValidation;

public interface ITaskStringInputValidation {


    public default void validateTaskStringInput(String input, String fieldName) throws InvalidTextInputValidation{};
}
