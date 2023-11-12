package unitTestLogic;

import org.junit.jupiter.api.Test;
import task.logic.TaskManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    @Test
    void createNewTask() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        assertDoesNotThrow(() -> taskManager.createNewTask("Test Task", "Description", dueDate));
        assertEquals(1, taskManager.getSavedTaskQuantity());
    }

}
