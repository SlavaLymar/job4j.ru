package ru.yalymar.filemanager.output;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public interface Output {

    /** write to client
     * @param str
     */
    void writeToClient(String str);

    /** send file to client
     * @param str
     */
    void sendFile(String str);

    /** send start directory
     * @param str
     */
    void sendConsole(String str);
}
