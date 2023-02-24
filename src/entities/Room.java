package entities;

public class Room {
    private int id;
    private String name;
    private String status;
    private int floor_id;
    private int roomtype_id;

    public Room(int id, String name, String status, int floor_id, int roomtype_id) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.floor_id = floor_id;
        this.roomtype_id = roomtype_id;
    }

    public int getId() {
        return id;
    }

    public Room setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Room setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getFloor_id() {
        return floor_id;
    }

    public Room setFloor_id(int floor_id) {
        this.floor_id = floor_id;
        return this;
    }

    public int getRoomtype_id() {
        return roomtype_id;
    }

    public Room setRoomtype_id(int roomtype_id) {
        this.roomtype_id = roomtype_id;
        return this;
    }
}
