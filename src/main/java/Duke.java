import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    private static final String dataFilePath = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            ui.printLoadingFromFile();
            tasks = new TaskList(storage.load()); // try loading tasks from file
            ui.printLoadSuccess();
        } catch (FileNotFoundException e) {
            ui.printLoadError(); // print error msg
            tasks = new TaskList(); // give up loading from file, just init new ArrayList
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        Parser.parseUserInput();
        storage.shutDown();
    }

    public static void main(String[] args) {
        new Duke(dataFilePath).run();
    }

}
