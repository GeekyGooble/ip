import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(Task.SAVE_FORMATTER) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + this.by.format(Task.DISPLAY_FORMATTER) + ")";
    }
}
