package ru.yalymar.monitorsync.userstorage;

/**
 * @author slavalymar
 * @since 05.04.2017
 * @version 1
 */
public class User {

    /**
     * user`s id
     */
    private int id;

    /**
     * user`s name
     */
    private String name;

    /**
     * user`s amount
     */
    private float amount;

    public User(int id, String name, float amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    /** equals depends of id
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    /** hashcode depends of id
     * @return int
     */
    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
