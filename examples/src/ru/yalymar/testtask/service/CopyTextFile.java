package ru.yalymar.testtask.service;

import ru.yalymar.testtask.sort.Sort;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public interface CopyTextFile {

    /** copy text file #from path to #to path using buffer equals #copybuffer
     * @param from
     * @param to
     * @param copyBuffer
     */
    default void copy(File from, File to, String copyBuffer){
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(from));
             PrintWriter out = new PrintWriter(to)) {

            long s = from.length();
            while (s > 0) {
                byte[] buffer = new byte[Integer.parseInt(copyBuffer)];
                int i = in.read(buffer);

                ByteBuffer buf = ByteBuffer.wrap(buffer);
                CharBuffer charbuf = Charset.forName("Cp866").decode(buf);
                char[] ch_array = charbuf.array();
                out.write(ch_array);
                s -= i;
                out.flush();
            }
        }
        catch (IOException e){
            Sort.logger.error(e.getMessage(), e);
        }
    }
}
