package geegar.task;

import geegar.exception.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskNumberException(String.valueOf(index + 1));
        }
        return this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void markTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskNumberException(String.valueOf(index + 1));
        }
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskNumberException(String.valueOf(index + 1));
        }
        tasks.get(index).markNotDone();
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public ArrayList<Task> showTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.by.toLocalDate().equals(date)) {
                    tasksOnDate.add(d);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if (e.from.toLocalDate().equals(date) || e.to.toLocalDate().equals(date)) {
                    tasksOnDate.add(e);
                }
            }
        }
        return tasksOnDate;
    }

    public ArrayList<Task> showTasksOnKeyword(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            return matchedTasks;
        }
        String reference = keyword.trim().toLowerCase();
        for (Task t : this.tasks) {
            if (t.getDescription().toLowerCase().contains(reference)) {
                matchedTasks.add(t);
            }
        }
        return matchedTasks;
    }
}
