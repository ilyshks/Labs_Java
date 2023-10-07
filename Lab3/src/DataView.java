public class DataView {
    public void printVehicleInfo(IUseAuto vehicle){
        vehicle.info();
        System.out.println();
    }
    public void printVehicleDrive(IUseAuto vehicle){
        vehicle.drive();
        System.out.println();
    }
}
