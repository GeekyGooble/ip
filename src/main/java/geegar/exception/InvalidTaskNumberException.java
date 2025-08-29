package geegar.exception;

public class InvalidTaskNumberException extends GeegarException {

    public InvalidTaskNumberException(String taskNumber) {
        super("geegar.task.Task Number does not exist!");
    }
}