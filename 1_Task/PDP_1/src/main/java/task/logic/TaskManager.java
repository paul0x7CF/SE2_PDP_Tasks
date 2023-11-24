package task.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task.data.ETaskStatus;
import task.data.Task;
import task.exceptions.InvalidDueDateException;
import task.exceptions.InvalidTextInputValidation;
import task.exceptions.TaskNotFoundException;

import java.time.LocalDate;
import java.util.HashMap;

public class TaskManager {

    private static final Logger logger = LogManager.getLogger(TaskManager.class);

    private HashMap<Integer, Task> taskList;

    public TaskManager() {
        this.taskList = new HashMap<>();
    }

    public void createNewTask(String taskName, String description, LocalDate dueDate) throws InvalidDueDateException, InvalidTextInputValidation {
        final int  NEW_TASK_ID = taskList.size() + 1;
        this.taskList.put(NEW_TASK_ID, new Task(taskName, description, dueDate, NEW_TASK_ID));
        logger.info("New task created with task ID {}", NEW_TASK_ID);
    }

    public void updateTaskName(int taskID, String taskName) throws InvalidTextInputValidation {
        this.taskList.get(taskID).setTaskName(taskName);
        logger.info("Task name updated to {} for task ID {}", taskName, taskID);
    }

    public void updateTaskDescription(int taskID, String description) throws InvalidTextInputValidation {
        this.taskList.get(taskID).setDescription(description);
        logger.info("Task description updated to {} for task ID {}", description, taskID);
    }

    public void updateTaskDueDate(int taskID, LocalDate dueDate) throws InvalidDueDateException {
        this.taskList.get(taskID).setDueDate(dueDate);
        logger.info("Task due date updated to {} for task ID {}", dueDate, taskID);
    }

    public void updateTaskStatus(int taskID, ETaskStatus status) {
        this.taskList.get(taskID).setStatus(status);
        logger.info("Task status updated to {} for task ID {}", status, taskID);
    }

    public void deleteTask(int taskID) {
        this.taskList.remove(taskID);
        logger.info("Task with task ID {} deleted", taskID);
    }

    public int getSavedTaskQuantity() {
        return this.taskList.size();
    }

    public Task getTaskByID(int taskID) {
        return this.taskList.get(taskID);
    }

    public int searchTask(String taskName) throws TaskNotFoundException {
        for (Task eachTask : this.taskList.values()) {
            if (eachTask.getTaskName().equals(taskName)) {
                logger.debug("Exact match found");
                return eachTask.getTaskID();
            }
        }
        logger.debug("No exact match found trying to find similar task name");
        int similarTaskID = searchTaskWithTypo(taskName);
        if(similarTaskID != -1) {
            logger.debug("Similar task name found");
            return similarTaskID;
        }
        throw new TaskNotFoundException("Task not found");
    }

    /**
     * Searches for a task with a similar name, considering potential typos.
     *
     * @param inputTaskName The input task name to search for.
     * @return The task ID if a match is found, or -1 if no match is found.
     */
    private int searchTaskWithTypo(String inputTaskName) {
        for (Task task : this.taskList.values()) {
            if (hasSimilarName(task.getTaskName(), inputTaskName)) {
                return task.getTaskID();
            }
        }
        return -1;
    }

    private boolean hasSimilarName(String str1, String str2) {
        final int TOLERANCE = 3;
        int distance = SearchAlgorithm.calculateLevenshteinDistance(str1, str2);
        logger.debug("Distance between {} and {} is {}", str1, str2, distance);
        return distance <= TOLERANCE;
    }




}
