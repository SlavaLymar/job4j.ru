package ru.lymar.t9.button;

public abstract class Button {

    private String name;

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
