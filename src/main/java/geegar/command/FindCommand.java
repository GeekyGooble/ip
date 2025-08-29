package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;
import geegar.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        ui.printFind();
        ArrayList<Task> tasksOnKeyword = tasks.showTasksOnKeyword(keyword);
        for (Task task : tasksOnKeyword) {
            ui.printTask(task);
        }
        if (tasksOnKeyword.size() == 0) {
            ui.printEmpty();
        }
    }

}
