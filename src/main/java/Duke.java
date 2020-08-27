import java.util.Scanner;

public class Duke {

    public static String indent = "     ";

    public static void main(String[] args) {

        System.out.println(indent + "Hello! I'm Duke\n" + indent + "What can I do for you?\n");

        Scanner line = new Scanner(System.in);
        String input = line.nextLine();

        while(!input.toLowerCase().equals("bye")) {
            System.out.println(indent + input);
            input = line.nextLine();
        }

        System.out.println(indent + "Bye. Hope to see you again soon!");

    }
}
