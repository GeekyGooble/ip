public class GeegarException extends Exception {
    public GeegarException(String message) {
        super(message);
    }
}

class EmptyDescriptionException extends GeegarException {
    public EmptyDescriptionException(String taskType) {
        super("Hey! The description of a " + taskType + " cannot be empty.");
    }
}

class UnknownCommandException extends GeegarException {
    public UnknownCommandException(String command) {
        super("I don't know what ur saying bro, try 'todo', 'deadline' to add a task!");
    }
}

class InvalidTaskNumberException extends GeegarException {
    public InvalidTaskNumberException(String taskNumber) {
        super("Task Number does not exist!");
    }
}

class InvalidFormatDeadlineException extends GeegarException {
    public InvalidFormatDeadlineException() {
        super("Invalid deadline format! Use: deadline <description> /by <time>");
    }
}

class InvalidFormatEventException extends GeegarException {
    public InvalidFormatEventException() {
        super("Invalid event format! Use: event <description> /from <start> /to <end>");

    }
}