import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String fileDB = "DataBase.dat";
        final String settingsName = "Settings.txt";
        final String fileLogName = "log.log";
        ArrayList<Trailer> trailers = new ArrayList<>(3);
        trailers.add(0, new Trailer("Креон", "1800", 500, 0));
        trailers.add(1, new Trailer("Русич", "203", 540, 0));
        trailers.add(2, new Trailer("Swift", "BaseCamp", 1000, 3));

        ArrayList<IUseAuto> vehicles = new ArrayList<>(4);

        Data model = new Data(trailers, vehicles);
        DataView view = new DataView();
        DataController controller = new DataController(model, view);

        controller.addVehicle(new Bike("Иж", "Планета", 200, 2, 90));
        controller.addVehicle(new Car("ВАЗ", "2105", 400, 5, 150, trailers.get(0)));
        controller.addVehicle(new Truck("ГАЗ", "2705", 1350, 2, 115, trailers.get(2)));
        controller.addVehicle(new Bus("ЛиАЗ", "5291", 13600, 48, 120));
        // Здесь вызвать чтение из файла настроек
        String login = "";
        String useLog = "false";
        boolean isContinue = true;
        BufferedWriter fileLog = null;
        try {
            String[] user = controller.loadSettings(settingsName, fileLogName);
            login = user[0];
            useLog = user[1];
        }catch (Exception e){
            controller.println("Ошибка при открытии файла настроек!");
            isContinue = false;
        }
        if (useLog.equals("true")){
            try{
                fileLog = new BufferedWriter(new FileWriter(fileLogName, true));
                controller.log(fileLog, login + "\tстарт программы");
            }catch (IOException e){
                try{
                    fileLog = new BufferedWriter(new FileWriter(fileLogName));
                    controller.log(fileLog, login + "\tстарт программы");
                }catch (IOException exc){
                    view.println("Ошибка при открытии файла для логирования!");
                    view.println("Далее программа будет выполнена без логирования.");
                    useLog = "false";
                }
            }
        }
        if (isContinue) {
            Scanner inputMain = new Scanner(System.in);
            controller.printMenu();
            controller.println("Чтобы выполнить функцию, введите соответствующий номер из меню.");
            controller.print("Введите номер функции: ");
            int task = controller.inputLimitedInt(inputMain, 1, 9);
            while (task != 9) {
                switch (task) {
                    case 1 -> {
                        controller.inputDataFromFile(fileDB);
                        if (useLog.equals("true")) controller.log(fileLog, "чтение данных из базы");
                    }
                    case 2 -> controller.addTrailers(inputMain);
                    case 3 -> controller.addVehicles(inputMain);
                    case 4 -> controller.changeTrailer(inputMain);
                    case 5 -> controller.changeVehicle(inputMain);
                    case 6 -> controller.delTrailer(inputMain);
                    case 7 -> controller.delVehicle(inputMain);
                    case 8 -> {
                        controller.outputDataToFile(fileDB, controller.getTrailers(), controller.getVehicles());
                        if (useLog.equals("true")) controller.log(fileLog, "запись данных в базу");
                    }
                }
                task = controller.inputLimitedInt(inputMain, 1, 9);
            }
            controller.outputDataToFile(fileDB, controller.getTrailers(), controller.getVehicles());
            if (useLog.equals("true")){
                controller.log(fileLog, "запись данных в базу");
                controller.log(fileLog, login + "\tвыход из программы");
            }
        }
    }
}