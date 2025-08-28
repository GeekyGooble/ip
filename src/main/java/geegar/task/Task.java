package geegar.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    // Machine-friendly format (for saving/reading)
    public static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Human-friendly format (for displaying to users)
    public static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
