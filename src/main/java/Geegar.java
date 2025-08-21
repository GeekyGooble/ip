import java.util.Scanner;

public class Geegar {
    private static final int UNDERSCORE_LENGTH = 60;
    private static final String ogreEmoji = "\uD83E\uDDCC";
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
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]);
                taskList[taskNumber - 1].markAsDone();
                System.out.println("_".repeat(UNDERSCORE_LENGTH));
                System.out.println(ogreEmoji + ": Nice! I've marked this task as done: ");
                System.out.println(taskList[taskNumber - 1]);
                System.out.println("_".repeat(UNDERSCORE_LENGTH));
                continue;
            }

            if (input.toLowerCase().startsWith("unmark ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]);
                taskList[taskNumber - 1].markNotDone();
                System.out.println("_".repeat(UNDERSCORE_LENGTH));
                System.out.println(ogreEmoji + ": Alright! I've marked this task as not done yet: ");
                System.out.println(taskList[taskNumber - 1]);
                System.out.println("Lock in Harder man");
                System.out.println("_".repeat(UNDERSCORE_LENGTH));
                continue;
            }

            // Adding a task logic
            taskList[index] = new Task(input);
            index++;

            System.out.println("_".repeat(UNDERSCORE_LENGTH));
            System.out.println(ogreEmoji + ": added: " + input);
            System.out.println("_".repeat(UNDERSCORE_LENGTH));
        }

        // saying goodbye
        printGoodbye();

    }

    private static void printIntroduction() {
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println("Hello! I'm Geegar " + ogreEmoji);
        System.out.println("What can I do for you today?");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    private static void printGoodbye() {
        String goodbye = "_".repeat(UNDERSCORE_LENGTH) + "\n" + ogreEmoji + ": Alright Bye ! Have a Geeky Time!\n" + "_".repeat(UNDERSCORE_LENGTH);
        System.out.println(goodbye);
    }

    private static void listTasks() {
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println(ogreEmoji + ": Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + "." + taskList[i]);
        }
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }


}
