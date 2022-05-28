public class Program {

    public static void main(String[] args) {

        User[] users = new User[10];
        for (int i = 0; i < 10; ++i) {
            Integer it = new Integer(i);
            users[i] = new User("NoName_" + it.toString(), 1000);
            System.out.print(users[i].getName());
            System.out.print(" id: ");
            System.out.println(users[i].getIdentifier());
        }
    }
}
