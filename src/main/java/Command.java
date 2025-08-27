import java.time.LocalDate;
import java.util.ArrayList;

abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException;
    public boolean isExit() {
        return false;
    };
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GeegarException {
        ui.printTaskList(tasks);
    }

}

class MarkCommand extends Command {
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

class UnmarkCommand extends Command {
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

class AddCommand extends Command {
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

class DeleteCommand extends Command {
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

class ScheduleCommand extends Command {
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
    }
}



