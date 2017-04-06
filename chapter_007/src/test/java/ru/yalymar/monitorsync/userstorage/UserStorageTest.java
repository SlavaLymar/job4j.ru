package ru.yalymar.monitorsync.userstorage;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class UserStorageTest {

    UserStorage userStorage = new UserStorage();
    UserStorage.UserStorageManager manager = userStorage.new UserStorageManager();

    public void initStorage(){
        this.userStorage.createStorage();
        this.userStorage.createUserMonitor();
    }

    @Test
    public void whenAddUserThenEditShouldGetNewName() throws InterruptedException {

        this.initStorage();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                manager.addUser(new User(0, "Slava", 1000.89f));
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                manager.addUser(new User(1, "Petya", 0.89f));
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                manager.edit(0, new User(0, "Vanya", 10.89f));
            }
        };

        // add into storage
        t1.start();
        t2.start();

        // wait addiction
        t1.join();
        t2.join();

        //edit user
        t3.start();
        t3.join();

        manager.read();
        assertThat(this.userStorage.getUsers().get(0).getName(), is("Vanya"));
    }

    @Test
    public void whenAddUserThenRemoveHimShouldGetNull() throws InterruptedException {
        this.initStorage();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                manager.addUser(new User(0, "Slava", 1000.89f));
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                manager.remove(0);
            }
        };

        // add user
        t1.start();
        t1.join();

        manager.read();

        // remove user
        t2.start();
        t2.join();

        assertNull(this.userStorage.getUsers().get(0));
    }

    @Test
    public void whenSendMoneyShouldIncreaseAndDecrease() throws InterruptedException {
        this.initStorage();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                manager.addUser(new User(0, "Slava", 1000.89f));
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                manager.addUser(new User(1, "Petya", 0.89f));
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                manager.getMoney(0, 1, 154.27f);
            }
        };

        Thread t4 = new Thread(){
            @Override
            public void run() {
                manager.getMoney(0, 1, 154.27f);
            }
        };

        // add users
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        manager.read();

        // send money two times
        t3.start();
        t4.start();
        t3.join();
        t4.join();

        manager.read();

        assertThat(this.userStorage.getUsers().get(0).getAmount(), is(692.35f));
        assertThat(this.userStorage.getUsers().get(1).getAmount(), is(309.43f));
    }
}