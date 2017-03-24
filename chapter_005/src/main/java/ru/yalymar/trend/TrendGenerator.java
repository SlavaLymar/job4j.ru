package ru.yalymar.trend;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrendGenerator {

    /**
     * constants for report
     */
    private final SimpleDateFormat SDF =
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SimpleDateFormat SDF1 =
            new SimpleDateFormat("MM/yy");
    private final String NEXTLINE = System.getProperty("line.separator");
    private final String SPACE = " ";

    /**
     * file that contain a report
     */
    private File file;

    /**
     * output
     */
    private Output out;

    public TrendGenerator(File file) {
        this.file = file;
        this.out = new Output(file);
    }

    /** generate report
     * @param list
     * @param start
     * @param finish
     * @return Boolean
     */
    public boolean generate(List<Task> list, Calendar start, Calendar finish){
        List<String> result = new ArrayList<>();
        for(Task task : list){
            result.add(this.getTableTask(task, start, finish));
        }
        this.out.output(result);
        return true;
    }

    /** get report by task
     * @param task
     * @param start
     * @param finish
     * @return String
     */
    private String getTableTask(Task task, Calendar start, Calendar finish) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.head(task));
        sb.append(this.getTimeLine(start, finish));
        sb.append(this.table(task, start, finish));
        return sb.toString();
    }

    /** get table of operations
     * @param task
     * @param start
     * @param finish
     * @return StringBuilder
     */
    private StringBuilder table(Task task, Calendar start, Calendar finish) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for(Operation o : task.getOperations()){
            String str = String.format("%s%s%s%s%s%s%s%s%s%s", NEXTLINE, "Operation: ", o.getName(), NEXTLINE,
                                        "State: ", o.getState(), NEXTLINE,
                                        "Deadline: ", SDF.format(o.getDeadLine().getTime()), NEXTLINE);
            sb.append(str);
            sb.append(this.getTimeLineOperation(o, start, finish));
        }
        return sb;
    }

    /** get time line of operation
     * @param o
     * @param start
     * @param finish
     * @return StringBuilder
     */
    private StringBuilder getTimeLineOperation(Operation o, Calendar start, Calendar finish) {
        StringBuilder sb = new StringBuilder();
        long timeOperation = Long.valueOf(o.getDeadLine().getTimeInMillis())
                - Long.valueOf(start.getTimeInMillis());
        long time = Long.valueOf(finish.getTimeInMillis()) - Long.valueOf(start.getTimeInMillis());
        int weeksBetween = (int) (time/604800000.0);
        int weeksBetweenOp = (int) (timeOperation/604800000.0);

        StringBuilder timeLine = new StringBuilder();
        if(timeOperation > time){
            for(int i = 0; i<weeksBetween; i++){
                timeLine.append("____");
            }
            return sb.append(timeLine);
        }

        for(int i = 0; i<weeksBetweenOp; i++){
            timeLine.append("____");
        }
        return sb.append(timeLine).append(NEXTLINE);
    }

    /** get time line between date of start and finish
     * @param start
     * @param finish
     * @return StringBuilder
     */
    private StringBuilder getTimeLine(Calendar start, Calendar finish) {
        StringBuilder sb = new StringBuilder();

        long time = Long.valueOf(finish.getTimeInMillis()) - Long.valueOf(start.getTimeInMillis());
        int weeksBetween = (int) (time/604800000.0);
        StringBuilder timeLine = new StringBuilder();
        for(int i = 0; i<weeksBetween-1; i++){
            timeLine.append("____");
        }

        StringBuilder timeLineSpaces = new StringBuilder();
        for(int i = 0; i<weeksBetween-2; i++){
            timeLineSpaces.append("    ");
        }

        StringBuilder timeLineMonth = new StringBuilder();
        timeLineMonth.append("|");
        for(int i = 0; i<weeksBetween; i++){
            Calendar tmp = start;
            tmp.add(Calendar.WEEK_OF_YEAR, 1);
            if(i % 4 == 0) {
                timeLineMonth.append(SDF1.format(tmp.getTime()));
                timeLineMonth.append("     |     ");
            }
        }
        start.add(Calendar.WEEK_OF_YEAR, -weeksBetween);

        String str = String.format("%s%s%s%s%s%s%s%s%s%s", "Now", timeLineSpaces, SDF1.format(finish.getTime()), NEXTLINE,
                timeLine, NEXTLINE,
                timeLineMonth, NEXTLINE,
                timeLine, NEXTLINE);
        sb.append(str);
        return sb;
    }

    /** get head of task
     * @param task
     * @return String
     */
    private String head(Task task){
        return String.format( "%s%s%s%s%s%s%s%s%s%s%s%s", NEXTLINE, task.getName(), NEXTLINE,
                "State: ", task.getState().toString(), SPACE, "Create: ",
                SDF.format(task.getCreate().getTime()), SPACE, "Last Update: ",
                SDF.format(task.getUpd().getTime()), NEXTLINE
        );
    }
}
