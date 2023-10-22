import java.io.FileWriter;
import java.io.IOException;

public class ErrCntLog {
    private static int countErrors;
    private static Log logger;
    public ErrCntLog(Log logger){
        ErrCntLog.logger = logger;
        countErrors = 0;
    }
    public int getCountErrors(){
        return countErrors;
    }
    public void startProgram(FileWriter file){
        try {
            logger.startProgram(file);
        }catch (IOException exc){
            countErrors += 1;
        }
    }
    public void usedCollection(FileWriter file, String name){
        try {
            logger.usedCollection(file, name);
        }catch (IOException exc){
            countErrors += 1;
        }
    }
    public void logElement(FileWriter file, String command, int index, long time){
        try{
            logger.logElement(file, command, index, time);
        }catch (IOException exc){
            countErrors += 1;
        }
    }

    public void InfoAddLog(FileWriter file, int length, long addTotalTime, long median) {
        try{
            logger.logInfoOfAdd(file, length, addTotalTime, median);
        }catch (IOException exc){
            countErrors += 1;
        }
    }

    public void InfoRemoveLog(FileWriter file, int length, long removeTotalTime, long median) {
        try{
            logger.logInfoOfRemove(file, length, removeTotalTime, median);
        }catch (IOException exc){
            countErrors += 1;
        }
    }

    public void finishProgram(FileWriter file) {
        try {
            logger.finishProgram(file);
        }catch (IOException exc){
            countErrors += 1;
        }
    }
}
