package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;
import geegar.ui.Ui;

/**
 * A Command that Deletes a task from the list
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        Task deletedTask = tasks.delete(taskNumber - 1);
        storage.save(tasks.getTasks());
        ui.printTaskDeleted(deletedTask, tasks.size());
    }
}
