package ru.yalymar.testtask.app;

import ru.yalymar.testtask.sort.Sort;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 *
 * This application sorts the text file in a lexerical order.
 * For correct operation it is necessary to set in "resources/settings.properties":
 * - #COUNTOFTHREADS: the number of threads in the thread pool;
 * - #SOURCEPATH: path to the file to be sorted;
 * - #DISTANCEPATH: path to the sorted file;
 * - #COUNFOFLINES: the number of lines read from the source file for
 * sorting in a separate thread
 * - #COPYBUFFER: the size of the buffer to copy the sorted file into #DISTANCEPATH.
 */
public class StartApp {

    /** start application
     * @param args
     */
    public static void main(String[] args) {
        Sort sort = new Sort();
        sort.sort();
        sort.getThreadPool().shutdown();
        do {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                Sort.logger.error(e.getMessage(), e);
            }
        }
        while(Thread.activeCount() > 2);
    }
}
