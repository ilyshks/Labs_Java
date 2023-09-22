public class Main {
    public static void main(String[] args) {
        Trailer[] trailers = new Trailer[3];
        trailers[0] = new Trailer("Креон", "1800", 500, 0);
        trailers[1] = new Trailer("Русич", "203", 540, 0);
        trailers[2] = new Trailer("Swift", "BaseCamp", 1000, 3);

        IUseAuto[] vehicles = new IUseAuto[4];
        vehicles[0] = new Bike("Иж", "Планета", 200, 2, 90);
        vehicles[1] = new Car("ВАЗ", "2105", 400, 5, 150, trailers[0]);
        vehicles[2] = new Truck("ГАЗ", "2705", 1350, 2, 115, trailers[2]);
        vehicles[3] = new Bus("ЛиАЗ", "5291", 13600, 48, 120);

        for (IUseAuto vehicle: vehicles){
            vehicle.info();
            System.out.println();
        }
    }
}