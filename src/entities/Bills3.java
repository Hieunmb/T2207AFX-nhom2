package entities;

public class Bills3 {
    private Integer id;
    private String name;
    private Float price;
    private String checkinDate;
    private String checkoutDate;
    private String payments;
    private String status;

    public Bills3(Integer id, String name, Float price, String checkinDate, String checkoutDate, String payments, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.payments = payments;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Bills3 setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bills3 setName(String name) {
        this.name = name;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Bills3 setPrice(Float price) {
        this.price = price;
        return this;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public Bills3 setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
        return this;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public Bills3 setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public String getPayments() {
        return payments;
    }

    public Bills3 setPayments(String payments) {
        this.payments = payments;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Bills3 setStatus(String status) {
        this.status = status;
        return this;
    }
}
