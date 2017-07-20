package ru.yalymar.ioc.storages;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yalymar.ioc.storages.storages.MemoryStorage;
import ru.yalymar.ioc.storages.storages.UserStorage;

import java.util.Scanner;

/**
 * @author slavalymar
 * @since 20.07.2017
 * @version 1
 */
public class ImportUser {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        Scanner sc = new Scanner(System.in);

        ImportUser instance = new ImportUser();
        boolean exit = false;
        while(!exit){
            instance.showMenu();

            int i = sc.nextInt();

//            if(){
//                continue;
//            }
            switch (i){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    break;
            }

        }


    }

    private void showMenu(){
        System.out.println("1. Add User\n2. Get User\n3. Update User\n4. Delete User\n5. Exit");
    }
}
