package unitTestLogic;

import org.junit.jupiter.api.Test;
import task.data.ETaskStatus;
import task.logic.TaskManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    @Test
    void createNewTask_ValidInput_TaskCreatedSuccessfully() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        assertEquals(1, taskManager.getSavedTaskQuantity());
    }

    @Test
    void updateTaskName_ExistingTask_TaskNameUpdatedSuccessfully() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        int taskID = taskManager.getTaskByID(1).getTaskID();

        assertDoesNotThrow(() -> taskManager.updateTaskName(taskID, "Updated Task Name"));
        assertEquals("Updated Task Name", taskManager.getTaskByID(1).getTaskName());
    }

    @Test
    void updateTaskDescription_ExistingTask_DescriptionUpdatedSuccessfully() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        int taskID = taskManager.getTaskByID(1).getTaskID();

        assertDoesNotThrow(() -> taskManager.updateTaskDescription(taskID, "Updated Description"));
        assertEquals("Updated Description", taskManager.getTaskByID(1).getDescription());
    }

    @Test
    void updateTaskDueDate_ExistingTask_DueDateUpdatedSuccessfully() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        int taskID = taskManager.getTaskByID(1).getTaskID();
        LocalDate newDueDate = LocalDate.now().plusDays(14);

        assertDoesNotThrow(() -> taskManager.updateTaskDueDate(taskID, newDueDate));
        assertEquals(newDueDate, taskManager.getTaskByID(1).getDueDate());
    }

    @Test
    void updateTaskStatus_ExistingTask_StatusUpdatedSuccessfully() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        int taskID = taskManager.getTaskByID(1).getTaskID();

        assertDoesNotThrow(() -> taskManager.updateTaskStatus(taskID, ETaskStatus.IN_PROGRESS));
        assertEquals(ETaskStatus.IN_PROGRESS, taskManager.getTaskByID(1).getStatus());
    }


    @Test
    void getSavedTaskQuantity_AfterTaskDeletion_NoTasksInList() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));

        assertEquals(1, taskManager.getSavedTaskQuantity());

        assertDoesNotThrow(() -> taskManager.deleteTask(1));
        assertEquals(0, taskManager.getSavedTaskQuantity());
    }

    @Test
    void searchTask_ExistingTask_ExactMatchFound() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        int taskID = taskManager.getTaskByID(1).getTaskID();

        assertDoesNotThrow(() -> {
            int foundTaskID = taskManager.searchTask("Test Task");
            assertEquals(taskID, foundTaskID);
        });
    }

    @Test
    void searchTaskWithTypo_SimilarTaskFound_SimilarTaskIDReturned() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        int taskID = taskManager.getTaskByID(1).getTaskID();

        assertDoesNotThrow(() -> {
            int foundTaskID = taskManager.searchTask("Test Tas");
            assertEquals(taskID, foundTaskID);
        });
    }
}
