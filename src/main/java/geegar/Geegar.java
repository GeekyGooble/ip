package geegar;

import geegar.command.Command;
import geegar.exception.GeegarException;
import geegar.parser.Parser;
import geegar.task.TaskList;
import geegar.ui.Ui;
import geegar.storage.Storage;

public class Geegar {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Geegar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GeegarException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printIntroduction();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GeegarException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }

    public static void main(String[] args) {
        new Geegar("data/tasks.txt").run();
    }

}
//    private static final int UNDERSCORE_LENGTH = 60;
//    private static final String OGRE_EMOJI = "\uD83E\uDDCC";
//    private static ArrayList<geegar.task.Task> taskList = new ArrayList<>();
//
//    public static void main(String[] args) {
//        taskList = geegar.storage.TaskReader.loadTasks();
//
//        printIntroduction();
//        Scanner sc = new Scanner(System.in);
//
//        // While loop to continue waiting for user input until user ends chat with "bye"
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.equalsIgnoreCase(geegar.command.Command.BYE.getKeyword())) {
//                break;
//            }
//
//            try {
//                geegar.command.Command command = geegar.command.Command.convertInput(input);
//
//                if (command == null) {
//                    if (!input.isEmpty()) {
//                        throw new geegar.exception.UnknownCommandException(input);
//                    }
//                    continue;
//                }
//
//                switch (command) {
//                    case LIST:
//                        listTasks();
//                        break;
//                    case MARK:
//                        handleMarkCommand(input);
//                        break;
//                    case UNMARK:
//                        handleUnmarkCommand(input);
//                        break;
//                    case TODO:
//                        handleTodoCommand(input);
//                        break;
//                    case DEADLINE:
//                        handleDeadlineCommand(input);
//                        break;
//                    case EVENT:
//                        handleEventCommand(input);
//                        break;
//                    case DELETE:
//                        handleDeleteCommand(input);
//                        break;
//                    case SCHEDULE:
//                        handleOnCommand(input);
//                        break;
//                    default:
//                        throw new geegar.exception.UnknownCommandException(input);
//                }
//
//            } catch (geegar.exception.GeegarException e) {
//                printError(e.getMessage());
//            }
////            handleAddTask(input);
//        }
//
//        printGoodbye();
//        sc.close();
//
//    }
//
//    private static void printIntroduction() {
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println("Hello! I'm geegar.Geegar " + OGRE_EMOJI);
//        System.out.println("What can I do for you today?");
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//    }
//
//    private static void printGoodbye() {
//        String goodbye =
//                "_".repeat(UNDERSCORE_LENGTH) + "\n" + OGRE_EMOJI
//                + ": Alright Bye ! Stay Geeky!\n" + "_".repeat(UNDERSCORE_LENGTH);
//        System.out.println(goodbye);
//    }
//
//    private static void listTasks() {
//        if (taskList.isEmpty()) {
//            System.out.println("_".repeat(UNDERSCORE_LENGTH));
//            System.out.println(OGRE_EMOJI + ": There are currently no tasks");
//            System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        } else {
//            System.out.println("_".repeat(UNDERSCORE_LENGTH));
//            System.out.println(OGRE_EMOJI + ": Here are the tasks in your list:");
//            for (int i = 0; i < taskList.size(); i++) {
//                System.out.println(i + 1 + "." + taskList.get(i));
//            }
//            System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        }
//    }
//
////    private static void handleAddTask(String input) {
////        taskList[index] = new geegar.task.Task(input);
////        index++;
////
////        System.out.println("_".repeat(UNDERSCORE_LENGTH));
////        System.out.println(OGRE_EMOJI + ": added: " + input);
////        System.out.println("_".repeat(UNDERSCORE_LENGTH));
////    }
//
//
//    private static void handleMarkCommand(String input) throws geegar.exception.InvalidTaskNumberException {
//        String[] parts = input.split(" ");
//        int taskNumber = Integer.parseInt(parts[1]);
//        if (taskNumber < 1 || taskNumber > taskList.size()) {
//            throw new geegar.exception.InvalidTaskNumberException(parts[1]);
//        }
//        taskList.get(taskNumber - 1).markAsDone();
//        geegar.storage.TaskWriter.saveTasks(taskList);
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": Nice! I've marked this task as done:");
//        System.out.println(taskList.get(taskNumber - 1));
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//    }
//
//    private static void handleUnmarkCommand(String input) throws geegar.exception.InvalidTaskNumberException {
//        String[] parts = input.split(" ");
//        int taskNumber = Integer.parseInt(parts[1]);
//        if (taskNumber < 1 || taskNumber > taskList.size()) {
//            throw new geegar.exception.InvalidTaskNumberException(parts[1]);
//        }
//        taskList.get(taskNumber - 1).markNotDone();
//        geegar.storage.TaskWriter.saveTasks(taskList);
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": Alright! I've marked this task as not done yet:");
//        System.out.println(taskList.get(taskNumber - 1));
//        System.out.println("Lock in Harder man");
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//    }
//
//    private static void handleTodoCommand(String input) throws geegar.exception.GeegarException {
//        if (input.length() <= 5) {
//            throw new geegar.exception.EmptyDescriptionException("todo");
//        }
//        String description = input.substring(5); // remove the todo keyword from input
//
//        geegar.task.Todo newTask = new geegar.task.Todo(description);
//        taskList.add(newTask);
//
//        geegar.storage.TaskWriter.saveTasks(taskList);
//
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
//        System.out.println(taskList.get(taskList.size() - 1));
//        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//
//    }
//
//    private static void handleDeadlineCommand(String input) throws geegar.exception.GeegarException {
//        if (input.length() <= 9) {
//            throw new geegar.exception.EmptyDescriptionException("deadline");
//        }
//        String content = input.substring(9); // remove the deadline keyword from input
//        if (!content.contains( " /by ")) {
//            throw new geegar.exception.InvalidFormatDeadlineException();
//        }
//        String[] parts = content.split(" /by ");
//
//        String description = parts[0].trim();
//        if (description.isEmpty()) {
//            throw new geegar.exception.EmptyDescriptionException("geegar.task.Deadline");
//        }
//        String byInput = parts[1].trim();
//
//        if (byInput.isEmpty()) {
//            throw new geegar.exception.InvalidFormatDeadlineException();
//        }
//
//        // Accept format "dd/MM/yyyy HHmm" like 27/08/2025 0600
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
//        LocalDateTime by = LocalDateTime.parse(byInput, formatter);
//
//        geegar.task.Deadline newTask = new geegar.task.Deadline(description, by);
//        taskList.add(newTask);
//        geegar.storage.TaskWriter.saveTasks(taskList);
//
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
//        System.out.println(taskList.get(taskList.size() - 1));
//        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//
//    }
//
//    private static void handleEventCommand(String input) throws geegar.exception.GeegarException {
//        if (input.length() <= 6) { throw new geegar.exception.EmptyDescriptionException("event"); }
//        String content = input.substring(6); // remove the event keyword from input
//
//        if (!content.contains( " /from ")) {
//            throw new geegar.exception.InvalidFormatEventException();
//        }
//
//        String[] splitByFrom = content.split(" /from ");
//        if (splitByFrom.length != 2) {
//            throw new geegar.exception.InvalidFormatEventException();
//        }
//        if (!splitByFrom[1].contains(" /to ")) {
//            throw new geegar.exception.InvalidFormatEventException();
//        }
//        String[] splitByTo = splitByFrom[1].split(" /to ");
//
//        String description = splitByFrom[0].trim();
//        if (description.isEmpty()) {
//            throw new geegar.exception.EmptyDescriptionException("geegar.task.Event");
//        }
//        String fromInput = splitByTo[0].trim();
//        String toInput = splitByTo[1].trim();
//        if (fromInput.isEmpty() || toInput.isEmpty()) {
//            throw new geegar.exception.InvalidFormatEventException();
//        }
//
//        // Accept format "dd/MM/yyyy HHmm" like 27/08/2025 0600
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
//        LocalDateTime from = LocalDateTime.parse(fromInput, formatter);
//        LocalDateTime to = LocalDateTime.parse(toInput, formatter);
//
//        geegar.task.Event newTask = new geegar.task.Event(description, from, to);
//        taskList.add(newTask);
//        geegar.storage.TaskWriter.saveTasks(taskList);
//
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
//        System.out.println(taskList.get(taskList.size() - 1));
//        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//
//    }
//
//    private static void printError(String message) {
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": Ooooopsies, " + message);
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//    }
//
//    private static void handleDeleteCommand(String input) throws geegar.exception.GeegarException {
//        String[] parts = input.split(" ");
//        int taskNumber = Integer.parseInt(parts[1]);
//
//        if (taskNumber < 1 || taskNumber > taskList.size()) {
//            throw new geegar.exception.InvalidTaskNumberException(parts[1]);
//        }
//
//        geegar.task.Task deletedTask = taskList.get(taskNumber - 1);
//        taskList.remove(taskNumber - 1);
//
//        // Writes into the txt file the entire file again with deletion
//        geegar.storage.TaskWriter.saveTasks(taskList);
//
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": Got it. I've deleted this task:");
//        System.out.println(deletedTask);
//        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//
//    }
//
//    private static void handleOnCommand(String input) throws geegar.exception.GeegarException {
//        String timeInput = input.substring(4);
//        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("d/M/yyyy");
//        LocalDate date = LocalDate.parse(timeInput.trim(), inputFmt);
//
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": here are your deadlines/events due or during your requested time");
//        // showTasksOnDate(date);
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//
//    }
//
//
//}
