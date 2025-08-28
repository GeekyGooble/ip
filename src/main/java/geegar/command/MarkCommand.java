package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        tasks.markTask(taskNumber - 1);
        storage.save(tasks.getTasks());
        ui.printTaskMarked(tasks.get(taskNumber - 1));
    }
}