import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    public static final String INDENT = "     ";
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2717";

    // init array of Task objects
    public static ArrayList<Task> allTasks = new ArrayList<>();

    // add task to allTasks
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

    // delete task from allTasks
    public static void deleteTask(int num) {
        try {
            Task tmpTask = allTasks.get(num - 1);
            allTasks.remove(num-1);
            System.out.println(INDENT + "Noted. I've removed this task: "
                               + System.lineSeparator()
                               + INDENT + INDENT + tmpTask
                               + System.lineSeparator()
                               + INDENT + "Now you have " + allTasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INDENT + "Sorry, this task does not exist!");
        }
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

    // helper function to convert string array to string
    // not just string rep like what Arrays.toString(strArr) does
    public static String convertToString(String[] strArr) {
        return String.join(" ", strArr);
    }

    // helper function to print newly added task
    public static void printNewlyAddedTask(Task task) {
        System.out.println(INDENT + "Got it. I've added this task: "
                           + System.lineSeparator()
                           + INDENT + INDENT + task
                           + System.lineSeparator()
                           + INDENT + "Now you have " + allTasks.size() + " tasks in the list.");
    }


    public static final String dirPath = "./data";
    public static final String dataFilePath = "./data/duke.txt";

    public static void readFileContents(String filePath) throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        Scanner s = new Scanner(fr);
        while (s.hasNext()) {
            String[] strArr = s.nextLine().split(">");
//            for (int i = 0; i < strArr.length; i++) {
//                strArr[i] = strArr[i].trim();
//            }

            // strArr[0] - type of Task (T/D/E)
            // strArr[1] - whether task isDone (0/1)
            // strArr[2] - task description
            // strArr[3] - by/at (for deadline/event only)

            Task newTask;

            switch(strArr[0]) {
                case ("T"): {
                    newTask = new Todo(strArr[2]);
                    break;
                }
                case ("D"): {
                    newTask = new Deadline(strArr[2], strArr[3]);
                    break;
                }
                case ("E"): {
                    newTask = new Event(strArr[2], strArr[3]);
                    break;
                }
                default:
                    newTask = new Todo("default");
            }
            newTask.setIsDone(strArr[1].equals("1"));
            allTasks.add(newTask);
        }
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); //overwrite file content

        for (int i = 0; i < allTasks.size(); i++) {

            Task tmpTask = allTasks.get(i);
            String strToWrite = "";

            String taskType = (tmpTask instanceof Todo? "T" :
                               tmpTask instanceof Deadline? "D" : "E");
            String isDone = (tmpTask.getIsDone()? "1" : "0");

            if (taskType.equals("T")) {
                String taskDesc = tmpTask.getTask();
                strToWrite = String.join(">", taskType, isDone, taskDesc);
            } else {
                String posString = "";
                String addInfo = "";

                if (taskType.equals("D")) {
                    posString = " (by:";
                    addInfo = ((Deadline) tmpTask).getBy();
                } else if (taskType.equals("E")) {
                    posString = " (at:";
                    addInfo = ((Event) tmpTask).getAt();

                }
                int pos = tmpTask.getTask().indexOf(posString);
                String taskDesc = tmpTask.getTask().substring(0, pos);
                strToWrite = String.join(">", taskType, isDone, taskDesc, addInfo);
            }
            fw.write(strToWrite + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {

        File fDir = new File (dirPath);
        File fTxt = new File (dataFilePath);

        try {
            readFileContents(dataFilePath);
        } catch (FileNotFoundException e) {
            if (!fDir.exists()) {
                fDir.mkdir();
            }
            if (!fTxt.exists()) {
                fTxt.createNewFile();
            }
        }

        System.out.println(INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n");

        Scanner line = new Scanner(System.in);
        String input = line.nextLine();

        String userCmd;

        while(!input.toLowerCase().equals("bye")) {
            String[] inputArr = input.split(" ");

            userCmd = inputArr[0].toLowerCase();

            switch (userCmd) {
                case("todo"):
                case("deadline"):
                case("event"): {
                    addTask(inputArr);
                    break;
                }
                case("delete"): {
                    try {
                        deleteTask(Integer.parseInt(inputArr[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(INDENT + "Please input a number!");
                    } catch (NumberFormatException e) {
                        System.out.println(INDENT + "Sorry, that is not a valid input!");
                    }
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
                case("list"): {
                    printAllTasks();
                    break;
                }
                default: {
                    System.out.println(INDENT + "Oops, I don't understand! D:");
                }
            }
            input = line.nextLine();
        }

        writeToFile(dataFilePath);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");

    }
}
