import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File taskFile;

    // constructor
    public Storage(String filePath) {
        taskFile = new File(filePath);
    }

    /**
     * This method first checks if the required data file and directories exist, creating them if they do not.
     * Then, tasks are loaded from the data file into an ArrayList, which is then returned.
     * 
     * @return Task list.
     * @throws FileNotFoundException if data file is found to not exist while attempting to load tasks.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        if (!taskFile.exists()) {
            createFile();
        }
        return loadTasksFromFile(taskFile.getPath());
    }

    /* HELPER FUNCTION for load()
       create data file (and associated parent dirs), if they don't exist */
    private void createFile() {
        File parentDir = taskFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            Ui.printFileNotCreatedError();
        }
    }

    /* HELPER FUNCTION for load()
       read previously stored task data from duke.txt file, when program first starts up */
    private ArrayList<Task> loadTasksFromFile(String filePath) throws FileNotFoundException {

        ArrayList<Task> allTasks = new ArrayList<>();
        FileReader fr = new FileReader(filePath);
        Scanner s = new Scanner(fr);

        while (s.hasNext()) {
            String[] taskStrArr = s.nextLine().split(">");

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

        return allTasks;
    }

    /**
     * This method writes the existing tasks to data file.
     * 
     * @return Nothing.
     * @throws IOException when tasks fail to be saved to file.
     */
    public void shutDown() {
        try {
            Ui.printSavingToFile();
            writeTasksToFile(taskFile.getPath());
            Ui.printSaveSuccess();
        } catch (IOException e) {
            Ui.printSaveError();
        }
        Ui.printGoodbyeMessage();
    }

    // HELPER FUNCTION for shutDown()
    private void writeTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); //overwrite file content

        for (int i = 0; i < TaskList.allTasks.size(); i++) {

            Task tmpTask = TaskList.allTasks.get(i);
            String strToWrite;

            String taskType = (tmpTask instanceof Todo? "T" :
                               tmpTask instanceof Deadline? "D" : "E");
            String isDone = (tmpTask.getIsDone()? "1" : "0");

            if (taskType.equals("T")) {
                String taskDesc = tmpTask.getTaskDesc();
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
                int pos = tmpTask.getTaskDesc().indexOf(posText);
                String taskDesc = tmpTask.getTaskDesc().substring(0, pos);
                strToWrite = String.join(">", taskType, isDone, taskDesc, addInfo);
            }
            fw.write(strToWrite + System.lineSeparator());
        }
        fw.close();
    }

}