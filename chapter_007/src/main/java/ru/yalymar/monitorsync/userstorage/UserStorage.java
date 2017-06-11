package ru.yalymar.monitorsync.userstorage;

import java.util.*;

/**
 * @author slavalymar
 * @since 05.04.2017
 * @version 1
 */
public class UserStorage {

    /**
     * monitor for access to users
     */
    private Object userMonitor;

    /**
     * user`s storage
     */
    private Map<Integer, User> users;

    /**
     * create userMonitor
     */
    public void createUserMonitor(){
        this.userMonitor = new Object();
    }

    /**
     * create user`s storage
     */
    public void createStorage(){
        this.users = new HashMap<>();
    }

    public Map<Integer, User> getUsers() {
        return this.users;
    }

    // class that describe user`s dao
    public class UserStorageManager{

        /** add user
         * @param user
         * @return boolean
         */
        public boolean addUser(User user) {
            synchronized (userMonitor) {
                return users.put(user.getId(), user) != null;
            }
        }

        /** edit user by id
         * @param id
         * @param newUser
         * @return boolean
         */
        public boolean edit(int id, User newUser) {
            synchronized (userMonitor) {
                boolean result = false;
                if (users.containsKey(id)) {
                    users.put(id, newUser);
                    result = true;
                }
                return result;
            }
        }

        /** remove user
         * @param id
         * @return boolean
         */
        public boolean remove(int id){
            synchronized (userMonitor) {
                boolean result = false;
                if (users.containsKey(id)) {
                    users.remove(id);
                    result = true;
                }
                return result;
            }
        }

        /**
         * get all users
         */
        public void read(){
            synchronized (userMonitor) {
                for (Integer id: users.keySet()){
                    User user = users.get(id);
                    this.print(user);
                }
            }
        }

        /** print user`s info
         * @param user
         */
        private void print(User user){
            System.out.println(String.format("id: %d, name: %s, amount: %f",
                    user.getId(), user.getName(), user.getAmount()));
        }

        /** send money from user1 to user2 by id
         * @param from
         * @param to
         * @param sum
         * @return boolean
         */
        public boolean getMoney(int from, int to, float sum){
            synchronized (userMonitor){
                boolean result = false;

                if(users.containsKey(from) &&
                        users.containsKey(to)){
                    if(users.get(from).getAmount() >= sum){
                        users.get(from).setAmount(users.get(from).getAmount()-sum);
                        users.get(to).setAmount(users.get(to).getAmount()+sum);
                        result = true;
                    }
                }
                return result;
            }
        }
    }

}
