public class UsersArrayList implements UsersList {

    private User[] userArr;
    private Integer number;

    private static Integer START_SIZE = 10;

    public UsersArrayList() {
        userArr = new User[START_SIZE];
        number = 0;
    }

    @Override
    public void addUser(User user) {

        if (userArr.length == number) {
            User[] newUserArr = new User[number * 2];
            for (int i = 0; i < userArr.length; ++i) {
                newUserArr[i] = userArr[i];
            }
            userArr = newUserArr;
        }
        userArr[number] = user;
        ++number;
    }

    @Override
    public User retrieveById(Integer id) throws UserNotFoundException {

        for (int i = 0; i < number; ++i) {
            if (userArr[i].getIdentifier().equals(id)) {
                return userArr[i];
            }
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public User retrieveByIndex(Integer index) {

        return userArr[index];
    }

    @Override
    public Integer retrieveNumber() {
        return number;
    }
}