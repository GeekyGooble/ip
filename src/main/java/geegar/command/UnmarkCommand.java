package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.TaskList;

/**
 * A Command that unmarks the task as not done
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {
        tasks.unmarkTask(taskNumber - 1);
        storage.save(tasks.getTasks());
        gui.printTaskUnmarked(tasks.get(taskNumber - 1));
    }

}
