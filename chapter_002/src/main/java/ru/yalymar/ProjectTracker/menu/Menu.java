package ru.yalymar.menu;

import ru.yalymar.action.BaseAction;
import ru.yalymar.action.UserAction;
import ru.yalymar.model.Comment;
import ru.yalymar.model.Item;
import ru.yalymar.start.Input;
import ru.yalymar.tracker.Tracker;
import java.util.List;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class Menu {

    private Input input;
    private Tracker tracker;
    private final int arrLength = 7;
    private UserAction[] userActions = new UserAction[arrLength];
    private int position = 0;
    private boolean b = true;

    public Menu(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * greetings
     */
    public void greetings(){
        System.out.println("Hello. This is a tracker!");
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isB() {
        return b;
    }

    /**
     * create menu
     */
    public void fillMenu(){
        this.userActions[position++] = new AddItem("Add the Item;");
        this.userActions[position++] = new EditItem("Edit the Item;");
        this.userActions[position++] = new DeteleItem("Delete the Item;");
        this.userActions[position++] = new ShowAllItem("Show all Items;");
        this.userActions[position++] = new ShowItemsByFilter("Show Items by filter;");
        this.userActions[position++] = new CommentClass("Comments;");
        this.userActions[position++] = new Exit("Exit");
    }

    /**
     * @param key
     */
    public void select(int key){
        this.userActions[key].execute(this.input, this.tracker);
    }

    /**
     * @return int[]
     */
    public int[] getIntArr(){
        int[] arr = new int[this.arrLength];
        for(int i = 0; i<arr.length;i++){
            arr[i] = userActions[i].key();
        }
        return arr;
    }

    /**
     * show menu
     */
    public void showMenu(){
        for(UserAction userAction: userActions){
            if(userAction!=null) {
                System.out.println(userAction.print());
            }
        }
    }

    /**
     * @author slavalymar
     * @since 13.01.2017
     * @version 1
     */
    private class AddItem extends BaseAction {

        public AddItem(String name) {
            super(name);
        }

        /**
         * @return k
         */
        @Override
        public int key() {
            final int k = 0;
            return k;
        }

        /**
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {

            int newin = 0;
            do {
                newin = input.getNumber("Do you want to add Item?\n1. Yes\n2. No");
                if (newin == 1) {
                    tracker.showAllItems();
                    String name = input.ask("Enter the name: ");
                    String descriprion = input.ask("Enter the description: ");
                    tracker.add(new Item(name, descriprion));
                    tracker.showAllItems();
                }
            }
            while (newin != 2);
        }
    }

    /**
     * @author slavalymar
     * @since 13.01.2017
     * @version 1
     */
    private class EditItem extends BaseAction {

        public EditItem(String name) {
            super(name);
        }

        /**
         * @return k
         */
        @Override
        public int key() {
            final int k = 1;
            return k;
        }

        /**
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, Tracker tracker){
            tracker.showAllItems();
            String id = input.ask("Enter the id: ");
            tracker.showOneItems(tracker.findById(id));
            int newin = 0;
            boolean exist = false;

            try {
                exist = tracker.findById(id).getId().equals(id);
            }
            catch (NullPointerException e){
                System.out.println("Item`s not found");
            }
                if (exist) {
                    do {
                        System.out.println("1. Edit name;\n2. Edit Description;\n3. Back.");
                        newin = input.getNumber("Enter the number: ");
                        if (newin == 1) {
                            Item newItem = new Item(input.ask("Enter new name: "), tracker.findById(id).getDescription(), tracker.findById(id).getId());
                            tracker.update(newItem);
                        }
                        if (newin == 2) {
                            Item newItem = new Item(tracker.findById(id).getName(), input.ask("Enter new description: "), tracker.findById(id).getId());
                            tracker.update(newItem);
                        }
                    }
                    while (newin != 3);
                }



        }
    }

    /**
     * @author slavalymar
     * @since 13.01.2017
     * @version 1
     */
    private class DeteleItem extends BaseAction {

        public DeteleItem(String name) {
            super(name);
        }

        /**
         * @return k
         */
        @Override
        public int key() {
            final int k = 2;
            return 2;
        }

        /**
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.showAllItems();
            int newin = 0;

            do {
                System.out.println("What you will want to do?\n1. Delete Item;\n2. Back.\n");
                newin = input.getNumber("Enter the number: ");
                tracker.showAllItems();
                if (newin == 1) {
                    String name = input.ask("Enter Item`s name what you will want to delete: ");
                    for (Item i : tracker.findByName(name)) {
                        if (i != null) tracker.delete(i);
                    }
                    tracker.showAllItems();
                }
            }
            while (newin != 2);
        }
    }

    /**
     * @author slavalymar
     * @since 13.01.2017
     * @version 1
     */
    private class ShowAllItem extends BaseAction {

        public ShowAllItem(String name) {
            super(name);
        }

        /**
         * @return k
         */
        @Override
        public int key() {
            final int k = 3;
            return k;
        }

        /**
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.showAllItems();
        }
    }

    /**
     * @author slavalymar
     * @since 13.01.2017
     * @version 1
     */
    private class ShowItemsByFilter extends BaseAction {

        public ShowItemsByFilter(String name) {
            super(name);
        }

        /**
         * @return k
         */
        @Override
        public int key() {
            final int k = 4;
            return k;
        }

        /**
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.showAllItems();
            int newin = 0;

            do {
                System.out.println("1. Items by id;\n2. Items by name;\n3. Items by description;\n4. Back.");
                newin = input.getNumber("Enter the number: ");
                if (newin == 1) {
                    String str = input.ask("Enter the id: ");
                    Item i = tracker.findById(str);
                    tracker.showOneItems(i);
                }
                if (newin == 2) {
                    String str = input.ask("Enter the name: ");
                    List<Item> itemsByName = tracker.findByName(str);
                    tracker.showAllItems(itemsByName);
                }
                if (newin == 3) {
                    String str = input.ask("Enter the description: ");
                    List<Item> itemsByDesc = tracker.findByDescription(str);
                    tracker.showAllItems(itemsByDesc);
                }
            }
            while (newin != 4);

        }
    }

    /**
     * @author slavalymar
     * @since 13.01.2017
     * @version 1
     */
    private class CommentClass extends BaseAction {

        public CommentClass(String name) {
            super(name);
        }

        /**
         * @return k
         */
        @Override
        public int key() {
            final int k = 5;
            return k;
        }

        /**
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.showAllItems();
            int newin = 0;

            do {
                System.out.println("What you will want to do? Enter the number:\n1. Add comment by name;\n" +
                        "2. Show comment by id;\n3. Back.");
                newin = input.getNumber("Enter the number: ");
                if (newin == 1) {
                    tracker.showAllItems();
                    String name = input.ask("Enter the Item`s name you will want to add comment: ");
                    String comment = input.ask("Enter a comment");
                    tracker.addCommentByName(name, new Comment(comment));
                }
                if(newin == 2){
                    tracker.showAllItems();
                    String id = input.ask("Enter the Item`s id you will want to show comments: ");
                    tracker.showComments(id);
                }
            }
            while(newin != 3);
        }
    }

    /**
     * @author slavalymar
     * @since 13.01.2017
     * @version 1
     */
    private class Exit extends BaseAction{

        public Exit(String name) {
            super(name);
        }

        /**
         * @return k
         */
        @Override
        public int key() {
            int k = 6;
            return k;
        }

        /**
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            setB(false);
        }
    }



}

