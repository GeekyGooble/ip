package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;
import geegar.ui.Ui;

/**
 * A Command that adds a task to the list
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.printTaskAdded(task, tasks.size());
    }
}
