import java.util.Scanner;

public class Parser {

    // parse user input in REPL loop
    public static void parseUserInput() {
        Scanner line = new Scanner(System.in);
        String input = line.nextLine();

        String userKeyWord;

        while(!input.toLowerCase().equals("bye")) {
            String[] inputArr = input.split(" ");

            userKeyWord = inputArr[0].toLowerCase();

            switch (userKeyWord) {
                case("todo"):
                case("deadline"):
                case("event"): {
                    TaskList.addTask(inputArr);
                    break;
                }
                case("delete"): {
                    try {
                        TaskList.deleteTask(Integer.parseInt(inputArr[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.printNoNumberWarning();
                    } catch (NumberFormatException e) {
                        Ui.printInvalidInputWarning();
                    }
                    break;
                }
                case("done"): {
                    try {
                        TaskList.markTaskAsDone(Integer.parseInt(inputArr[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.printNoNumberWarning();
                    } catch (NumberFormatException e) {
                        Ui.printInvalidInputWarning();
                    }
                    break;
                }
                case("list"): {
                    Ui.printAllTasks();
                    break;
                }
                default: {
                    Ui.printInvalidInputWarning();
                }
            }
            input = line.nextLine();
        }
    }

}
