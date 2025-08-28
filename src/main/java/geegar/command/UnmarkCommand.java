package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        tasks.unmarkTask(taskNumber - 1);
        storage.save(tasks.getTasks());
        ui.printTaskUnmarked(tasks.get(taskNumber - 1));
    }

}
