package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;

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
