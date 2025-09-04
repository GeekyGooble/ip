package geegar.command;

import geegar.exception.GeegarException;
import geegar.task.TaskList;
import geegar.storage.Storage;
import geegar.gui.Gui;


public abstract class Command {

    /**
     * Executes the command
     *
     * @param tasks
     * @param gui
     * @param storage
     * @throws GeegarException
     */

    public abstract void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException;

    /**
     * Checks if the command is an Exit type of command
     * This will determine if the while loop for chatbot should be ended or not
     *
     * @return
     */
    public boolean isExit() {
        return false;
    };
}



