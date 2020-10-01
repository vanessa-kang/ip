/**
 * This class contains the task list,
 * and operations to modify it. 
 * (add/delete/mark as done)
 */

import java.util.ArrayList;
import java.util.Arrays;

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

        if (inputArr.length == 1) {
            Ui.printEmptyDescWarning();
            return;
        }

        Task newTask;

        switch(inputArr[0]) {
            case("todo"): {
                String[] descArr = Arrays.copyOfRange(inputArr, 1, inputArr.length);
                newTask = new Todo((convertToString(descArr)));
                break;
            }
            case("deadline"): {
                try {
                    int pos = Arrays.asList(inputArr).indexOf("/by");
                    String[] descArr = Arrays.copyOfRange(inputArr, 1, pos);
                    String[] byArr = Arrays.copyOfRange(inputArr,pos+1,inputArr.length);
                    newTask = new Deadline(convertToString(descArr),convertToString(byArr));
                    break;
                } catch (IllegalArgumentException e) {
                    Ui.printNotSpecifiedWarning("D");
                    return;
                }
            }
            case("event"): {
                try {
                    int pos = Arrays.asList(inputArr).indexOf("/at");
                    String[] descArr = Arrays.copyOfRange(inputArr, 1, pos);
                    String[] atArr = Arrays.copyOfRange(inputArr, pos + 1, inputArr.length);
                    newTask = new Event(convertToString(descArr), convertToString(atArr));
                    break;
                } catch (IllegalArgumentException e) {
                    Ui.printNotSpecifiedWarning("E");
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
     * Helper function to convert a String Array to a String,
     * instead of a String representation like what Arrays.toString(strArr) does.
     * 
     * @param strArr String Array to be converted to String
     * @return String, with spaces as delimiter.
     */
    public static String convertToString(String[] strArr) {
        return String.join(" ", strArr);
    }

    // find tasks that contain a certain keyword
    public static void findTask(String keyword) {
        int count = 1;
        boolean headerFlag = true;

        for (Task task: allTasks) {
            String strToSearch = task.getTask().toLowerCase();
            if (strToSearch.contains(keyword.toLowerCase())) {
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

}
