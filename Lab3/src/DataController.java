import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    private static Data model;
    private static DataView view;

    public DataController(Data m, DataView v){
        model = m;
        view = v;
    }
    public ArrayList<Trailer> getTrailers(){
        return model.getTrailers();
    }
    public ArrayList<IUseAuto> getVehicles(){
        return model.getVehicles();
    }
    public void setTrailers(ArrayList<Trailer> other){
        model.setTrailers(other);
    }
    public void setVehicles(ArrayList<IUseAuto> other){
        model.setVehicles(other);
    }
    public void addTrailer(Trailer new_trailer){
        model.addTrailer(new_trailer);
    }
    public void addVehicle(IUseAuto new_vehicle){
        model.addVehicle(new_vehicle);
    }

    public void addTrailers(Scanner in){
        view.print("Введите кол-во прицепов: ");
        int cntTrailers = inputConsolePositiveInt(in);
        for(int i = 0; i < cntTrailers; i++){
            view.print("Введите марку прицепа: ");
            String mark = in.nextLine();
            view.print("Введите модель прицепа: ");
            String TrailerModel = in.nextLine();
            view.print("Введите макс. грузоподъёмность прицепа: ");
            double maxWeight = inputConsolePositiveDouble(in);
            view.print("Введите кол-во пассажиров, которое вмещает прицеп: ");
            int passengers = inputConsolePositiveInt(in);
            model.addTrailer(new Trailer(mark, TrailerModel, maxWeight, passengers));
        }
    }

    public void addVehicles(Scanner in){
        view.print("Введите кол-во транспортных средств: ");
        int cntVehicles = inputConsolePositiveInt(in);
        for(int i = 0; i < cntVehicles; i++){
            view.println("Выберите один из 4-х видов транспорта.");
            view.println("Мотоцикл - введите 1, автобус - 2, легковая машина - 3, грузовик - 4.");
            view.print("Введите номер ТС: ");
            int type = inputLimitedInt(in, 1, 4);
            view.print("Введите марку ТС: ");
            String mark = in.nextLine();
            view.print("Введите модель ТС: ");
            String vehicleModel = in.nextLine();
            view.print("Введите макс. грузоподъёмность ТС: ");
            double maxWeight = inputConsolePositiveDouble(in);
            view.print("Введите кол-во пассажиров ТС: ");
            int passengers = inputConsolePositiveInt(in);
            view.print("Введите макс. скорость ТС: ");
            double topSpeed = inputConsolePositiveDouble(in);
            view.println("ТС с прицепом? Да - введите 1, нет - введите 0.");
            view.print("Ваш ответ: ");
            int isUseTrailer = inputLimitedInt(in, 0, 1);
            if (isUseTrailer == 1){
                if (model.getTrailers().size() > 0) {
                    view.printTrailers(model.getTrailers());
                    view.print("Введите ID прицепа: ");
                    int TrailerID = inputLimitedInt(in, 1, model.getTrailers().size() - 1);
                    Trailer usedTrailer = model.getTrailers().get(TrailerID);
                    switch (type) {
                        case (1) -> addVehicle(new Bike(mark, vehicleModel, maxWeight, passengers, topSpeed, usedTrailer));
                        case (2) -> addVehicle(new Bus(mark, vehicleModel, maxWeight, passengers, topSpeed, usedTrailer));
                        case (3) -> addVehicle(new Car(mark, vehicleModel, maxWeight, passengers, topSpeed, usedTrailer));
                        case (4) -> addVehicle(new Truck(mark, vehicleModel, maxWeight, passengers, topSpeed, usedTrailer));
                    }
                }else{
                    view.println("Нет доступных прицепов.");
                }
            }else{
                switch (type) {
                    case (1) -> addVehicle(new Bike(mark, vehicleModel, maxWeight, passengers, topSpeed));
                    case (2) -> addVehicle(new Bus(mark, vehicleModel, maxWeight, passengers, topSpeed));
                    case (3) -> addVehicle(new Car(mark, vehicleModel, maxWeight, passengers, topSpeed));
                    case (4) -> addVehicle(new Truck(mark, vehicleModel, maxWeight, passengers, topSpeed));
                }
            }

        }
    }

    public void changeTrailer(Scanner in){
        int cntTrailers = model.getTrailers().size();
        if (cntTrailers == 0){
            view.println("Нет прицепов в наличии.");
            return;
        }
        view.printTrailers(model.getTrailers());
        view.print("Введите ID прицепа, данные которого хотите изменить: ");
        int index = inputLimitedInt(in, 1, cntTrailers - 1);

        String mark = model.getTrailers().get(index).getMark();
        String TrailerModel = model.getTrailers().get(index).getModel();
        double maxWeight = model.getTrailers().get(index).getMaxWeight();
        int passengers = model.getTrailers().get(index).getPassengers();

        view.println("Вы хотите поменять марку? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeMark = inputLimitedInt(in, 0, 1);
        if (changeMark == 1){
            view.print("Введите новую марку: ");
            mark = in.nextLine();
        }
        view.println("Вы хотите поменять модель? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeModel = inputLimitedInt(in, 0, 1);
        if (changeModel == 1){
            view.print("Введите новую модель: ");
            TrailerModel = in.nextLine();
        }
        view.println("Вы хотите поменять макс. грузоподъёмность? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeMaxWeight = inputLimitedInt(in, 0, 1);
        if (changeMaxWeight == 1){
            view.print("Введите новую макс. грузоподъёмность: ");
            maxWeight = inputConsolePositiveDouble(in);
        }
        view.println("Вы хотите поменять кол-во пассажиров? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeMaxPassengers = inputLimitedInt(in, 0, 1);
        if (changeMaxPassengers == 1){
            view.print("Введите новое кол-во пассажиров: ");
            passengers = inputConsolePositiveInt(in);
        }
        model.changeTrailer(index, mark, TrailerModel, maxWeight, passengers);
    }
    public void changeVehicle(Scanner in){
        int cntVehicles = model.getVehicles().size();
        if (cntVehicles == 0){
            view.println("Нет ТС в наличии.");
            return;
        }
        view.printVehicles(model.getVehicles());
        view.print("Введите ID ТС, данные которого хотите изменить: ");
        int index = inputLimitedInt(in, 1, cntVehicles - 1);

        String mark = model.getVehicles().get(index).getMark();
        String vehicleModel = model.getVehicles().get(index).getModel();
        double maxWeight = model.getVehicles().get(index).getMaxWeight();
        int passengers = model.getVehicles().get(index).getPassengers();
        double topSpeed = model.getVehicles().get(index).getTopSpeed();
        Trailer usedTrailer = model.getVehicles().get(index).getUsedTrailer();

        view.println("Вы хотите поменять марку? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeMark = inputLimitedInt(in, 0, 1);
        if (changeMark == 1){
            view.print("Введите новую марку: ");
            mark = in.nextLine();
        }
        view.println("Вы хотите поменять модель? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeModel = inputLimitedInt(in, 0, 1);
        if (changeModel == 1){
            view.print("Введите новую модель: ");
            vehicleModel = in.nextLine();
        }
        view.println("Вы хотите поменять макс. грузоподъёмность? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeMaxWeight = inputLimitedInt(in, 0, 1);
        if (changeMaxWeight == 1){
            view.print("Введите новую макс. грузоподъёмность: ");
            maxWeight = inputConsolePositiveDouble(in);
        }
        view.println("Вы хотите поменять кол-во пассажиров? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeMaxPassengers = inputLimitedInt(in, 0, 1);
        if (changeMaxPassengers == 1){
            view.print("Введите новое кол-во пассажиров: ");
            passengers = inputConsolePositiveInt(in);
        }
        view.println("Вы хотите поменять макс. скорость? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeTopSpeed = inputLimitedInt(in, 0, 1);
        if (changeTopSpeed == 1){
            view.print("Введите новую макс. скорость: ");
            topSpeed = inputConsolePositiveDouble(in);
        }
        view.println("Вы хотите поменять используемый прицеп? Да - 1, нет - 0.");
        view.print("Ваш ответ: ");
        int changeUsedTrailer = inputLimitedInt(in, 0, 1);
        if (changeUsedTrailer == 1){
            if (model.getTrailers().size() > 0) {
                view.printTrailers(model.getTrailers());
                view.print("Введите ID прицепа: ");
                int TrailerID = inputLimitedInt(in, 1, model.getTrailers().size() - 1);
                usedTrailer = model.getTrailers().get(TrailerID);
            }else{
                view.println("Нет доступных прицепов.");
            }
        }
        model.changeVehicle(index, mark, vehicleModel, maxWeight, passengers, topSpeed, usedTrailer);
    }
    public void delTrailer(Scanner in){
        if (model.getTrailers().size() == 0){
            view.println("Нет доступных прицепов.");
            return;
        }
        view.printTrailers(model.getTrailers());
        view.print("Введите ID прицепа, который хотите удалить: ");
        int index = inputLimitedInt(in, 1, model.getTrailers().size() - 1);
        model.delTrailer(index);
    }
    public void delVehicle(Scanner in){
        if (model.getVehicles().size() == 0){
            view.println("Нет доступных транспортных средств.");
            return;
        }
        view.printVehicles(model.getVehicles());
        view.print("Введите ID ТС, который хотите удалить: ");
        int index = inputLimitedInt(in, 1, model.getVehicles().size() - 1);
        model.delVehicle(index);
    }
    public void printVehicleInfo(IUseAuto vehicle){
        view.printVehicleInfo(vehicle);
    }
    public void printVehicleDrive(IUseAuto vehicle){
        view.printVehicleDrive(vehicle);
    }
    public void print(String str){
        view.print(str);
    }
    public void println(String str){
        view.println(str);
    }
    public void printTrailers(ArrayList<Trailer> trailers){
        view.printTrailers(trailers);
    }
    public void printVehicles(ArrayList<IUseAuto> vehicles){
        view.printVehicles(vehicles);
    }
    public void inputDataFromFile(String fileName){
        model.inputDataFromFile(fileName);
    }
    public void outputDataToFile(String fileName, ArrayList<Trailer> t, ArrayList<IUseAuto> v){
        view.outputDataToFile(fileName, t, v);
    }
    public void inputTrailersFromConsole(){
        Scanner in = new Scanner(System.in);
        view.print("Введите кол-во прицепов: ");
        int cntTrailers = inputConsolePositiveInt(in);
        for(int i = 0; i < cntTrailers; i++){
            view.print("Введите марку прицепа: ");
            String mark = in.nextLine();
            view.print("Введите модель прицепа: ");
            String model = in.nextLine();
            view.print("Максимальная грузоподъёмность прицепа: ");
            double maxWeight = inputConsolePositiveDouble(in);
            view.print("Введите кол-во пассажиров, которое может вместить прицеп: ");
            int passengers = inputConsolePositiveInt(in);
            Trailer newTrailer = new Trailer(mark, model, maxWeight, passengers);
            addTrailer(newTrailer);
        }
        in.close();
    }
    public void inputVehiclesFromConsole(){
        Scanner in = new Scanner(System.in);
        view.print("Введите кол-во машин: ");
        int cntVehicles = inputConsolePositiveInt(in);
        for(int i = 0; i < cntVehicles; i++){
            view.println("Что вы хотите добавить?");
            view.println("Мотоцикл - введите 1, автобус - 2, легковое авто - 3, грузовик - 4.");
            view.print("Введите число: ");
            int type = inputLimitedInt(in, 1, 4);
            view.print("Введите марку транспортного средства: ");
            String mark = in.nextLine();
            view.print("Введите модель транспортного средства: ");
            String autoModel = in.nextLine();
            view.print("Максимальная грузоподъёмность ТС: ");
            double maxWeight = inputConsolePositiveDouble(in);
            view.print("Введите кол-во пассажиров, которое может вместить ТС: ");
            int passengers = inputConsolePositiveInt(in);
            view.print("Введите максимальную скорость ТС: ");
            double topSpeed = inputConsolePositiveDouble(in);
            view.println("ТС с прицепом?");
            view.println("Да - введите целое число > 0, нет - любое другое.");
            boolean useTrailer = isUseTrailer(in);
            if(!useTrailer || model.getTrailers().size() == 0) {
                if (useTrailer) view.println("Нет доступных прицепов. ТС будет без прицепа!");
                switch (type) {
                    case (1) -> {
                        Bike newBike = new Bike(mark, autoModel, maxWeight, passengers, topSpeed);
                        addVehicle(newBike);
                    }
                    case (2) -> {
                        Bus newBus = new Bus(mark, autoModel, maxWeight, passengers, topSpeed);
                        addVehicle(newBus);
                    }
                    case (3) -> {
                        Car newCar = new Car(mark, autoModel, maxWeight, passengers, topSpeed);
                        addVehicle(newCar);
                    }
                    case (4) -> {
                        Truck newTruck = new Truck(mark, autoModel, maxWeight, passengers, topSpeed);
                        addVehicle(newTruck);
                    }
                }
            }else{
                view.println("Веберите прицеп по ID из доступных:");
                view.println("ID \tНазвание");
                for(int j = 0; j < model.getTrailers().size(); j++){
                    String info = (j + 1) + "\t" + model.getTrailers().get(j).shortInfo();
                    view.println(info);
                }
                int index = inputLimitedInt(in, 1, model.getTrailers().size() - 1);
                Trailer usedTrailer = model.getTrailers().get(index);
                switch (type) {
                    case (1) -> {
                        Bike newBike = new Bike(mark, autoModel, maxWeight, passengers, topSpeed, usedTrailer);
                        addVehicle(newBike);
                    }
                    case (2) -> {
                        Bus newBus = new Bus(mark, autoModel, maxWeight, passengers, topSpeed, usedTrailer);
                        addVehicle(newBus);
                    }
                    case (3) -> {
                        Car newCar = new Car(mark, autoModel, maxWeight, passengers, topSpeed, usedTrailer);
                        addVehicle(newCar);
                    }
                    case (4) -> {
                        Truck newTruck = new Truck(mark, autoModel, maxWeight, passengers, topSpeed, usedTrailer);
                        addVehicle(newTruck);
                    }
                }
            }
        }
        in.close();
    }
    public boolean isUseTrailer(Scanner input){
        while (true){
            try{
                int value = input.nextInt();
                return value > 0;
            }catch (Exception e){
                view.print("Введите заново целое число: ");
            }
        }
    }
    public int inputLimitedInt(Scanner input, int min, int max){
        while (true){
            try{
                int value = input.nextInt();
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
                int value = input.nextInt();
                if (value > 0) return value;
                else view.print("Введите заново целое положительное число: ");
            }catch (Exception e){
                view.print("Введите заново целое положительное число: ");
            }
        }
    }
    public double inputConsolePositiveDouble(Scanner input){
        while (true){
            try{
                double value = input.nextDouble();
                if (value > 0) return value;
                else view.print("Введите заново положительное число: ");
            }catch (Exception e){
                view.print("Введите заново положительное число: ");
            }
        }
    }
    public void printMenu(){
        view.menu();
    }
    public String[] loadSettings(String settingsName, String fileLogName){
        try (BufferedReader settings = new BufferedReader(new FileReader(settingsName))){
            String login = settings.readLine();
            String useLog = settings.readLine();
            view.println("Добро пожаловать, " + login);
            return new String[]{login, useLog};
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void log(BufferedWriter fileLog, String str){
        view.printLog(fileLog, str);
    }
}
