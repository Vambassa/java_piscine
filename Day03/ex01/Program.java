import java.util.concurrent.Semaphore;

public class Program {

    private static final Semaphore semEgg = new Semaphore(1);
    private static final Semaphore semHen = new Semaphore(1);

    public static void main(String[] args)  {

        if ((args.length != 1) || (args.length == 1 && !args[0].startsWith("--count="))) {
            printError("Invalid argument");
        }

        int count = Integer.parseInt(args[0].substring("--count=".length()));

        new Thread(() -> {
            for (int i = 0; i < count; ++i) {
                try {
                    semEgg.acquire();
                    System.out.println("Egg");
                    semHen.release();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
        new Thread(() -> {
            try {
                semHen.acquire();
                for (int i = 0; i < count; ++i) {
                    semHen.acquire();
                    System.out.println("Hen");
                    semEgg.release();
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }).start();
    }

    public static void printError(String str) {
        System.err.println(str);
        System.exit(1);
    }
}