package ru.lymar.t9.button;


/**
 * @author slavalymar
 * @since 05.03.2017
 * @version 1
 */
public abstract class Button {

    /**
     * button`s name
     */
    private String name;

    /**
     * array of characters
     */
    private char[] characters;

    public Button(String name, char[] characters) {
        this.name = name;
        this.characters = characters;
    }

    public String getName() {
        return name;
    }

    public char[] getCharacters() {
        return characters;
    }
}
