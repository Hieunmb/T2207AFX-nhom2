package entities;

import java.util.Date;

public class Bills2 {
    private Integer id;
    private Integer customer_id;
    private Float price;
    private String checkinDate;
    private String checkoutDate;
    private String payments;

    private String status;

    public Bills2(Integer id, Integer customer_id, Float price, String checkinDate, String checkoutDate, String payments, String status) {
        this.id = id;
        this.customer_id = customer_id;
        this.price = price;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.payments = payments;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Bills2 setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public Bills2 setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Bills2 setPrice(Float price) {
        this.price = price;
        return this;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public Bills2 setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
        return this;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public Bills2 setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public String getPayments() {
        return payments;
    }

    public Bills2 setPayments(String payments) {
        this.payments = payments;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Bills2 setStatus(String status) {
        this.status = status;
        return this;
    }
}
