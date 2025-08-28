package geegar.exception;

public class InvalidFormatDeadlineException extends GeegarException {
    public InvalidFormatDeadlineException() {
        super("Invalid deadline format! Use: deadline <description> /by <time>");
    }
}


