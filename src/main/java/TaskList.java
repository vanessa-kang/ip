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

    // add task to allTasks
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
        Ui.printNewlyAddedTask(newTask);
    }

    // delete task from allTasks
    public static void deleteTask(int num) {
        try {
            Task tmpTask = allTasks.get(num - 1);
            allTasks.remove(num-1);
            Ui.printNewlyDeletedTask(tmpTask);
        } catch (IndexOutOfBoundsException e) {
            Ui.printTaskDoesNotExistWarning();
        }
    }

    // mark a Task as done
    public static void markTaskAsDone (int num) {
        try {
            Task tmpTask = allTasks.get(num - 1);
            tmpTask.setIsDone(true);
            Ui.printMarkedAsDoneTask(tmpTask);
        } catch (IndexOutOfBoundsException e) {
            Ui.printTaskDoesNotExistWarning();
        }
    }

    // HELPER FUNCTION
    // convert string array to string,
    // not just string rep like what Arrays.toString(strArr) does
    public static String convertToString(String[] strArr) {
        return String.join(" ", strArr);
    }

}
