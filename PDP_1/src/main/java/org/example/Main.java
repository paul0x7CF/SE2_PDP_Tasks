package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task.data.ETaskStatus;
import task.exceptions.InvalidDueDateException;
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

        while (true) {
            System.out.println();
            System.out.println("Please choose one of the following options:");
            System.out.println("1. Create a new task");
            System.out.println("2. Update a task");
            System.out.println("3. Delete a task");
            System.out.println("4. Search for a task");
            System.out.println("5. Exit the application");

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
                    int task_ID = 0;
                    System.out.print("Please enter the task name you want to update: ");
                    String selectedTaskName = scanner.nextLine();
                    try {
                        ValidationHandler.validateUserInput(selectedTaskName);
                        task_ID = myTaskManager.searchTask(selectedTaskName);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Do you want to update the following task?");
                    System.out.println(myTaskManager.getTaskByID(task_ID));
                    System.out.print("[y/n]: ");
                    String userConfirmation = scanner.nextLine();
                    if (userConfirmation.equals("n"))
                        break;


                    System.out.println("Please choose one of the following options:");
                    System.out.println("1. Update task name");
                    System.out.println("2. Update task description");
                    System.out.println("3. Update task due date");
                    System.out.println("4. Update task status");
                    System.out.print("Please enter your choice: ");
                    int userUpdateChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (userUpdateChoice) {
                        case 1 -> {
                            System.out.print("Please enter the new task name: ");
                            String newTaskName = scanner.nextLine();
                            try {
                                ValidationHandler.validateUserInput(newTaskName);
                                myTaskManager.updateTaskName(task_ID, newTaskName);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case 2 -> {
                            System.out.print("Please enter the new task description: ");
                            String newTaskDescription = scanner.nextLine();
                            try {
                                ValidationHandler.validateUserInput(newTaskDescription);
                                myTaskManager.updateTaskDescription(task_ID, newTaskDescription);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }


                        }
                        case 3 -> {
                            System.out.print("Please enter the new task due date: ");
                            LocalDate newTaskDueDate = LocalDate.parse(scanner.nextLine());
                            try {
                                myTaskManager.updateTaskDueDate(task_ID, newTaskDueDate);
                            } catch (InvalidDueDateException e) {
                                System.out.println(e.getMessage());
                            }

                        }
                        case 4 -> {
                            System.out.println("Please choose one of the following options:");
                            System.out.println("1. Open");
                            System.out.println("2. In Progress");
                            System.out.println("3. Done");
                            System.out.print("Please enter your choice: ");
                            int userStatusChoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (userStatusChoice) {
                                case 1 -> myTaskManager.updateTaskStatus(task_ID, ETaskStatus.OPEN);
                                case 2 -> myTaskManager.updateTaskStatus(task_ID, ETaskStatus.IN_PROGRESS);
                                case 3 -> myTaskManager.updateTaskStatus(task_ID, ETaskStatus.DONE);
                            }

                        }
                    }
                }
                case 3 -> {
                    logger.info("User chose to delete a task");
                    if (myTaskManager.getSavedTaskQuantity() == 0) {
                        System.out.println("There are no tasks saved, please create a new task first");
                        break;
                    }
                    int task_ID = 0;
                    System.out.print("Please enter the task name you want to delete: ");
                    String selectedTaskName = scanner.nextLine();
                    try {
                        ValidationHandler.validateUserInput(selectedTaskName);
                        task_ID = myTaskManager.searchTask(selectedTaskName);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Do you want to delete the following task?");
                    System.out.println(myTaskManager.getTaskByID(task_ID));
                    System.out.print("[y/n]: ");
                    String userConfirmation = scanner.nextLine();
                    if (userConfirmation.equals("n"))
                        break;

                    myTaskManager.deleteTask(task_ID);

                }
                case 4 -> {
                    logger.info("User chose to search for a task");
                    if (myTaskManager.getSavedTaskQuantity() == 0) {
                        System.out.println("There are no tasks saved, please create a new task first");
                        break;
                    }
                    int task_ID = 0;
                    System.out.print("Please enter the task name you want to search: ");
                    String selectedTaskName = scanner.nextLine();
                    try {
                        ValidationHandler.validateUserInput(selectedTaskName);
                        task_ID = myTaskManager.searchTask(selectedTaskName);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Following task was found:");
                    System.out.println(myTaskManager.getTaskByID(task_ID));

                }
                case 5 -> {
                    System.out.println("Goodbye, closing...");
                    System.exit(0);
                }

            }




        }

    }
}
