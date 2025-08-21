import java.util.Scanner;

public class Geegar {
    public static void main(String[] args) {
        int UNDERSCORE_LENGTH = 60;
        String ogreEmoji = "\uD83E\uDDCC";

        String[] listStorage = new String[100];
        int index = 0;
        // saying introduction
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println("Hello! I'm Geegar " + ogreEmoji);
        System.out.println("What can I do for you today?");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

        Scanner sc = new Scanner(System.in);

        // While loop to continue waiting for user input until user ends chat with "bye"
        while (true) {
            String input = sc.nextLine();

            if (input.toLowerCase().equals("bye")) {
                break;
            }

            if (input.toLowerCase().equals("list")) {
                System.out.println("_".repeat(UNDERSCORE_LENGTH));
                System.out.println(ogreEmoji + " Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1 + "." + listStorage[i]);
                }
                System.out.println("_".repeat(UNDERSCORE_LENGTH));
            }

            listStorage[index] = input;
            index++;

            System.out.println("_".repeat(UNDERSCORE_LENGTH));
            System.out.println(ogreEmoji + ": added: " + input);
            System.out.println("_".repeat(UNDERSCORE_LENGTH));
        }

        // saying goodbye
        String goodbye = "_".repeat(UNDERSCORE_LENGTH) + "\n" + ogreEmoji + ": Alright Bye !! :3 Have a Geeky Time!\n" + "_".repeat(UNDERSCORE_LENGTH);
        System.out.println(goodbye);

    }
}
