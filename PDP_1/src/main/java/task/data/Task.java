package task.data;

import java.time.LocalDate;

public class Task {

    private int taskID;
    private String taskName;
    private String description;
    private ETaskStatus status;
    private LocalDate dueDate;
    private final int MAX_TASK_NAME_LENGTH = 50;
    private final int MAX_DESCRIPTION_LENGTH = 100;

    public Task(String taskName, String description, LocalDate dueDate, int taskID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.description = description;
        this.status = ETaskStatus.OPEN;
        this.dueDate = dueDate;
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
}
