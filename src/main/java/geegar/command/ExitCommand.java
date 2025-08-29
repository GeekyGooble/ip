package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;

/**
 * A Command that exits a chatbot
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
