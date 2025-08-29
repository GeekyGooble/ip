package geegar.exception;

public class InvalidFormatEventException extends GeegarException {

    public InvalidFormatEventException() {
        super("Invalid event format! Use: event <description> /from <start> /to <end>");

    }
}