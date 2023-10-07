import java.util.ArrayList;

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

    public void addTrailers(ArrayList<Trailer> new_trailers){
        model.addTrailers(new_trailers);
    }

    public void addVehicles(ArrayList<IUseAuto> new_vehicles){
        model.addVehicles(new_vehicles);
    }
    public void printVehicleInfo(IUseAuto vehicle){
        view.printVehicleInfo(vehicle);
    }
    public void printVehicleDrive(IUseAuto vehicle){
        view.printVehicleDrive(vehicle);
    }
}
