package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car bmw = new Car("bmw", 1);
        Car lada = new Car("lada", 3);
        Car volvo = new Car("volvo", 5);
        Car bmw1 = new Car("bmw", 1);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", bmw));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", lada));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", volvo));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", bmw1));

        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        List<User> usersWithCar = userService.getUserByCarModelAndSeries("bmw", 1);

        System.out.println("\nUsers with 'bmw' series '1'");
        for (User user : usersWithCar) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
        }

        context.close();
    }
}
