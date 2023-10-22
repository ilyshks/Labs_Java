public interface IUseAuto {
    void addTrailer(Trailer newTrailer);
    void drive();
    void info();
    void setMark(String mark);
    String short_info();
    IUseAuto copy();

    void setModel(String model);

    void setMaxWeight(double maxWeight);

    void setPassengers(int passengers);

    void setTopSpeed(double topSpeed);

    void setUsedTrailer(Trailer usedTrailer);

    String getMark();

    String getModel();

    double getMaxWeight();

    int getPassengers();

    double getTopSpeed();

    Trailer getUsedTrailer();
}
