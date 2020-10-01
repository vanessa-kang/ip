import java.util.Scanner;

public class Parser {


    /**
     * This method parses user input in a REPL loop.
     * 
     * @return Nothing.
     * @throws ArrayIndexOutOfBoundsException when a Task number is not provided for delete or done.
     * @throws NumberFormatException when aa non-numerical Task number is provided to delete or done.
     */
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
                        Ui.printMissingArgWarning("num");
                    } catch (NumberFormatException e) {
                        Ui.printInvalidInputWarning();
                    }
                    break;
                }
                case("done"): {
                    try {
                        TaskList.markTaskAsDone(Integer.parseInt(inputArr[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.printMissingArgWarning("num");
                    } catch (NumberFormatException e) {
                        Ui.printInvalidInputWarning();
                    }
                    break;
                }
                case("list"): {
                    Ui.printAllTasks();
                    break;
                }
                case("find"): {
                    try {
                        TaskList.findTask(inputArr[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.printMissingArgWarning("str");
                    }
                    break;
                }
                case("help"): {
                    Ui.printCommandList();
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
