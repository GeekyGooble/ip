import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(Task.SAVE_FORMATTER)
                + " (to: " + this.to.format(Task.SAVE_FORMATTER) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(Task.DISPLAY_FORMATTER)
                + " to: " + this.to.format(Task.DISPLAY_FORMATTER) + ")";
    }
}
