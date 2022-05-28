public interface UsersList {

    void addUser(User user);
    User retrieveById(Integer id) throws UserNotFoundException;
    User retrieveByIndex(Integer index);
    Integer retrieveNumber();
}
