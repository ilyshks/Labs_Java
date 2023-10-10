import java.io.*;
import java.util.ArrayList;

public class Data {
    private ArrayList<Trailer> trailers;
    private ArrayList<IUseAuto> vehicles;

    public Data(ArrayList<Trailer> other_trailers, ArrayList<IUseAuto> other_vehicles){
        setTrailers(other_trailers);
        setVehicles(other_vehicles);
    }
    public void inputDataFromFile(String fileName) {
        try (FileReader fin = new FileReader(fileName)) {
            BufferedReader file = new BufferedReader(fin);

            String[] line = file.readLine().split("\\s+");
            int trailersSize = Integer.parseInt(line[line.length-1]);
            trailers = new ArrayList<>(trailersSize);

            for (int i = 0; i < trailersSize; i++) {
                line = file.readLine().split("\\s+");
                String mark = line[line.length-1];
                line = file.readLine().split("\\s+");
                String model = line[line.length-1];
                line = file.readLine().split("\\s+");
                double maxWeight = Double.parseDouble(line[line.length-1]);
                line = file.readLine().split("\\s+");
                int passengers = Integer.parseInt(line[line.length-1]);
                Trailer loadTrailer = new Trailer(mark, model, maxWeight, passengers);
                addTrailer(loadTrailer);
            }

            line = file.readLine().split("\\s+");
            int vehiclesSize = Integer.parseInt(line[line.length-1]);
            vehicles = new ArrayList<>(vehiclesSize);

            for (int i = 0; i < vehiclesSize; i++) {
                line = file.readLine().split("\\s+");
                String className = line[line.length-1];
                line = file.readLine().split("\\s+");
                String mark = line[line.length-1];
                line = file.readLine().split("\\s+");
                String model = line[line.length-1];
                line = file.readLine().split("\\s+");
                double maxWeight = Double.parseDouble(line[line.length-1]);
                line = file.readLine().split("\\s+");
                int passengers = Integer.parseInt(line[line.length-1]);
                line = file.readLine().split("\\s+");
                double topSpeed = Double.parseDouble(line[line.length-1]);
                String trailerMark = file.readLine();
                if (trailerMark.equals("null")) {
                    switch (className) {
                        case ("Bike") -> {
                            Bike loadBike = new Bike(mark, model, maxWeight, passengers, topSpeed);
                            addVehicle(loadBike);
                        }
                        case ("Bus") -> {
                            Bus loadBus = new Bus(mark, model, maxWeight, passengers, topSpeed);
                            addVehicle(loadBus);
                        }
                        case ("Car") -> {
                            Car loadCar = new Car(mark, model, maxWeight, passengers, topSpeed);
                            addVehicle(loadCar);
                        }
                        case ("Truck") -> {
                            Truck loadTruck = new Truck(mark, model, maxWeight, passengers, topSpeed);
                            addVehicle(loadTruck);
                        }
                    }
                }else{
                    trailerMark = trailerMark.split("\\s+")[1];
                    line = file.readLine().split("\\s+");
                    String trailerModel = line[line.length-1];
                    line = file.readLine().split("\\s+");
                    double trailerMaxWeight = Double.parseDouble(line[line.length - 1]);
                    line = file.readLine().split("\\s+");
                    int trailerPassengers = Integer.parseInt(line[line.length - 1]);
                    Trailer trailer = new Trailer(trailerMark, trailerModel, trailerMaxWeight, trailerPassengers);
                    switch (className) {
                        case ("Bike") -> {
                            Bike loadBike = new Bike(mark, model, maxWeight, passengers, topSpeed, trailer);
                            addVehicle(loadBike);
                        }
                        case ("Bus") -> {
                            Bus loadBus = new Bus(mark, model, maxWeight, passengers, topSpeed, trailer);
                            addVehicle(loadBus);
                        }
                        case ("Car") -> {
                            Car loadCar = new Car(mark, model, maxWeight, passengers, topSpeed, trailer);
                            addVehicle(loadCar);
                        }
                        case ("Truck") -> {
                            Truck loadTruck = new Truck(mark, model, maxWeight, passengers, topSpeed, trailer);
                            addVehicle(loadTruck);
                        }
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public void changeTrailer(int index, String mark, String model, double maxWeight, int passengers){
        trailers.get(index).setMark(mark);
        trailers.get(index).setModel(model);
        trailers.get(index).setMaxWeight(maxWeight);
        trailers.get(index).setPassengers(passengers);
    }
    public void changeVehicle(int index, String mark, String model, double maxWeight, int passengers, double topSpeed, Trailer usedTrailer){
        vehicles.get(index).setMark(mark);
        vehicles.get(index).setModel(model);
        vehicles.get(index).setMaxWeight(maxWeight);
        vehicles.get(index).setPassengers(passengers);
        vehicles.get(index).setTopSpeed(topSpeed);
        vehicles.get(index).setUsedTrailer(usedTrailer);
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
    public void delTrailer(int index){
        trailers.remove(index);
    }
    public void delVehicle(int index){
        vehicles.remove(index);
    }
}
