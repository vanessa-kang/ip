import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String indent = "     ";

    public static ArrayList<Task> allTasks = new ArrayList<>();

    public static void printAllTasks() {
        for (int i = 0; i < allTasks.size(); i++) {
            String symbol = allTasks.get(i).getIsDone() ? "\u2713" : "\u2717" ;
            System.out.println(indent + (i + 1) + ". [" + symbol + "] " + allTasks.get(i).getTask());
        }
    }

    public static void updateTaskAsDone(int num) {
        if (num >= allTasks.size()) {
            System.out.println(indent + "Sorry, this task does not exist!");
        }
        else {
            allTasks.get(num - 1).setAsDone();
            System.out.println(indent + "Nice! I've marked this task as done:");
            System.out.println(indent + "[\u2713] " + allTasks.get(num - 1).getTask());
        }
    }

    public static void addTask(String input) {
        if (!input.isEmpty()) {
            Task newTask = new Task(input);
            allTasks.add(newTask);
            System.out.println(indent + "added: " + input);
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
            String[] splitInput = input.split(" ");
            if (splitInput[0].toLowerCase().equals("list")) {
                printAllTasks();
            }
            else if (splitInput[0].toLowerCase().equals("done")) {
                updateTaskAsDone(Integer.parseInt(splitInput[1]));
            }
            else {
                addTask(input);
            }
            input = line.nextLine();
        }

        System.out.println(indent + "Bye. Hope to see you again soon!");

    }
}
