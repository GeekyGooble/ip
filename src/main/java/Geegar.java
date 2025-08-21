public class Geegar {
    public static void main(String[] args) {
        int UNDERSCORE_LENGTH = 60;
        // saying introduction
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
        System.out.println("Hello! I'm Geegar");
        System.out.println("What can I do for you today?");
        System.out.println("_".repeat(UNDERSCORE_LENGTH));

        // saying goodbye
        String goodbye = "Alright Bye! Have a Geeky Time!\n" + "_".repeat(UNDERSCORE_LENGTH);
        System.out.println(goodbye);

    }
}
