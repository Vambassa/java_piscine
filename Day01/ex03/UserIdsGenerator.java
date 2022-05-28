public class UserIdsGenerator {

    private Integer stateId = 0;

    private static UserIdsGenerator generatorId;

    private UserIdsGenerator() {}

    public static UserIdsGenerator getInstance() {
        if (generatorId == null) {
            generatorId = new UserIdsGenerator();
        }
        return generatorId;
    }

    public Integer generateId() {
        return stateId++;
    }
}
