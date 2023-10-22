import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    private static Data model;
    private static DataView view;

    public DataController(Data m, DataView v){
        model = m;
        view = v;
    }
    public ArrayList<IUseAuto> getResearchArray(){
        return model.getResearchArray();
    }
    public void createData(int length){
        model.createData(length);
    }
    public IUseAuto createAuto(){
        return model.createAuto();
    }
    public long addVehicleToResearchArrayList(IUseAuto new_vehicle){
        return model.addVehicleToResearchArrayList(new_vehicle);
    }
    public long addVehicleToArrayList(IUseAuto new_vehicle){
        return model.addVehicleToArrayList(new_vehicle);
    }
    public long addVehicleToLinkedList(IUseAuto new_vehicle){
        return model.addVehicleToLinkedList(new_vehicle);
    }
    public long addVehicleToHashMap(IUseAuto new_vehicle){
        return model.addVehicleToHashMap(new_vehicle);
    }
    public void print(String str){
        view.print(str);
    }
    public void println(String str){
        view.println(str);
    }
    public int inputLimitedInt(Scanner input, int min, int max){
        while (true){
            try{
                int value = Integer.parseInt(input.nextLine());
                if (value >= min && value <= max) return value;
                else view.print("Введите заново целое положительное число от " + min + " до "+ max +": ");
            }catch (Exception e){
                view.print("Введите заново целое положительное число от " + min + " до "+ max +": ");
            }
        }
    }
    public int inputConsolePositiveInt(Scanner input){
        while (true){
            try{
                int value = Integer.parseInt(input.nextLine());
                if (value > 0) return value;
                else view.print("Введите заново целое положительное число: ");
            }catch (Exception e){
                view.print("Введите заново целое положительное число: ");
            }
        }
    }
    public int inputConsoleNotNegativeInt(Scanner input){
        while (true){
            try{
                int value = Integer.parseInt(input.nextLine());
                if (value >= 0) return value;
                else view.print("Введите заново целое неторицательное число: ");
            }catch (Exception e){
                view.print("Введите заново целое неторицательное число: ");
            }
        }
    }
    public double inputConsolePositiveDouble(Scanner input){
        while (true){
            try{
                double value = Double.parseDouble(input.nextLine());
                if (value > 0) return value;
                else view.print("Введите заново положительное число: ");
            }catch (Exception e){
                view.print("Введите заново положительное число: ");
            }
        }
    }
    public void log(ErrCntLog errAndLog, FileWriter file, String command, int index, long time){
        view.printLog(errAndLog, file, command, index, time);
    }

    public void startProgramLog(ErrCntLog errorsAndLog, FileWriter file) {
        errorsAndLog.startProgram(file);
    }

    public void collectionNameLog(ErrCntLog errorsAndLog, FileWriter file, String name) {
        errorsAndLog.usedCollection(file, name);
    }

    public void InfoAddLog(ErrCntLog errorsAndLog, FileWriter file, int length, long addTotalTime, long median) {
        errorsAndLog.InfoAddLog(file, length, addTotalTime, median);
    }

    public long removeVehicleFromArrayList() {
        return model.removeVehicleFromArrayList();
    }

    public void InfoRemoveLog(ErrCntLog errorsAndLog, FileWriter file, int length, long removeTotalTime, long median) {
        errorsAndLog.InfoRemoveLog(file, length, removeTotalTime, median);
    }

    public long removeVehicleFromLinkedList() {
        return model.removeVehicleFromLinkedList();
    }

    public long removeVehicleFromHashMap() {
        return model.removeVehicleFromHashMap();
    }

    public void finishProgramLog(ErrCntLog errorsAndLog, FileWriter file) {
        errorsAndLog.finishProgram(file);
    }
}
