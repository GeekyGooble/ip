
package geegar;

import geegar.command.Command;
import geegar.exception.GeegarException;
import geegar.parser.Parser;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;
import geegar.gui.Gui;

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


//    public void run() {
//        ui.printIntroduction();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (GeegarException e) {
//                ui.printError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//
//        }
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (input.trim().isEmpty()) {
            return "Please enter a command!";
        }

        try {
            Command command = Parser.parse(input);
            Gui gui = new Gui();

            command.execute(tasks, gui, storage);

            if (command.isExit()) {
                return "Alright, Bye! Stay Geeky!";
            }

            return gui.getResponse();

        } catch (GeegarException e) {
            return "Error!!: " + e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        return "Welcome to Geegar!";
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


}