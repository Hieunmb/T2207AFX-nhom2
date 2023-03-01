package entities;

public class Floor {
    private int id;
    private String name;

    public Floor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Floor setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Floor setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

