package geegar.exception;

public class EmptyDescriptionException extends GeegarException {
    public EmptyDescriptionException(String taskType) {
        super("Hey! The description of a " + taskType + " cannot be empty.");
    }
}
