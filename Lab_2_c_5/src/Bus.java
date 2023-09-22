public class Bus extends Auto{
    Bus(){super();}
    Bus(String mark, String model, double maxWeight, int passengers, double topSpeed){
        super(mark, model, maxWeight, passengers, topSpeed);
    }
    Bus(String mark, String model, double maxWeight, int passengers, double topSpeed, Trailer usedTrailer){
        super(mark, model, maxWeight, passengers, topSpeed, usedTrailer);
    }
    public void drive(){
        if (getUsedTrailer() != null) System.out.println("Я еду на машине с прицепом!");
        else System.out.println("Я еду на машине!");
    }
}
