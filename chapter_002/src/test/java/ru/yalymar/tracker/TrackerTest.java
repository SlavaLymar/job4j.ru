package ru.yalymar.tracker;

import org.junit.Test;
import ru.yalymar.model.Comment;
import ru.yalymar.model.Item;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class TrackerTest {
    @Test
    public void addTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Task1", "Description of task1");
        Item result = tracker.add(item);
        assertThat(result, is(item));
    }

    @Test
    public void updateTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Task1", "Description of task1");
        tracker.add(item);
        Item expected = new Item("Task21", "Description of task2", item.getId());
        tracker.update(expected);
        Item result = tracker.getItems().get(0);
        assertThat(result, is(expected));
    }

    @Test
    public void deleteTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Task1", "Description of task1");
        tracker.add(item);
        tracker.delete(item);
        List <Item> expected = new ArrayList<Item>();
        assertThat(tracker.getItems(),is(expected));
    }

    @Test
    public void findAllTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Task1", "Description of task1");
        Item item2 = new Item("Task2", "Description of task2");
        Item item3 = new Item("Task3", "Description of task3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List <Item> expected = new ArrayList<Item>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        List <Item> result = tracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void findByNameTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Task1", "Description of task1");
        Item item2 = new Item("Task2", "Description of task2");
        Item item3 = new Item("Task1", "Description of task3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List <Item> result = tracker.findByName("Task1");
        List <Item> expected = new ArrayList<Item>();
        expected.add(item1);
        expected.add(item3);
        assertThat(result, is(expected));
    }

    @Test
    public void addCommentTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Task1", "Description of task1");
        tracker.add(item1);
        tracker.addCommentById(item1.getId(), new Comment("Это комментарий"));
        String expected = "Это комментарий";
        assertThat(item1.getComments().get(0).getComment(), is(expected));
    }

}