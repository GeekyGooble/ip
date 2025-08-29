package geegar.parser;

import geegar.command.*;
import geegar.exception.*;
import geegar.task.Deadline;
import geegar.task.Event;
import geegar.task.Task;
import geegar.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws GeegarException {
        if (fullCommand.trim().isEmpty()) {
            throw new UnknownCommandException("");
        }

        // split into: first word which is the command
        // second portion after first space will be the following arguments
        String[] parts = fullCommand.trim().split(" ", 2);
        // first word will always be the command
        String commandWord = parts[0].toLowerCase();
        // remaining are argument sot be inputted
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseTaskNumber(arguments));
            case "unmark":
                return new UnmarkCommand(parseTaskNumber(arguments));
            case "delete":
                return new DeleteCommand(parseTaskNumber(arguments));
            case "todo":
                return new AddCommand(parseTodo(arguments));
            case "deadline":
                return new AddCommand(parseDeadline(arguments));
            case "event":
                return new AddCommand(parseEvent(arguments));
            case "on":
                return new ScheduleCommand(parseDate(arguments));
            case "find":
                return new FindCommand(parseKeyword(arguments));
            default:
                throw new UnknownCommandException(commandWord);
        }
    }

    // For methods with the following format: '<geegar.Command> <geegar.task.Task Number>'
    // mark, unmark, delete
    // (e.g. mark 1)
    private static int parseTaskNumber(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new InvalidTaskNumberException("");
        }
        try {
            return Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(arguments);
        }
    }

    private static Task parseTodo(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(arguments.trim());
    }

    // example deadline return book /by 2/12/2019 1800
    private static Task parseDeadline(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        if (!arguments.contains(" /by ")) {
            throw new InvalidFormatDeadlineException();
        }

        String[] parts = arguments.split("/by ", 2);
        String description = parts[0].trim();
        String byInput = parts[1].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (byInput.isEmpty()) {
            throw new InvalidFormatDeadlineException();
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime by = LocalDateTime.parse(byInput, formatter);
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatDeadlineException();
        }
    }

    // example: event meeting /from 2/12/2019 1800 /to 2/12/2019 1900
    private static Task parseEvent(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        if (!arguments.contains(" /from ")) {
            throw new InvalidFormatEventException();
        }

        String[] splitByFrom = arguments.split("/from ", 2);
        if (splitByFrom.length != 2 || !splitByFrom[1].contains(" /to ")) {
            throw new InvalidFormatEventException();
        }

        String[] splitByTo = splitByFrom[1].split("/to ", 2);
        if (splitByTo.length != 2) {
            throw new InvalidFormatEventException();
        }

        String description = splitByTo[0].trim();
        String fromInput = splitByTo[0].trim();
        String toInput = splitByTo[1].trim();

        if (description.isEmpty() || fromInput.isEmpty() || toInput.isEmpty()) {
            throw new InvalidFormatEventException();
        }

        try {
            // date format should be in dd/M/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(fromInput, formatter);
            LocalDateTime to = LocalDateTime.parse(toInput, formatter);
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatEventException();
        }
    }

    private static LocalDate parseDate(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("Please provide a date in the format: dd/mm/yyyy");
        }

        try {
            // date format should be in dd/M/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return LocalDate.parse(arguments.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new GeegarException("Invalid date format! type in dd/mm/yyyy dumbahh");
        }
    }

    private static String parseKeyword(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("find");
        }
        return arguments.trim();
    }
}
