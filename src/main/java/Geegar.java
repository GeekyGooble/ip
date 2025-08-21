import java.util.Scanner;

public class Geegar {
    public static void main(String[] args) {
        int UNDERSCORE_LENGTH = 60;
        // saying introduction
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println("Hello! I'm Geegar");
        System.out.println("What can I do for you today?");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

        Scanner sc = new Scanner(System.in);

        // While loop to continue waiting for user input until user ends chat with "bye"
        boolean isActive = true;
        while (isActive) {
            String input = sc.nextLine();

            System.out.println("_".repeat(UNDERSCORE_LENGTH));
            System.out.println(" " + input);
            System.out.println("_".repeat(UNDERSCORE_LENGTH));

            if (input.toLowerCase().equals("bye")) {
                isActive = false;
            }
        }

        // saying goodbye
        String goodbye = "Alright Bye !! :3 Have a Geeky Time!\n" + "_".repeat(UNDERSCORE_LENGTH);
        System.out.println(goodbye);

    }
}
