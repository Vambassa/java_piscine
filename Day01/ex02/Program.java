public class Program {

    public static void main(String[] args) throws UserNotFoundException{

        UsersList users = new UsersArrayList();
        User[] names = new User[20];

        for (int i = 0; i < 10; ++i) {
            Integer it = new Integer(i);
            names[i] = new User("NoName_" + it.toString(), 1000);
            users.addUser(names[i]);
        }

        User newUser = new User("Linda", 1000);
        users.addUser(newUser);

        System.out.println("Number of users = " + users.retrieveNumber());
        System.out.println(users.retrieveByIndex(1).getName() + ", " + names[1].getName());
        System.out.println(users.retrieveById(newUser.getIdentifier()).getName() + ", " + newUser.getName());
        System.out.println(users.retrieveById(100).getName());
    }
}
