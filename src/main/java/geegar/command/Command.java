package geegar.command;

import geegar.exception.GeegarException;
import geegar.task.TaskList;
import geegar.ui.Ui;
import geegar.storage.Storage;


public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException;
    public boolean isExit() {
        return false;
    };
}



