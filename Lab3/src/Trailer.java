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
    public Trailer copy(){
        return new Trailer(getMark(), getModel(), getMaxWeight(), passengers);
    }
    public boolean is_equal(Trailer other){
        return getMark().equals(other.getMark()) && getModel().equals(other.getModel());
    }
}
