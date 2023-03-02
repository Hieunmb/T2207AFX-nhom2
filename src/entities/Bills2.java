package entities;

import java.util.Date;

public class Bills2 {
    private Integer id;
    private Integer customer_id;
    private Integer price;
    private Date checkoutDate;
    private String payments;

    public Bills2(Integer id, Integer customer_id, Integer price, Date checkoutDate, String payments) {
        this.id = id;
        this.customer_id = customer_id;
        this.price = price;
        this.checkoutDate = checkoutDate;
        this.payments = payments;
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

    public Integer getPrice() {
        return price;
    }

    public Bills2 setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Bills2 setCheckoutDate(Date checkoutDate) {
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
}
