import java.util.*;

public class CreateCollections {
    final private ArrayList<String> marks;
    final private ArrayList<String> models;
    final private ArrayList<String> trailerMarks;
    final private ArrayList<String> trailerModels;
    private ArrayList<String> createCopy(ArrayList<String> array){
        ArrayList<String> copyArray = new ArrayList<>(array.size());
        for(int i = 0; i < array.size(); i++){
            copyArray.add(i, array.get(i));
        }
        return copyArray;
    }

    public CreateCollections(ArrayList<String> marks, ArrayList<String> models, ArrayList<String> tMarks,
                             ArrayList<String> tModels){
        this.marks = createCopy(marks);
        this.models = createCopy(models);
        this.trailerMarks = createCopy(tMarks);
        this.trailerModels = createCopy(tModels);
    }

    public Trailer createTrailer(){
        Random rand = new Random();
        String mark = trailerMarks.get(rand.nextInt(trailerMarks.size()));
        String model = trailerModels.get(rand.nextInt(trailerModels.size()));
        return new Trailer(mark, model, rand.nextDouble(200, 1000), rand.nextInt(5));
    }
    public IUseAuto createAuto(){
        Random rand = new Random();
        int classType = rand.nextInt(4);
        IUseAuto testClass = null;
        String testMark = marks.get(rand.nextInt(marks.size()));
        String testModel = models.get(rand.nextInt(models.size()));
        double testMaxWeight = rand.nextDouble(1000);
        double testTopSpeed = rand.nextDouble(80, 250);
        int useTrailer = rand.nextInt(2);
        Trailer testTrailer = createTrailer();

        switch (classType) {
            case 0 -> testClass = new Bike(testMark, testModel, testMaxWeight, rand.nextInt(1, 4), testTopSpeed);
            case 1 -> testClass = new Bus(testMark, testModel, testMaxWeight, rand.nextInt(3, 20), testTopSpeed);
            case 2 -> testClass = new Car(testMark, testModel, testMaxWeight, rand.nextInt(3, 20), testTopSpeed);
            case 3 -> testClass = new Truck(testMark, testModel, testMaxWeight, rand.nextInt(3, 20), testTopSpeed);
        }
        if (useTrailer == 1) testClass.addTrailer(testTrailer);
        return testClass;
    }
    public ArrayList<IUseAuto> createArrayList(int length){
        return new ArrayList<>(length);
    }
    public LinkedList<IUseAuto> createLinkedList(int length){
        return new LinkedList<>();
    }
    public HashMap<Integer, IUseAuto> createHashMap(int length){
        return new HashMap<>();
    }
}
