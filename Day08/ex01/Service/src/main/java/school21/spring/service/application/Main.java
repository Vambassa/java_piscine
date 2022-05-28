package school21.spring.service.application;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void createRepository(UsersRepository repository) {

        System.out.println("Initial state of repository");
        System.out.println(repository.findAll());
        System.out.println();

        User user = new User(5L, "user@mail.ru");
        repository.save(user);
        System.out.println("Adding user");
        System.out.println(repository.findById(user.getId()));
        System.out.println();

        System.out.println("Updating user");
        user.setEmail("changed_user@edu.com");
        repository.update(user);
        System.out.println("Finding by Email");
        System.out.println(repository.findByEmail(user.getEmail()));
        System.out.println();

        System.out.println("Deleting user");
        repository.delete(user.getId());
        System.out.println(repository.findById(user.getId()));
        System.out.println();
    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println("---- usersRepositoryJdbc ----");
        createRepository(usersRepository);

        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println("---- usersRepositoryJdbcTemplate ----");
        createRepository(usersRepository);
    }
}
