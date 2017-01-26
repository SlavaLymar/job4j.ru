package ru.yalymar.deletewords;

import java.io.*;

/**
 * @author slavalymar
 * @since 26.01.2017
 * @version 1
 */
public class DeleteWords {

    /**
     * @param in
     * @param out
     * @param abuse
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"))) {
            while (br.ready()) {
                String line = br.readLine();
                for (String word : abuse) {
                    line = line.replaceAll(word, "");
                }
                bw.write(line);
            }
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (NullPointerException e) {
            throw e;
        }
    }
}
