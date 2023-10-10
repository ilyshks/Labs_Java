import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.*;
import java.util.Date;

public class DataView {
    public void menu(){
        System.out.println("****************************");
        System.out.println("Меню:");
        System.out.println("Прочитать данные из базы\t1");
        System.out.println("Добавить новые прицепы\t2");
        System.out.println("Добавить новые ТС\t3");
        System.out.println("Изменить данные конкретного прицепа\t4");
        System.out.println("Изменить данные конкретного ТС\t5");
        System.out.println("Удалить прицеп\t6");
        System.out.println("Удалить ТС\t7");
        System.out.println("Записать данные в базу\t8");
        System.out.println("Выход из программы\t9");
        System.out.println("****************************");
    }

    public void outputDataToFile(String fileName, ArrayList<Trailer> t, ArrayList<IUseAuto> v){
        try (FileWriter file = new FileWriter(fileName)) {
            file.append("Количество прицепов: ").append(Integer.toString(t.size())).append("\n");
            for (Trailer trailer : t)
                file.append(trailer.toString()).append("\n");

            file.append("Количество ТС: ").append(Integer.toString(v.size())).append("\n");
            for (IUseAuto vehicle : v) {
                file.append("Название класса: ").append(vehicle.getClass().getSimpleName()).append("\n");
                file.append(vehicle.toString());
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printVehicleInfo(IUseAuto vehicle){
        vehicle.info();
        System.out.println();
    }
    public void printVehicleDrive(IUseAuto vehicle){
        vehicle.drive();
        System.out.println();
    }
    public void print(String str){
        System.out.print(str);
    }
    public void println(String str){
        System.out.println(str);
    }
    public void printTrailers(ArrayList<Trailer> trailers){
        System.out.println("Прицепы в наличии:");
        System.out.println("ID \tНазвание");
        for(int j = 0; j < trailers.size(); j++){
            String info = (j + 1) + "\t" + trailers.get(j).shortInfo();
            System.out.println(info);
        }
    }
    public void printVehicles(ArrayList<IUseAuto> vehicles){
        System.out.println("Доступные ТС:");
        System.out.println("ID \tТип ТС\tНазвание");
        for(int j = 0; j < vehicles.size(); j++){
            String info = (j + 1) + "\t" + vehicles.get(j).getClass().getSimpleName() + "\t" + vehicles.get(j).short_info();
            System.out.println(info);
        }
    }
    public void printLog(FileWriter fileLog, String str){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateNow = new Date();
        try{
            fileLog.append(formatter.format(dateNow)).append("\t").append(str).append("\n");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
