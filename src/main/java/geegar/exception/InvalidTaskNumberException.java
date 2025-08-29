package geegar.exception;

/**
 * Thrown to indicate the task number shown does not exist
 */
public class InvalidTaskNumberException extends GeegarException {

    public InvalidTaskNumberException(String taskNumber) {
        super("geegar.task.Task Number does not exist!");
    }
}