package entities;

import java.util.Date;

public class Bills {
    private Integer id;
    private Integer checkin_id;
    private Integer price;
    private Date checkoutDate;
    private String payments;

    public Bills(Integer id, Integer checkin_id, Integer price, Date checkoutDate, String payments) {
        this.id = id;
        this.checkin_id = checkin_id;
        this.price = price;
        this.checkoutDate = checkoutDate;
        this.payments = payments;
    }

    public Integer getId() {
        return id;
    }

    public Bills setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCheckin_id() {
        return checkin_id;
    }

    public Bills setCheckin_id(Integer checkin_id) {
        this.checkin_id = checkin_id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Bills setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Bills setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public String getPayments() {
        return payments;
    }

    public Bills setPayments(String payments) {
        this.payments = payments;
        return this;
    }
}
