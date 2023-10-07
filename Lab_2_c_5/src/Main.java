import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
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

        for(IUseAuto vehicle: controller.getVehicles()){
            controller.printVehicleInfo(vehicle);
            controller.printVehicleDrive(vehicle);
        }
    }
}