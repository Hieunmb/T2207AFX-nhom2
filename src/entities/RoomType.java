package entities;

public class RoomType {
    private int id;
    private String name;
    private double price;
    private int numberBeds;
    private int peopleMax;

    public RoomType(int id, String name, double price, int numberBeds, int peopleMax) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.numberBeds = numberBeds;
        this.peopleMax = peopleMax;
    }

    public int getId() {
        return id;
    }

    public RoomType setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoomType setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public RoomType setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getNumberBeds() {
        return numberBeds;
    }

    public RoomType setNumberBeds(int numberBeds) {
        this.numberBeds = numberBeds;
        return this;
    }

    public int getPeopleMax() {
        return peopleMax;
    }

    public RoomType setPeopleMax(int peopleMax) {
        this.peopleMax = peopleMax;
        return this;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
