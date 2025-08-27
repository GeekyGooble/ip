import java.util.Scanner;

public class Ui {
    private static final int UNDERSCORE_LENGTH = 60;
    private static final String OGRE_EMOJI = "\uD83E\uDDCC";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printIntroduction() {
        showLine();
        System.out.println("Hello! I'm Geegar " + OGRE_EMOJI);
        System.out.println("What can I do for you today?");
        showLine();
    }

    public void printGoodbye() {
        showLine();
        System.out.println(OGRE_EMOJI + ": Alright Bye ! Stay Geeky!");
        showLine();
    }

    public void printTaskList(TaskList tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println(OGRE_EMOJI + ": There are currently no tasks");
        } else {
            System.out.println(OGRE_EMOJI + ": Here are the tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        showLine();
    }

    public void printError(String message) {
        System.out.println(OGRE_EMOJI + ": Ooooopsies, " + message);
    }

    public void printTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();

    }

    public void printTaskMarked(Task task) {
        showLine();
        System.out.println(OGRE_EMOJI + ": Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    public void printTaskUnmarked(Task task) {
        showLine();
        System.out.println(OGRE_EMOJI + ": Alright! I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("Lock in Harder man");
        showLine();
    }

    public void printTaskDeleted(Task task, int totalTasks) {
        showLine();
        System.out.println(OGRE_EMOJI + ": Got it. I've deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    public void printSchedule() {
        showLine();
        System.out.println(OGRE_EMOJI + ": here are your deadlines/events due or during your requested time");
        showLine();
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void close() {
        this.scanner.close();
    }

}
