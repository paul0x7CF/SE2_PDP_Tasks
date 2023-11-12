package task.data;

import task.exceptions.InvalidDueDateException;
import task.exceptions.InvalidTextInputValidation;
import task.validation.ValidationHandler;

import java.time.LocalDate;

public class Task {

    private final int TASK_ID;
    private String taskName;
    private String description;
    private ETaskStatus status;
    private LocalDate dueDate;
    private final int MAX_TASK_NAME_LENGTH = 50;
    private final int MAX_DESCRIPTION_LENGTH = 100;

    public Task(String taskName, String description, LocalDate dueDate, int taskID) throws InvalidTextInputValidation, InvalidDueDateException {
        this.TASK_ID = taskID;
        this.taskName = ValidationHandler.validateTaskStringInput(taskName, "Task Name", MAX_TASK_NAME_LENGTH);
        this.description = ValidationHandler.validateTaskStringInput(description, "Description", MAX_DESCRIPTION_LENGTH);
        this.status = ETaskStatus.OPEN;
        this.dueDate = ValidationHandler.validateDueDate(dueDate);
    }

    public void setTaskName(String taskName) throws InvalidTextInputValidation {
        this.taskName = ValidationHandler.validateTaskStringInput(taskName, "Task Name", MAX_TASK_NAME_LENGTH);
    }

    public void setDescription(String description) throws InvalidTextInputValidation {
        this.description = this.description = ValidationHandler.validateTaskStringInput(description, "Description", MAX_DESCRIPTION_LENGTH);
    }

    public void setStatus(ETaskStatus status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) throws InvalidDueDateException {
        this.dueDate = ValidationHandler.validateDueDate(dueDate);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public ETaskStatus getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getTaskID() {
        return TASK_ID;
    }

    @Override
    public String toString() {
        return "Task ID: " + TASK_ID +
                "\nTask Name: " + taskName +
                "\nDescription: " + description +
                "\nStatus: " + status.toString() +
                "\nDue Date: " + dueDate;
    }
}
