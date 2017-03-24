package ru.yalymar.trend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author slavalymar
 * @since 24.03.2017
 * @version 1
 */
public class Output {

    /**
     * file for writing
     */
    private File file;

    public Output(File file) {
        this.file = file;
    }

    /** write into file
     * @param list
     */
    public void output(List<String> list){

        try(FileOutputStream out = new FileOutputStream(this.file)){

            for(String str : list){
                char[] chars = str.toCharArray();
                CharBuffer charBuffer = CharBuffer.wrap(chars);
                ByteBuffer byteBuffer = Charset.forName("Cp866").encode(charBuffer);
                byte[] buffer = byteBuffer.array();
                out.write(buffer);
                out.flush();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
