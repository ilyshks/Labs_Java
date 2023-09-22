public class Truck extends Auto{
    Truck(){super();}
    Truck(String mark, String model, double maxWeight, int passengers, double topSpeed){
        super(mark, model, maxWeight, passengers, topSpeed);
    }
    Truck(String mark, String model, double maxWeight, int passengers, double topSpeed, Trailer usedTrailer){
        super(mark, model, maxWeight, passengers, topSpeed, usedTrailer);
    }
    public void drive(){
        if (getUsedTrailer() != null) System.out.println("Я еду на грузовике с прицепом!");
        else System.out.println("Я еду на грузовике!");
    }
}
