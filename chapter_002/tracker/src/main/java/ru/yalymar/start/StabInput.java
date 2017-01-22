package ru.yalymar.start;

public class StabInput implements Input {
    private String[] answers;
    private int position = 0;
    private int[] numbers;
    private int positionNumber = 0;
    private int positionOfMenu = 0;

    public StabInput(String[] answers, int[] numbers, int positionOfMenu) {
        this.answers = answers;
        this.numbers = numbers;
        this.positionOfMenu = positionOfMenu;
    }

    public String ask(String question) {
        return this.answers[position++];
    }

    @Override
    public int ask(String question, int[] arr) {
        return this.positionOfMenu;
    }

    public int getNumber(String question) {
        return this.numbers[positionNumber++];
    }

}
