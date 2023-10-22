import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    final private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public void startProgram(FileWriter file) throws IOException {
        Date dateNow = new Date();
        file.append("Start program: ").append(formatter.format(dateNow)).append("\n");
    }
    public void usedCollection(FileWriter file, String name) throws IOException {
        file.append(name).append("\n");
    }
    public void logElement(FileWriter file, String command, int index, long time) throws IOException {
        String str = command + ", ID = " + (index + 1) + ", " + time + "\n";
        file.append(str);
    }
    public void logInfoOfAdd(FileWriter file, int length, long addTotalTime, long median) throws IOException {
        file.append("addTotalCount = ").append(String.valueOf(length)).append("\n");
        file.append("addTotalTime = ").append(String.valueOf(addTotalTime)).append("\n");
        file.append("addMedianTime = ").append(String.valueOf(median)).append("\n");
    }

    public void logInfoOfRemove(FileWriter file, int length, long removeTotalTime, long median) throws IOException {
        file.append("removeTotalCount = ").append(String.valueOf(length)).append("\n");
        file.append("removeTotalTime = ").append(String.valueOf(removeTotalTime)).append("\n");
        file.append("removeMedianTime = ").append(String.valueOf(median)).append("\n");
    }

    public void finishProgram(FileWriter file) throws IOException {
        Date dateNow = new Date();
        file.append("Finish program: ").append(formatter.format(dateNow)).append("\n");
    }
}
