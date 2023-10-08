public abstract class Vehicle {
    private String mark;
    private String model;
    private double maxWeight;
    public Vehicle(){
        mark = "";
        model = "";
        maxWeight = 0;
    }
    public Vehicle(String mark, String model, double maxWeight){
        this.mark = mark;
        this.model = model;
        this.maxWeight = maxWeight;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }
}
