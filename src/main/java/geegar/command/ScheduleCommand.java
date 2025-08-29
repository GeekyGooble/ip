package geegar.command;

import geegar.exception.GeegarException;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;
import geegar.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A Command that shows the schedule of task based on input
 */
public class ScheduleCommand extends Command {
    private LocalDate date;

    public ScheduleCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        ui.printSchedule();
        ArrayList<Task> tasksOnDate = tasks.showTasksOnDate(date);
        for (Task task : tasksOnDate) {
            ui.printTask(task);
        }
        if (tasksOnDate.size() == 0) {
            ui.printEmpty();
        }
    }
}
