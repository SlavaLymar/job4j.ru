package ru.yalymar.cache;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author slavalymar
 * @since 29.03.2017
 * @version 1
 */
public class InputFile {

    /** read file
     * @param file
     * @return String
     */
    public String readFile(File file){
        StringBuffer result = new StringBuffer();

        try(FileInputStream in = new FileInputStream(file);
            StringWriter out = new StringWriter()){

            long s = file.length();

            while (s > 0) {
                byte[] buffer = new byte[1024*1024];
                int i = in.read(buffer);

                ByteBuffer buf = ByteBuffer.wrap(buffer);
                CharBuffer charbuf = Charset.forName("Cp866").decode(buf);
                char[] ch_array = charbuf.array();
                out.write(ch_array);
                s-= i;
                result.append(out.getBuffer());
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return result.toString();
    }

}
