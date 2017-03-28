package ru.yalymar.cache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Path;

public class InputFile {

    public String readFile(Path path){
        StringBuffer result = null;

        try(FileInputStream in = new FileInputStream(path.toFile());
            StringWriter out = new StringWriter()){

            byte[] buffer = new byte[1024];
            int i;

            do {
                i = in.read(buffer);

                ByteBuffer buf = ByteBuffer.wrap(buffer);
                CharBuffer charbuf = Charset.forName("Cp866").decode(buf);
                char[] ch_array = charbuf.array();

                out.write(ch_array);
                out.flush();
                result.append(out.getBuffer());
            }
            while (i != -1);

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
