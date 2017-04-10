package ru.yalymar.monitorsync.findtext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {

    private final File STARTPATH;
    private File file;
    private final String TEXT;
    private boolean flag;
    private final Pattern PATTERN = Pattern.compile(".+.txt");
    private List<SearchText> list;

    public FindText(String text) {
        this.STARTPATH = new File("C:/");
        this.TEXT = text;
        this.file = null;
        this.flag = false;
        this.list = new ArrayList<>(20);
    }

    // constructor for test
    public FindText(String text, String startPath) {
        this.STARTPATH = new File(startPath);
        this.TEXT = text;
        this.file = null;
        this.flag = false;
        this.list = new ArrayList<>(20);
    }

    public void getPath(File file){
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public List<SearchText> getList() {
        return this.list;
    }

    private void setFlag(boolean flag){
        this.flag = flag;
    }

    private void finish(){
        for(SearchText st: this.list){
            if(st != null) {
                try {
                    st.sleep(1000);
                    st.interrupt();
                } catch (InterruptedException e) {
                    System.out.println(String.format("%s has been closed!", st.getName()));
                }
            }
        }
    }

    public class SearchFile extends Thread{

        @Override
        public void run() {
            this.searchFiles(null);
        }

        public synchronized void searchFiles(File file){
            if(file == null) file = STARTPATH;
            File[] files = file.listFiles();

            if(files != null) {
                for (File f : files) {
                    if (!f.isDirectory()) {
                        Matcher matcher = PATTERN.matcher(f.toString());
                        if (matcher.find()) {
                            SearchText t = new SearchText(f);

                            if (list.size() == 0) {
                                list.add(t);
                                t.start();
                            }
                            for (SearchText st : list) {
                                if (st == null || !st.isAlive()) {
                                    list.add(t);
                                    t.start();
                                    break;
                                }
                            }
                            if (flag) finish();
                        }
                    }
                }

                for (File f : files) {
                    if (f.isDirectory()) this.searchFiles(f);
                }
            }
        }
    }

    public class SearchText extends Thread{
        final File file;

        SearchText(File file){
            this.file = file;
        }

        @Override
        public void run() {
            System.out.println(String.format("%s start working in %s", Thread.currentThread().getName(), this.file.toString()));
            if(this.execute()) {
                getPath(this.file);
                setFlag(true);
                System.out.println("BINGO!!!!!!!!!!!!!!!!!!");
            }
            System.out.println(String.format("%s has complete", Thread.currentThread().getName()));
        }

        private boolean execute(){
            StringBuffer str = new StringBuffer();
            long l = this.file.length();
            try(FileReader in = new FileReader(this.file);
                StringWriter out = new StringWriter()) {

                while (l > 0) {
                    char[] buffer = new char[1024];
                    int i = in.read(buffer);
                    out.write(buffer);
                    str = out.getBuffer();
                    out.flush();

                    l -= i;
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return str.toString().contains(TEXT);
        }
    }




}
