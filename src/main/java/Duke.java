import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String indent = "     ";

    public static ArrayList<String> taskList = new ArrayList<String>();

    public static void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(indent + (i + 1) + ". " + taskList.get(i));
        }
    }

    public static void addTask(String task) {
        if (!task.isEmpty()) {
            taskList.add(task);
            System.out.println(indent + "added: " + task);
        }
        else {
            System.out.println(indent + "No text specified.");
        }
    }

    public static void main(String[] args) {

        System.out.println(indent + "Hello! I'm Duke\n" + indent + "What can I do for you?\n");

        Scanner line = new Scanner(System.in);
        String input = line.nextLine();

        while(!input.toLowerCase().equals("bye")) {
            if (input.toLowerCase().equals("list")) {
                printTaskList();
            }
            else {
                addTask(input);
            }
            input = line.nextLine();
        }

        System.out.println(indent + "Bye. Hope to see you again soon!");

    }
}
