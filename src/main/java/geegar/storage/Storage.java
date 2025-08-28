package geegar.storage;

import geegar.exception.GeegarException;
import geegar.task.Task;

import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws GeegarException {
        return TaskReader.loadTasks();
    }

    public void save(ArrayList<Task> tasks) throws GeegarException {
        TaskWriter.saveTasks(tasks);
    }

    public String getFilePath() {
        return filePath;
    }
}
