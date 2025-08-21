import java.util.Scanner;

public class Geegar {
    private static final int UNDERSCORE_LENGTH = 60;
    private static final String OGRE_EMOJI = "\uD83E\uDDCC";
    private static Task[] taskList = new Task[100];
    private static int index = 0;

    public static void main(String[] args) {
        printIntroduction();
        Scanner sc = new Scanner(System.in);

        // While loop to continue waiting for user input until user ends chat with "bye"
        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

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

//            handleAddTask(input);
        }

        // saying goodbye
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
        String goodbye = "_".repeat(UNDERSCORE_LENGTH) + "\n" + OGRE_EMOJI + ": Alright Bye ! Have a Geeky Time!\n" + "_".repeat(UNDERSCORE_LENGTH);
        System.out.println(goodbye);
    }

    private static void listTasks() {
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + "." + taskList[i]);
        }
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

//    private static void handleAddTask(String input) {
//        taskList[index] = new Task(input);
//        index++;
//
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//        System.out.println(OGRE_EMOJI + ": added: " + input);
//        System.out.println("_".repeat(UNDERSCORE_LENGTH));
//    }


    private static void handleMarkCommand(String input) {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        taskList[taskNumber - 1].markAsDone();
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Nice! I've marked this task as done: ");
        System.out.println(taskList[taskNumber - 1]);
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    private static void handleUnmarkCommand(String input) {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        taskList[taskNumber - 1].markNotDone();
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Alright! I've marked this task as not done yet: ");
        System.out.println(taskList[taskNumber - 1]);
        System.out.println("Lock in Harder man");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    private static void handleTodoCommand(String input) {
        String description = input.substring(5); // remove the todo keyword from input
        taskList[index] = new Todo(input);
        index++;
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
        System.out.println(taskList[index - 1]);
        System.out.println("Now you have " + index + " tasks in the list.");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

    }

    private static void handleDeadlineCommand(String input) {
        String content = input.substring(9); // remove the deadline keyword from input
        String[] parts = content.split(" /by ");

        String description = parts[0];
        String by = parts[1];

        taskList[index] = new Deadline(description, by);
        index++;

        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task: ");
        System.out.println(taskList[index - 1]);
        System.out.println("Now you have " + index + " tasks in the list.");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

    }

    private static void handleEventCommand(String input) {
        String content = input.substring(6); // remove the event keyword from input
        String[] splitByFrom = content.split(" /from ");
        String[] splitByTo = splitByFrom[1].split(" /to ");

        String description = splitByFrom[0];
        String from = splitByTo[0];
        String to = splitByTo[1];

        taskList[index] = new Event(description, from, to);
        index++;

        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task: ");
        System.out.println(taskList[index - 1]);
        System.out.println("Now you have " + index + " tasks in the list.");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

    }

}
