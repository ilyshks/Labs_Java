public class Bike extends Auto{
    Bike(){super();}
    Bike(String mark, String model, double maxWeight, int passengers, double topSpeed){
        super(mark, model, maxWeight, passengers, topSpeed);
    }
    Bike(String mark, String model, double maxWeight, int passengers, double topSpeed, Trailer usedTrailer){
        super(mark, model, maxWeight, passengers, topSpeed, usedTrailer);
    }
    public void drive(){
        if (getUsedTrailer() != null) System.out.println("Я еду на мотоцикле с прицепом!");
        else System.out.println("Я еду на мотоцикле!");
    }
    public Bike copy(){
        return new Bike(getMark(), getModel(), getMaxWeight(), getPassengers(), getTopSpeed(), getUsedTrailer());
    }
}