import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains the task list, and operations to modify it - add, delete, mark as done, find.
 */
public class TaskList {

    public static ArrayList<Task> allTasks;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    // overload, if storage.load() is successful
    public TaskList(ArrayList<Task> taskListFromFile) {
        allTasks = taskListFromFile;
    }

    /**
     * When user adds a Task (Todo/Deadline/Event), 
     * this method parses the user input and adds it to the Task list.
     * Task is not added if the Task description is empty,
     * or if a deadline/time for Deadline/Event is not specified.
     * 
     * @param inputArr String Array containing user input for Task to be added.
     * @return Nothing.
     * @throws IllegalArgumentException when deadline/time for Deadline/Event is not specified.
     */
    public static void addTask(String[] inputArr) {

        int arrLen = inputArr.length;

        if (arrLen == 1) {
            Ui.printMissingArgWarning("desc");
            return;
        }

        // convert user input to lowercase
        for (int i = 0; i < arrLen; i++) {
            inputArr[i] = inputArr[i].toLowerCase();
        }

        Task newTask;

        switch(inputArr[0]) {
            case("todo"): {
                String[] descArr = Arrays.copyOfRange(inputArr, 1, arrLen);
                newTask = new Todo((convertToString(descArr)));
                break;
            }
            case("deadline"): {
                try {
                    int pos = Arrays.asList(inputArr).indexOf("/by");
                    if (pos == arrLen - 1) {
                        throw new IllegalArgumentException();
                    }
                    String[] descArr = Arrays.copyOfRange(inputArr, 1, pos);
                    String[] byArr = Arrays.copyOfRange(inputArr, pos + 1, arrLen);
                    newTask = new Deadline(convertToString(descArr),convertToString(byArr));
                    break;
                } catch (IllegalArgumentException e) {
                    Ui.printMissingArgWarning("date");
                    return;
                }
            }
            case("event"): {
                try {
                    int pos = Arrays.asList(inputArr).indexOf("/at");
                    if (pos == arrLen - 1) {
                        throw new IllegalArgumentException();
                    }
                    String[] descArr = Arrays.copyOfRange(inputArr, 1, pos);
                    String[] atArr = Arrays.copyOfRange(inputArr, pos + 1, arrLen);
                    newTask = new Event(convertToString(descArr), convertToString(atArr));
                    break;
                } catch (IllegalArgumentException e) {
                    Ui.printMissingArgWarning("date");
                    return;
                }
            }
            default: {
                newTask = new Task((convertToString(inputArr)));
            }
        }
        allTasks.add(newTask);
        Ui.printNewlyModifiedTask(newTask, "add");
    }

    /**
     * This method removes a Task.
     * 
     * @param num Index + 1 of Task to be deleted.
     * @return Nothing.
     * @throws IndexOutOfBoundsException when user attempts to delete a Task that does not exist.
     */
    public static void deleteTask(int num) {
        try {
            Task deletedTask = allTasks.get(num - 1);
            allTasks.remove(num-1);
            Ui.printNewlyModifiedTask(deletedTask, "delete");
        } catch (IndexOutOfBoundsException e) {
            Ui.printTaskDoesNotExistWarning();
        }
    }

    /**
     * This method marks a Task as done.
     * 
     * @param num Index + 1 of Task to be marked as done.
     * @return Nothing.
     * @throws IndexOutOfBoundsException when user attempts to mark a Task that does not exist as done.
     */
    public static void markTaskAsDone (int num) {
        try {
            Task doneTask = allTasks.get(num - 1);
            doneTask.setIsDone(true);
            Ui.printNewlyModifiedTask(doneTask, "done");
        } catch (IndexOutOfBoundsException e) {
            Ui.printTaskDoesNotExistWarning();
        }
    }

    /**
     * This method searches for tasks that contain a certain keyword, and prints them.
     * If no matching tasks are found, a warning message is printed instead.
     * 
     * @param key keyword to search for in tasks.
     * @return Nothing.
     */
    public static void findTask(String key) {
        int count = 1;
        boolean headerFlag = true;

        for (Task task: allTasks) {
            String strToSearch = task.getTaskDesc().toLowerCase();
            if (strToSearch.contains(key.toLowerCase())) {
                if (headerFlag) {
                    Ui.printMatchesFoundHeader();
                    headerFlag = false;
                }
                Ui.printANumberedTask(task,count);
                count++;
            }
        }
        if (headerFlag) {
            Ui.printNoMatchingTasksWarning();
        }
    }

    /**
     * Helper function to convert a String Array to a String,
     * instead of a String representation like what Arrays.toString(strArr) does.
     * 
     * @param strArr String Array to be converted to String
     * @return String, with spaces as delimiter.
     */
    public static String convertToString(String[] strArr) {
        return String.join(" ", strArr);
    }

}
