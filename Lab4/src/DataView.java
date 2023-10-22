import java.io.FileWriter;
import java.util.ArrayList;

public class DataView {
    public void print(String str){
        System.out.print(str);
    }
    public void println(String str){
        System.out.println(str);
    }

    public void printLog(ErrCntLog errAndLog, FileWriter file, String command, int index, long time){
        errAndLog.logElement(file, command, index, time);
    }
}
