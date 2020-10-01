import java.io.FileNotFoundException;

public class Duke {

    private static final String dataFilePath = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            ui.printFileIOStatus("load");
            tasks = new TaskList(storage.load()); // try loading tasks from file
            ui.printFileIOSuccess("load");
        } catch (FileNotFoundException e) {
            ui.printFileIOError("load"); // print load error msg
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
