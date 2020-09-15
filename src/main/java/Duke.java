import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    // defining some constants
    public static final String INDENT = "     ";
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2717";
    public static final String dirPath = "./data";
    public static final String dataFilePath = "./data/duke.txt";

    // init array of Task objects
    public static ArrayList<Task> allTasks = new ArrayList<>();

    // initialise task file data, print welcome message
    public static void initProgram() {
        File fDir = new File (dirPath);
        File fTxt = new File (dataFilePath);

        try {
            readFileContents(dataFilePath);
        } catch (FileNotFoundException e) {
            if (!fDir.exists()) {
                fDir.mkdir();
            }
            if (!fTxt.exists()) {
                try {
                    fTxt.createNewFile();
                } catch (IOException ioException) {
                    System.exit(1);
                }
            }
        }

        System.out.println(INDENT + "Hello! I'm Duke"
                           + System.lineSeparator()
                           + INDENT + "What can I do for you?");
    }

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
    }

    // shut down Duke
    public static void shutDownProgram() {
        try {
            writeToFile(dataFilePath);
        } catch (IOException e) {
            System.out.println(INDENT + "Shoots, an error occurred while saving your list data. :(");
        }
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }

    // read previously stored task data from duke.txt file, when program first starts up
    public static void readFileContents(String filePath) throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        Scanner s = new Scanner(fr);
        while (s.hasNext()) {
            String[] taskStrArr = s.nextLine().split(">");
//            for (int i = 0; i < taskStrArr.length; i++) {
//                taskStrArr[i] = taskStrArr[i].trim();
//            }

            // strArr[0] - type of Task (T/D/E)
            // strArr[1] - whether task isDone (0/1)
            // strArr[2] - task description
            // strArr[3] - by/at (for deadline/event only)

            Task newTask;

            switch(taskStrArr[0]) {
            case ("T"): {
                newTask = new Todo(taskStrArr[2]);
                break;
            }
            case ("D"): {
                newTask = new Deadline(taskStrArr[2], taskStrArr[3]);
                break;
            }
            case ("E"): {
                newTask = new Event(taskStrArr[2], taskStrArr[3]);
                break;
            }
            default:
                newTask = new Todo("default");
            }
            newTask.setIsDone(taskStrArr[1].equals("1"));
            allTasks.add(newTask);
        }
    }

    // write all task data to file, before program closes
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
                String posText = "";
                String addInfo = "";

                if (taskType.equals("D")) {
                    posText = " (by:";
                    addInfo = ((Deadline) tmpTask).getBy();
                } else if (taskType.equals("E")) {
                    posText = " (at:";
                    addInfo = ((Event) tmpTask).getAt();
                }
                int pos = tmpTask.getTask().indexOf(posText);
                String taskDesc = tmpTask.getTask().substring(0, pos);
                strToWrite = String.join(">", taskType, isDone, taskDesc, addInfo);
            }
            fw.write(strToWrite + System.lineSeparator());
        }
        fw.close();
    }

    // add task to allTasks
    public static void addTask(String[] inputArr) {

        if (inputArr.length == 1) {
            System.out.println(INDENT + "Oops, the description field cannot be empty!");
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
                System.out.println(INDENT + "Sorry, you did not specify a deadline!");
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
                System.out.println(INDENT + "Sorry, you did not specify a time!");
                return;
            }
        }
        default: {
            newTask = new Task((convertToString(inputArr)));
        }
        }
        allTasks.add(newTask);
        printNewlyAddedTask(newTask);
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
            System.out.println(INDENT + "Nice! I've marked this task as done:"
                               + System.lineSeparator()
                               + INDENT + "[" + TICK + "] " + allTasks.get(num - 1).getTask());
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
        System.out.println(INDENT + "Here are the tasks currently in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + allTasks.get(i));
        }
    }

    // helper function to print newly added task
    public static void printNewlyAddedTask(Task task) {
        System.out.println(INDENT + "Got it. I've added this task: "
                + System.lineSeparator()
                + INDENT + INDENT + task
                + System.lineSeparator()
                + INDENT + "Now you have " + allTasks.size() + " tasks in the list.");
    }

    // helper function to convert string array to string
    // not just string rep like what Arrays.toString(strArr) does
    public static String convertToString(String[] strArr) {
        return String.join(" ", strArr);
    }



    public static void main(String[] args) throws IOException {
        initProgram();
        parseUserInput();
        shutDownProgram();
    }
}
