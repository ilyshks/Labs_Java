public abstract class Auto extends Vehicle implements IUseAuto{
    private int passengers;
    private double topSpeed;
    private Trailer usedTrailer;

    public Auto(){
        super();
        passengers = 2;
        topSpeed = 50;
        usedTrailer = null;
    }
    public Auto(String mark, String model, double maxWeight, int passengers, double topSpeed){
        super(mark, model, maxWeight);
        this.passengers = passengers;
        this.topSpeed = topSpeed;
        this.usedTrailer = null;
    }
    public Auto(String mark, String model, double maxWeight, int passengers, double topSpeed, Trailer usedTrailer){
        super(mark, model, maxWeight);
        this.passengers = passengers;
        this.topSpeed = topSpeed;
        this.usedTrailer = usedTrailer;
        addTrailer(usedTrailer);
    }
    public void addTrailer(Trailer newTrailer){
        if (newTrailer != null) {
            usedTrailer = newTrailer;
            passengers += newTrailer.getPassengers();
            setMaxWeight(getMaxWeight() + newTrailer.getMaxWeight());
            setTopSpeed(getTopSpeed() - 20);
        }
    }
    public abstract void drive();
    public void info(){
        System.out.println(getMark() + " " + getModel());
        System.out.println("Характеристики:");
        System.out.println("Грузоподъёмность\t" + getMaxWeight() + " кг");
        System.out.println("Кол-во пассажиров\t" + passengers);
        System.out.println("Макс. скорость\t" + topSpeed + " км/ч");
        if (usedTrailer != null) System.out.println("Прицеп\t" + usedTrailer.shortInfo());
        else System.out.println("Прицеп\t" + "нет");
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    public Trailer getUsedTrailer() {
        return usedTrailer;
    }

    public void setUsedTrailer(Trailer usedTrailer) {
        this.usedTrailer = usedTrailer;
    }

}