import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Data {
    private ArrayList<IUseAuto> vehiclesArrayList;
    private LinkedList<IUseAuto> vehiclesLinkedList;
    private HashMap<Integer, IUseAuto> vehiclesHashMap;
    private final CreateCollections creator;
    private ArrayList<IUseAuto> researchArray;

    public Data(ArrayList<IUseAuto> vArrayList, LinkedList<IUseAuto> vLinkedList, HashMap<Integer,
            IUseAuto> vHashMap, CreateCollections creator){
        vehiclesArrayList = vArrayList;
        vehiclesLinkedList = vLinkedList;
        vehiclesHashMap = vHashMap;
        this.creator = creator;
        researchArray = new ArrayList<>();
    }
    public ArrayList<IUseAuto> getResearchArray(){
        return researchArray;
    }
    public IUseAuto createAuto(){
        return creator.createAuto();
    }
    public void createData(int length){
        vehiclesArrayList = creator.createArrayList(length);
        vehiclesLinkedList = creator.createLinkedList(length);
        vehiclesHashMap = creator.createHashMap(length);
    }
    public long addVehicleToResearchArrayList(IUseAuto new_vehicle){
        if (new_vehicle == null) return 0;
        long start = System.nanoTime();
        researchArray.add(new_vehicle);
        long end = System.nanoTime();
        return end - start;
    }
    public long addVehicleToArrayList(IUseAuto new_vehicle){
        if (new_vehicle == null) return 0;
        long start = System.nanoTime();
        vehiclesArrayList.add(new_vehicle.copy());
        long end = System.nanoTime();
        return end - start;
    }
    public long addVehicleToLinkedList(IUseAuto new_vehicle){
        if (new_vehicle == null) return 0;
        long start = System.nanoTime();
        vehiclesLinkedList.add(new_vehicle.copy());
        long end = System.nanoTime();
        return end - start;
    }

    public long addVehicleToHashMap(IUseAuto new_vehicle){
        if (new_vehicle == null) return 0;
        int size = vehiclesHashMap.size();
        long start = System.nanoTime();
        vehiclesHashMap.put(size + 1, new_vehicle.copy());
        long end = System.nanoTime();
        return end - start;
    }

    public long removeVehicleFromArrayList() {
        Random rand = new Random();
        int index = rand.nextInt(vehiclesArrayList.size());
        long start = System.nanoTime();
        vehiclesArrayList.remove(index);
        long end = System.nanoTime();
        return end - start;
    }

    public long removeVehicleFromLinkedList() {
        Random rand = new Random();
        int index = rand.nextInt(vehiclesLinkedList.size());
        long start = System.nanoTime();
        vehiclesLinkedList.remove(index);
        long end = System.nanoTime();
        return end - start;
    }

    public long removeVehicleFromHashMap() {
        Random rand = new Random();
        int index = rand.nextInt(vehiclesHashMap.size());
        long start = System.nanoTime();
        vehiclesHashMap.remove(index);
        long end = System.nanoTime();
        return end - start;
    }
}
