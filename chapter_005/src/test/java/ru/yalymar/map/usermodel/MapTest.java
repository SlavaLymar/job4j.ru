package ru.yalymar.map.usermodel;

import org.junit.Test;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 14.03.2017
 * @version 1
 */
public class MapTest {

    // inner class
    public class User {

        /**
         * name of user
         */
        private String name;

        /**
         * numerous of children
         */
        private int children;

        /**
         * day of birthday
         */
        private Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }


        /**count hashcode
         * @return int
         */
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + children;
            result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
            return result;
        }

        /**checked equals
         * @param o
         * @return boolean
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (children != user.children) return false;
            if (name != null ? !name.equals(user.name) : user.name != null) return false;
            return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
        }
    }

    @Test
    public void whenCreateMapThenGetSize(){
        Map<User, Object> map = new HashMap<>();

        // create two equals users
        Calendar calendarUser = Calendar.getInstance();
        calendarUser.set(1963, 03, 12);
        User user1 = new User("Vasya", 1, calendarUser);

        User user2 = new User("Vasya", 1, calendarUser);

        // add users to map
        map.put(user1, "first");
        map.put(user2, "second");

        System.out.println(map);
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));

        assertThat(map.size(), is(2));
    }
}