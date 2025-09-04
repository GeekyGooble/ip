
package geegar;

import geegar.command.Command;
import geegar.exception.GeegarException;
import geegar.parser.Parser;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;

/**
 * This is the main class
 */
public class Geegar {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Geegar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GeegarException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printIntroduction();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GeegarException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }

    public static void main(String[] args) {
        new Geegar("data/tasks.txt").run();
    }

}