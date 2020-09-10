import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

    public static final String INDENT = "     ";
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2717";

    // init array of Task objects
    public static ArrayList<Task> allTasks = new ArrayList<>();

    // helper function to convert string array to string
    // not just string rep like what Arrays.toString(strArr) does
    public static String convertToString(String[] strArr) {
        return String.join(" ", strArr);
    }

    // print array of Task objects
    public static void printAllTasks() {
        if (allTasks.size() == 0) {
            System.out.println(INDENT + "There are currently no tasks in list!");
            return;
        }
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + allTasks.get(i));
        }
    }

    // print newly added task
    public static void printNewlyAddedTask(Task task) {
        System.out.println(INDENT + "Got it. I've added this task: "
                           + System.lineSeparator()
                           + INDENT + INDENT + task
                           + System.lineSeparator()
                           + INDENT + "Now you have " + allTasks.size() + " tasks in the list.");
    }

    // mark a Task as done
    public static void markTaskAsDone (int num) {
        try {
            allTasks.get(num - 1).setIsDone(true);
            System.out.println(INDENT + "Nice! I've marked this task as done:");
            System.out.println(INDENT + "[" + TICK + "] " + allTasks.get(num - 1).getTask());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INDENT + "Sorry, this task does not exist!");
        }
    }

    public static void addTask(String[] inputArr) {

        /* TODO: implement error checking for user input
           cases:
           - inputArr[0] == _todo/deadline/event, but description field is empty
           - deadline/event does not have "/by" or "/at"
        */

        if (inputArr.length == 1) {
            System.out.println(INDENT + "Oops, the description field cannot be empty!");
            return;
        }

        Task newObj;

        switch(inputArr[0]) {
            //TODO: refactor this part
            case("todo"): {
                String[] descArr = Arrays.copyOfRange(inputArr, 1, inputArr.length);
                newObj = new Todo((convertToString(descArr)));
                break;
            }
            case("deadline"): {
                int pos = Arrays.asList(inputArr).indexOf("/by");
                String[] descArr = Arrays.copyOfRange(inputArr, 1, pos);
                String[] byArr = Arrays.copyOfRange(inputArr,pos+1,inputArr.length);
                newObj = new Deadline(convertToString(descArr),convertToString(byArr));
                break;
            }
            case("event"): {
                int pos = Arrays.asList(inputArr).indexOf("/at");
                String[] descArr = Arrays.copyOfRange(inputArr, 1, pos);
                String[] atArr = Arrays.copyOfRange(inputArr,pos+1,inputArr.length);
                newObj = new Event(convertToString(descArr),convertToString(atArr));
                break;
            }
            default: {
                newObj = new Task((convertToString(inputArr)));
            }
        }
        allTasks.add(newObj);
        printNewlyAddedTask(newObj);
    }


    //TODO: refactor main
    public static void main(String[] args) {

        System.out.println(INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n");

        Scanner line = new Scanner(System.in);
        String input = line.nextLine();

        String userCmd = "";

        while(!input.toLowerCase().equals("bye")) {
            String[] inputArr = input.split(" ");

            userCmd = inputArr[0].toLowerCase();

            switch (userCmd) {
                case("list"): {
                    printAllTasks();
                    break;
                }
                case("done"): {
                    try {
                        markTaskAsDone(Integer.parseInt(inputArr[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(INDENT + "Please input a number!");
                    } catch (NumberFormatException e) {
                        System.out.println(INDENT + "Sorry, that is not a valid input!");
                    }
                    break;
                }
                case("todo"):
                case("deadline"):
                case("event"): {
                    addTask(inputArr);
                    break;
                }
                default: {
                    System.out.println(INDENT + "Oops, I don't understand! D:");
                }
            }

            input = line.nextLine();
        }

        System.out.println(INDENT + "Bye. Hope to see you again soon!");

    }
}
