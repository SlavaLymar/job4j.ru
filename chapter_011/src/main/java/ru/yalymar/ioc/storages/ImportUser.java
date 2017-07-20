package ru.yalymar.ioc.storages;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yalymar.ioc.storages.models.Role;
import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.UserStorage;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 20.07.2017
 * @version 1
 */
public class ImportUser {

    public static final Logger logger = Logger.getLogger(ImportUser.class);
    private ApplicationContext context;

    public static void main(String[] args) {
        ImportUser instance = new ImportUser();
        instance.context = new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        UserStorage storage = instance.context.getBean(UserStorage.class);
        Scanner sc = new Scanner(System.in);


        boolean exit = false;
        while(!exit){
            instance.showUsers();
            instance.showMenu();

            int i = 0;
            try {
                i = sc.nextInt();
            }
            catch (InputMismatchException e){
                logger.error(e.getMessage(), e);
                continue;
            }
            switch (i){
                case 1:
                    boolean complete1 = false;
                    while (!complete1){
                        System.out.println("Enter login: ");
                        String login = sc.next();
                        System.out.println("Enter pwd: ");
                        String pwd = sc.next();
                        if(!"".equals(login) && !"".equals(pwd)){
                            storage.add(new User(login, pwd, new Role(2)));
                            complete1 = true;
                        }
                    }
                    break;
                case 2:
                    boolean complete2 = false;
                    while (!complete2){
                        System.out.println("Choose user by index: ");
                        int index = 0;
                        try {
                            index = sc.nextInt();
                        }
                        catch (InputMismatchException e){
                            logger.error(e.getMessage(), e);
                            continue;
                        }

                        System.out.println("Enter login: ");
                        String login = sc.next();
                        System.out.println("Enter pwd: ");
                        String pwd = sc.next();
                        if(!"".equals(login) && !"".equals(pwd)){
                            storage.update(index, new User(login, pwd, new Role(2)));
                            complete2 = true;
                        }
                    }
                    break;
                case 3:
                    boolean complete3 = false;
                    while (!complete3){
                        System.out.println("Choose index for deleting: ");
                        int index = 0;
                        try {
                            index = sc.nextInt();
                        }
                        catch (InputMismatchException e){
                            logger.error(e.getMessage(), e);
                            continue;
                        }
                        storage.delete(index);
                        complete3 = true;
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    break;
            }

        }


    }

    private void showUsers() {
        UserStorage storage = this.context.getBean(UserStorage.class);
        List<User> users = storage.getAll();
        System.out.println(String.format("index%-10slogin%-10spwd%-10srole", " ", " ", " "));
        System.out.println("------------------------------------------------------");
        users.forEach((u) -> {
            System.out.println(String.format("%-15s%-15s%-15s%-15s",
                    users.indexOf(u), u.getLogin(), u.getPassword(), u.getRole()));
        });

    }

    private void showMenu(){
        System.out.println("1. Add User\n2. Update User\n3. Delete User\n4. Exit");
    }
}
