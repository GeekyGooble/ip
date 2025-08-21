import java.util.Scanner;
import java.util.ArrayList;

public class Geegar {
    private static final int UNDERSCORE_LENGTH = 60;
    private static final String OGRE_EMOJI = "\uD83E\uDDCC";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        printIntroduction();
        Scanner sc = new Scanner(System.in);

        // While loop to continue waiting for user input until user ends chat with "bye"
        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            try {

                if (input.equalsIgnoreCase("list")) {
                    listTasks();
                    continue;
                }

                if (input.toLowerCase().startsWith("mark ")) {
                    handleMarkCommand(input);
                    continue;
                }

                if (input.toLowerCase().startsWith("unmark ")) {
                    handleUnmarkCommand(input);
                    continue;
                }

                if (input.toLowerCase().startsWith("todo ")) {
                    handleTodoCommand(input);
                    continue;
                }

                if (input.toLowerCase().startsWith("deadline ")) {
                    handleDeadlineCommand(input);
                    continue;
                }

                if (input.toLowerCase().startsWith("event ")) {
                    handleEventCommand(input);
                    continue;
                }

                if (input.toLowerCase().startsWith("delete ")) {
                    handleDeleteCommand(input);
                    continue;
                }

                if (!input.isEmpty()) {
                    throw new UnknownCommandException(input);
                }

            } catch (GeegarException e) {
                printError(e.getMessage());
            }
//            handleAddTask(input);
        }

        printGoodbye();
        sc.close();

    }

    private static void printIntroduction() {
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println("Hello! I'm Geegar " + OGRE_EMOJI);
        System.out.println("What can I do for you today?");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    private static void printGoodbye() {
        String goodbye =
                "_".repeat(UNDERSCORE_LENGTH) + "\n" + OGRE_EMOJI
                + ": Alright Bye ! Stay Geeky!\n" + "_".repeat(UNDERSCORE_LENGTH);
        System.out.println(goodbye);
    }

    private static void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("_".repeat(UNDERSCORE_LENGTH));
            System.out.println(OGRE_EMOJI + ": There are currently no tasks");
            System.out.println("_".repeat(UNDERSCORE_LENGTH));
        } else {
            System.out.println("_".repeat(UNDERSCORE_LENGTH));
            System.out.println(OGRE_EMOJI + ": Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i));
            }
            System.out.println("_".repeat(UNDERSCORE_LENGTH));
        }
    }

//    private static void handleAddTask(String input) {
//        taskList[index] = new Task(input);
//        index++;
//
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": added: " + input);
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//    }


    private static void handleMarkCommand(String input) throws InvalidTaskNumberException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new InvalidTaskNumberException(parts[1]);
        }
        taskList.get(taskNumber - 1).markAsDone();
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Nice! I've marked this task as done: ");
        System.out.println(taskList.get(taskNumber - 1));
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    private static void handleUnmarkCommand(String input) throws InvalidTaskNumberException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new InvalidTaskNumberException(parts[1]);
        }
        taskList.get(taskNumber - 1).markNotDone();
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Alright! I've marked this task as not done yet: ");
        System.out.println(taskList.get(taskNumber - 1));
        System.out.println("Lock in Harder man");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    private static void handleTodoCommand(String input) throws GeegarException {
        if (input.length() <= 5) {
            throw new EmptyDescriptionException("todo");
        }
        String description = input.substring(5); // remove the todo keyword from input

        taskList.add(new Todo(description));

        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

    }

    private static void handleDeadlineCommand(String input) throws GeegarException {
        if (input.length() <= 9) {
            throw new EmptyDescriptionException("deadline");
        }
        String content = input.substring(9); // remove the deadline keyword from input
        if (!content.contains( " /by ")) {
            throw new InvalidFormatDeadlineException();
        }
        String[] parts = content.split(" /by ");

        String description = parts[0];
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Deadline");
        }
        String by = parts[1];

        if (by.isEmpty()) {
            throw new InvalidFormatDeadlineException();
        }


        taskList.add(new Deadline(description, by));

        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

    }

    private static void handleEventCommand(String input) throws GeegarException {
        if (input.length() <= 6) { throw new EmptyDescriptionException("event"); }
        String content = input.substring(6); // remove the event keyword from input

        if (!content.contains( " /from ")) {
            throw new InvalidFormatEventException();
        }

        String[] splitByFrom = content.split(" /from ");
        if (splitByFrom.length != 2) {
            throw new InvalidFormatEventException();
        }
        if (!splitByFrom[1].contains(" /to ")) {
            throw new InvalidFormatEventException();
        }
        String[] splitByTo = splitByFrom[1].split(" /to ");

        String description = splitByFrom[0];
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Event");
        }
        String from = splitByTo[0];
        String to = splitByTo[1];
        if (from.isEmpty() || to.isEmpty()) {
            throw new InvalidFormatEventException();
        }

        taskList.add(new Event(description, from, to));

        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

    }

    private static void printError(String message) {
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Ooooopsies, " + message);
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    private static void handleDeleteCommand(String input) throws GeegarException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);

        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new InvalidTaskNumberException(parts[1]);
        }

        Task deletedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);

        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Got it. I've deleted this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

    }

}
