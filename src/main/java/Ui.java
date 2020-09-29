public class Ui {

    public static final String INDENT = "     ";
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2717";


    /* LOAD DATA FROM FILE */

    public static void printLoadingFromFile() {
        System.out.println(INDENT + "Loading tasks from file...");
    }

    public static void printLoadSuccess() {
        System.out.println(INDENT + "List successfully loaded! :)");
    }

    public static void printLoadError() {
        System.out.println(INDENT + "Oops, could not load from file! :(\n"
                + INDENT + "Creating a new empty list...");
    }

    public static void printFileNotCreatedError() {
        System.out.println(INDENT + "Oops, could not make a new file!");
    }


    /* WELCOME MESSAGE */

    public static void printWelcomeMessage() {
        System.out.println(INDENT + "Hello! I'm Duke\n"
                           + INDENT + "What can I do for you?");
    }


    /* TASK-RELATED MESSAGES */

    public static void printAllTasks() {
        if (TaskList.allTasks.size() == 0) {
            System.out.println(INDENT + "There are currently no tasks in list!");
            return;
        }
        System.out.println(INDENT + "Here are the tasks currently in your list:");
        for (int i = 0; i < TaskList.allTasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + TaskList.allTasks.get(i));
        }
    }

    public static void printNewlyAddedTask(Task task) {
        System.out.println(INDENT + "Got it. I've added this task: "
                + System.lineSeparator()
                + INDENT + INDENT + task
                + System.lineSeparator()
                + INDENT + "Now you have " + TaskList.allTasks.size() + " tasks in the list.");
    }

    public static void printNewlyDeletedTask(Task task) {
        System.out.println(INDENT + "Noted. I've removed this task: "
                + System.lineSeparator()
                + INDENT + INDENT + task
                + System.lineSeparator()
                + INDENT + "Now you have " + TaskList.allTasks.size() + " tasks in the list.");
    }

    public static void printMarkedAsDoneTask(Task task) {
        System.out.println(INDENT + "Nice! I've marked this task as done:"
                + System.lineSeparator()
                + INDENT + "[" + TICK + "] " + task);
    }

    public static void printEmptyDescWarning() {
        System.out.println(INDENT + "Oops, the description field cannot be empty!");
    }

    public static void printNotSpecifiedWarning(String taskType) {
        String tmp = (taskType.equals("D") ? "deadline" : "time");
        System.out.println(INDENT + "Sorry, you did not specify a " + tmp + "!");
    }

    public static void printMissingArgWarning(String key) {
        if(key.equals("num")) {
            System.out.println(INDENT + "Please input a number!");
        } else if(key.equals("str")) {
            System.out.println(INDENT + "Please input a search term!");
        }
    }

    public static void printInvalidInputWarning() {
        System.out.println(INDENT + "Sorry, that is not a valid input!");
    }

    public static void printTaskDoesNotExistWarning() {
        System.out.println(INDENT + "Sorry, this task does not exist!");
    }


    /* WRITE DATA TO FILE*/

    public static void printSavingToFile() {
        System.out.println(INDENT + "Saving tasks to file...");
    }

    public static void printSaveSuccess() {
        System.out.println(INDENT + "Tasks successfully saved! :)");
    }

    public static void printSaveError() {
        System.out.println(INDENT + "Shoots, an error occurred while saving your task data. :(");
    }


    /* GOODBYE MESSAGE */

    public static void printGoodbyeMessage() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }

}
