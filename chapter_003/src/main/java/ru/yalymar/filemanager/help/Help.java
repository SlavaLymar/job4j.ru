package ru.yalymar.filemanager.help;

import ru.yalymar.filemanager.action.BaseAction;
import ru.yalymar.filemanager.action.ClientAction;
import ru.yalymar.filemanager.filemanager.FileManager;
import ru.yalymar.filemanager.output.Output;
import ru.yalymar.filemanager.input.Input;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Help {

    private Input input;
    private Output output;
    private FileManager fileManager;
    private final int arrLength = 6;
    private ClientAction[] userActions = new ClientAction[arrLength];
    private int position = 0;
    private boolean b = true;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");

    public Help(Input input, Output output, FileManager fileManager) {
        this.input = input;
        this.output = output;
        this.fileManager = fileManager;
    }

    public String  greetings(){
        return "Welcome to File Manager!";
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isB() {
        return b;
    }

    public void fillHelp(){
        this.userActions[position++] = new GetList("Get list;");
        this.userActions[position++] = new ChangeDirectory("Change directory;");
        this.userActions[position++] = new Back("Back;");
        this.userActions[position++] = new Download("Download from server;");
        this.userActions[position++] = new Upload("Upload to server;");
        this.userActions[position++] = new Exit("Exit");
    }

    public void select(Path path) throws IOException{
        if(path.endsWith("/dir")){
            this.userActions[0].execute(path, this.fileManager);
        }
        if(path.toString().contains("/cd ")){
            this.userActions[1].execute(path, this.fileManager);
        }
        if(path.endsWith("/cd..")){
            this.userActions[2].execute(path, this.fileManager);
        }
        if(path.toString().contains("/download ")){
            this.userActions[3].execute(path, this.fileManager);
        }
        if(path.toString().contains("/upload ")){
            this.userActions[4].execute(path, this.fileManager);
        }
        if(path.endsWith("/exit")){
            this.userActions[5].execute(path, this.fileManager);
        }
    }

    public int[] getIntArr(){
        int[] arr = new int[this.arrLength];
        for(int i = 0; i<arr.length;i++){
            arr[i] = userActions[i].key();
        }
        return arr;
    }

    public void showHelp(){
        for(UserAction userAction: userActions){
            if(userAction!=null) {
                System.out.println(userAction.print());
            }
        }
    }


    private class GetList extends BaseAction {

        public GetList(String name) {
            super(name);
        }

        @Override
        public int key() {
            final int k = 0;
            return k;
        }

        @Override
        public void execute(Path path, FileManager fileManager) throws IOException{
            File[] list = fileManager.getList(path);
            for(File file : list){
                String time = sdf.format(new Date(file.lastModified()));

                String str =
                        String.format("%s%s%s%s", time, (file.isDirectory()? "<DIR>" : "  "),
                                file.length(), file.getName());
                output.writeToClient(str);
            }

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
            int newin = 0;
            boolean exist = false;
            String id = " ";

            do {
                tracker.showAllItems();
                id = input.ask("Enter the id: ");
                tracker.showOneItems(tracker.findById(id));
                if(tracker.findById(id) == null) {
                    System.out.println("Wrong id!");
                    continue;
                }
                exist = tracker.findById(id).getId().equals(id);
            }
            while (!exist);

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

