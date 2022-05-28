public class Program {

    public static void main(String[] args) {

        if ((args.length != 1) || (args.length == 1 && !args[0].startsWith("--count="))) {
            printError("Invalid argument");
        }

        try {

            int count = Integer.parseInt(args[0].substring("--count=".length()));

            Thread egg = new Thread(new Egg(count));
            Thread hen = new Thread(new Hen(count));

            egg.start();
            hen.start();

            egg.join();
            hen.join();


            for (int i = 0; i < count; ++i) {
                System.out.println("Human");
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void printError(String str) {
        System.err.println(str);
        System.exit(1);
    }
}