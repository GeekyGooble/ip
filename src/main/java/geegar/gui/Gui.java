package geegar.gui;

import geegar.task.Task;
import geegar.task.TaskList;

/**
 * GUI Compatible UI  handler that will capture the output as strings
 */
public class Gui {
    private StringBuilder response;

    public Gui() {
        response = new StringBuilder();
    }

    /**
     * Gets the accumulated response String
     */
    public String getResponse() {
        return this.response.toString().trim();
    }

    public void printIntroduction() {
        this.response.append("Hello! I'm Geegar!, what can I do for you today!");
    }

    public void printGoodbye() {
        this.response.append("Alright Bye ! Stay Geeky!");
    }

    /**
     * Goes through the entire task list and prints
     * in a formated way and by index
     * @param tasks List of tasks currently saved
     */
    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            this.response.append("There are currently no tasks found!");
        } else {
            this.response.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                this.response.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
    }

    public void printError(String message) {
        this.response.append("Oooopsies, ").append(message);
    }

    public void printLoadingError() {
        this.response.append("Error Loading tasks from file! I'll start with an empty task list.");
    }

    public void printTaskAdded(Task task, int totalTasks) {
        this.response.append("Got it, I've added this task: \n");
        this.response.append(task).append("\n");
        this.response.append("Now you have ").append(totalTasks).append(" tasks in the list.");
    }

    public void printTaskMarked(Task task) {
        this.response.append("Nice! I've marked this task as done!\n");
        this.response.append(task);
    }

    public void printTaskUnmarked(Task task) {
        this.response.append("Alright! I've marked this task as NOT done!\n");
        this.response.append(task).append("\n");
        this.response.append("Lock in Harder Man !!!");
    }

    public void printTaskDeleted(Task task, int totalTasks) {
        this.response.append("Got it, I've deleted this task: \n");
        this.response.append(task).append("\n");
        this.response.append("Now you have ").append(totalTasks).append(" tasks in the list.");
    }

    public void printSchedule() {
        this.response.append("Here are your deadlines/events due or during your requested time:\n");
    }

    public void printTask(Task task) {
        this.response.append(task).append("\n");
    }

    public void printFind() {
        this.response.append("Here are your tasks with similiar keywords: \n");
    }

    public void printEmpty() {
        this.response.append("There are currently no tasks found!");
    }
}
