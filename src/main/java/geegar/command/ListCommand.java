package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        ui.printTaskList(tasks);
    }
}
