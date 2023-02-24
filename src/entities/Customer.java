package entities;

public class Customer {
    private int id;
    private String name;
    private String cccd;
    private String nationality;
    private String phone;
    private String gender;

    public Customer(int id, String name, String cccd, String nationality, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.cccd = cccd;
        this.nationality = nationality;
        this.phone = phone;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public Customer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getCccd() {
        return cccd;
    }

    public Customer setCccd(String cccd) {
        this.cccd = cccd;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Customer setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Customer setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
