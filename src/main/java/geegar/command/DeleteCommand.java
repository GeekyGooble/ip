package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;

/**
 * A Command that Deletes a task from the list
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {

        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {
        Task deletedTask = tasks.delete(taskNumber - 1);
        storage.save(tasks.getTasks());
        gui.printTaskDeleted(deletedTask, tasks.size());
    }
}
