import java.util.ArrayList;

public class Data {
    private ArrayList<Trailer> trailers;
    private ArrayList<IUseAuto> vehicles;

    public Data(ArrayList<Trailer> other_trailers, ArrayList<IUseAuto> other_vehicles){
        setTrailers(other_trailers);
        setVehicles(other_vehicles);
    }
    public ArrayList<Trailer> getTrailers(){
        ArrayList<Trailer> copy_trailers = new ArrayList<>(trailers.size());
        for(int i=0; i < trailers.size(); i++)
            copy_trailers.add(i, trailers.get(i).copy());
        return copy_trailers;
    }
    public ArrayList<IUseAuto> getVehicles(){
        ArrayList<IUseAuto> copy_vehicles = new ArrayList<>(vehicles.size());
        for(int i=0; i < vehicles.size(); i++)
            copy_vehicles.add(i, vehicles.get(i).copy());
        return copy_vehicles;
    }

    public void setTrailers(ArrayList<Trailer> other){
        if (other == null) return;
        trailers = new ArrayList<>(other.size());
        for(int i=0; i < other.size(); i++)
            trailers.add(i, other.get(i).copy());
    }
    public void setVehicles(ArrayList<IUseAuto> other){
        if (other == null) return;
        vehicles = new ArrayList<>(other.size());
        for(int i=0; i < other.size(); i++)
            vehicles.add(i, other.get(i).copy());
    }

    public void addTrailer(Trailer new_trailer){
        if (new_trailer == null) return;
        trailers.add(new_trailer.copy());
    }
    public void addVehicle(IUseAuto new_vehicle){
        if (new_vehicle == null) return;
        vehicles.add(new_vehicle.copy());
    }

    public void addTrailers(ArrayList<Trailer> new_trailers){
        if (new_trailers == null) return;
        for (Trailer new_trailer: new_trailers)
            trailers.add(new_trailer.copy());
    }

    public void addVehicles(ArrayList<IUseAuto> new_vehicles){
        if (new_vehicles == null) return;
        for (IUseAuto new_vehicle: new_vehicles)
            vehicles.add(new_vehicle.copy());
    }
}
