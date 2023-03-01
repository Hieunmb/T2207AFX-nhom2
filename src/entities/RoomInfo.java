package entities;

public class RoomInfo {
    private int id;
    private String name;
    private String roomtype;
    private String floor;
    private String status;
    private double price;

    public RoomInfo(int id, String name, String roomtype, String floor, String status, double price) {
        this.id = id;
        this.name = name;
        this.roomtype = roomtype;
        this.floor = floor;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public RoomInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoomInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public RoomInfo setRoomtype(String roomtype) {
        this.roomtype = roomtype;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public RoomInfo setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RoomInfo setStatus(String status) {
        this.status = status;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public RoomInfo setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getId());
    }
}
