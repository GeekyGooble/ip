import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskWriter {
    private static final Path TASKS_FILE = Paths.get("data", "tasks.txt");

    public static void saveTasks(ArrayList<Task> taskList) {
        try {
            // create directory if doesnt exist
            TASKS_FILE.getParent().toFile().mkdirs();

            FileWriter writer = new FileWriter(TASKS_FILE.toString(), false);
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

}
