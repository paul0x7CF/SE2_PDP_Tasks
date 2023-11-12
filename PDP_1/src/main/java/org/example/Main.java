package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task.exceptions.InvalidUserInputException;
import task.logic.TaskManger;
import task.validation.ValidationHandler;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.debug("Hallo");
        System.out.println("Welcome by the Task Manager App");
        Scanner scanner = new Scanner(System.in);
        TaskManger myTaskManager = new TaskManger();

        System.out.println("Please choose one of the following options:");
        System.out.println("1. Create a new task");
        System.out.println("2. Update a task");
        System.out.println("3. Delete a task");
        System.out.println("4. Search for a task");
        System.out.println("5. Exit the application");

        while (true) {

            int userChoice = 0;
            boolean isChoiceInValid;
            do {
                isChoiceInValid = false;
                System.out.print("Please enter your choice: ");
                try {
                    userChoice = scanner.nextInt();
                    if (userChoice < 1 || userChoice > 5) {
                        throw new NumberFormatException();
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number");
                    isChoiceInValid = true;
                    scanner.nextLine();
                }

            } while (isChoiceInValid);
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> {
                    logger.info("User chose to create a new task");
                    boolean isInputInValid;

                    do {
                        isInputInValid = false;
                        try {
                            System.out.print("Please enter the task name: ");
                            String selectedTaskName = scanner.nextLine();
                            ValidationHandler.validateUserInput(selectedTaskName);

                            System.out.print("Please enter the task description: ");
                            String selectedTaskDescription = scanner.nextLine();
                            ValidationHandler.validateUserInput(selectedTaskDescription);

                            System.out.print("Please enter the due date (yyyy-mm-dd): ");
                            LocalDate selectedDueDate = LocalDate.parse(scanner.nextLine());

                            myTaskManager.createNewTask(selectedTaskName, selectedTaskDescription, selectedDueDate);

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            isInputInValid = true;
                        }
                    }
                    while (isInputInValid);
                }

                case 2 -> {
                    logger.info("User chose to update a task");
                    if (myTaskManager.getSavedTaskQuantity() == 0) {
                        System.out.println("There are no tasks saved, please create a new task first");
                        break;
                    }
                }
                case 3 -> {
                    logger.info("User chose to delete a task");
                    if (myTaskManager.getSavedTaskQuantity() == 0) {
                        System.out.println("There are no tasks saved, please create a new task first");
                        break;
                    }

                }
                case 4 -> {
                    logger.info("User chose to search for a task");
                    if (myTaskManager.getSavedTaskQuantity() == 0) {
                        System.out.println("There are no tasks saved, please create a new task first");
                        break;
                    }

                }
                case 5 -> {
                    System.out.println("Goodbye, closing...");
                    System.exit(0);
                }

            }


        }
    }
}
