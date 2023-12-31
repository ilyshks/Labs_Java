public class Trailer extends Vehicle{
    private int passengers;
    public Trailer(){
        super();
        passengers = 0;
    }
    public Trailer(String mark, String model, double maxWeight, int passengers){
        super(mark, model, maxWeight);
        this.passengers = passengers;
    }
    public String shortInfo(){
        return getMark() + " " + getModel();
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
