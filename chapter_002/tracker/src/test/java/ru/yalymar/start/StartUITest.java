package ru.yalymar.start;


import org.junit.Test;
import ru.yalymar.menu.Menu;
import ru.yalymar.tracker.Tracker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 17.01.2017
 * @version 1
 */
public class StartUITest {

    Tracker tracker = new Tracker();

    //test for add item
    @Test
    public void stabInputTestForAddItem() {
        int[] numbers = {1, 2};
        String[] answers = {"Slava", "Molodec"};
        int positionOfMenu = 0;
        StabInput SI = new StabInput(answers, numbers, positionOfMenu);
        Menu menu = new Menu(SI, tracker);
        menu.fillMenu();
        int key = SI.ask("Question", menu.getIntArr());
        menu.select(key);
        assertThat(tracker.getItems().get(0).getName(), is("Slava"));
    }

    //test for edit item
    @Test
    public void stabInputTestForEdit(){
        stabInputTestForAddItem();

        //edit
        int[] numbers = {1, 3, 7};
        String[] answers = {"0", "Katya"};
        int positionOfMenu = 1;
        StabInput SI = new StabInput(answers, numbers, positionOfMenu);
        Menu menu = new Menu(SI, tracker);
        menu.fillMenu();
        int key = SI.ask("Question", menu.getIntArr());
        menu.select(key);
        assertThat(tracker.getItems().get(0).getName(), is("Katya"));
    }

    //test for delete item
    @Test
    public void stabInputTestForDelete(){
        stabInputTestForAddItem();

        //delete
        int[] numbers = {1, 2};
        String[] answers = {"Slava"};
        int positionOfMenu = 2;
        StabInput SI = new StabInput(answers, numbers, positionOfMenu);
        Menu menu = new Menu(SI, tracker);
        menu.fillMenu();
        int key = SI.ask("Question", menu.getIntArr());
        menu.select(key);
        String expected = null;
        assertThat(tracker.findById(answers[0]), is(expected));
    }

    //test for show all
    @Test
    public void stabInputTestForShowAll(){
        stabInputTestForAddItem();

        //show
        int[] numbers = new int[0];
        String[] answers = new String[0];
        int positionOfMenu = 3;
        StabInput SI = new StabInput(answers, numbers, positionOfMenu);
        Menu menu = new Menu(SI, tracker);
        menu.fillMenu();
        int key = SI.ask("Question", menu.getIntArr());
        menu.select(key);
    }

    //test for show by filter
    @Test
    public void stabInputTestForShowByfilter(){
        stabInputTestForAddItem();

        //show by id
        int[] numbers = {1, 4};
        String[] answers = {"0"};
        int positionOfMenu = 4;
        StabInput SI = new StabInput(answers, numbers, positionOfMenu);
        Menu menu = new Menu(SI, tracker);
        menu.fillMenu();
        int key = SI.ask("Question", menu.getIntArr());
        menu.select(key);
    }

    //test for comments
    @Test
    public void stabInputTestForComments(){
        stabInputTestForAddItem();

        //add and show comment
        int[] numbers = {1, 2, 3};
        String[] answers = {"Slava", "Kleviy", "0"};
        int positionOfMenu = 5;
        StabInput SI = new StabInput(answers, numbers, positionOfMenu);
        Menu menu = new Menu(SI, tracker);
        menu.fillMenu();
        int key = SI.ask("Question", menu.getIntArr());
        menu.select(key);
        assertThat(tracker.getItems().get(0).getComments().get(0).getComment(), is("Kleviy"));
    }

    //test for exit
    @Test
    public void stabInputTestForExit(){
        int[] numbers = {1};
        String[] answers = {""};
        int positionOfMenu = 6;
        StabInput SI = new StabInput(answers, numbers, positionOfMenu);
        Menu menu = new Menu(SI, tracker);
        menu.fillMenu();
        int key = SI.ask("Question", menu.getIntArr());
        menu.select(key);
    }

}