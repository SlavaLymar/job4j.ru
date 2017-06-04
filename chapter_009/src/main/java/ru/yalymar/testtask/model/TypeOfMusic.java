package ru.yalymar.testtask.model;
/**
 * @author slavalymar
 * @version 1
 * @since 04.06.2017
 */
public class TypeOfMusic {

    private int id;
    private String type;

    public TypeOfMusic(String type) {
        this.type = type;
    }

    public TypeOfMusic(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeOfMusic that = (TypeOfMusic) o;

        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
