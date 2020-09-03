import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

    public static String indent = "     ";

    // init array of Task objects
    public static ArrayList<Task> allTasks = new ArrayList<>();

    // helper function to convert string array to string
    // not just string rep like what Arrays.toString(strArr) does
    public static String convertToString(String[] strArr) {
//        StringBuilder builder = new StringBuilder();
//        for(String s : strArr) {
//            builder.append(s + " ");
//        }
//        return builder.toString();
        return String.join(" ", strArr);
    }

    // print array of Task objects
    public static void printAllTasks() {
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(indent + (i + 1) + ". " + allTasks.get(i));
        }
    }

    // print newly added task
    public static void printNewlyAddedTask(Task task) {
        System.out.println(indent + "Got it. I've added this task: "
                + System.lineSeparator()
                + indent + indent + task
                + System.lineSeparator()
                + indent + "Now you have " + allTasks.size() + " tasks in the list.");
    }

    // mark a Task as done
    public static void markTaskAsDone(int num) {
        if (num > allTasks.size() || num == 0) {
            System.out.println(indent + "Sorry, this task does not exist!");
        }
        else {
            allTasks.get(num - 1).setIsDone(true);
            System.out.println(indent + "Nice! I've marked this task as done:");
            System.out.println(indent + "[\u2713] " + allTasks.get(num - 1).getTask());
        }
    }

    public static void addTask(String[] inputArr) {

        /* TODO: implement error checking for user input
           cases:
           - input is completely empty
           - inputArr[0] == _todo/deadline/event, but rest of the input is completely empty
           - deadline/event does not have "/by" or "/at"
        */

        Task newObj;

        switch(inputArr[0]) {
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


    public static void main(String[] args) {

        System.out.println(indent + "Hello! I'm Duke\n" + indent + "What can I do for you?\n");

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
                    markTaskAsDone(Integer.parseInt(inputArr[1]));
                    break;
                }
                default: {
                    addTask(inputArr);
                    break;
                }
            }

            input = line.nextLine();
        }

        System.out.println(indent + "Bye. Hope to see you again soon!");

    }
}
