package entities;

import java.sql.Date;

public class Bills {
    private Integer id;
    private int customer_id;
    private Double price;
    private String payment;
    private Date checkoutdate;

    public Bills(Integer id, int customer_id, Double price, String payment, Date checkoutdate) {
        this.id = id;
        this.customer_id = customer_id;
        this.price = price;
        this.payment = payment;
        this.checkoutdate = checkoutdate;
    }

    public Integer getId() {
        return id;
    }

    public Bills setId(int id) {
        this.id = id;
        return this;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public Bills setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Bills setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getPayment() {
        return payment;
    }

    public Bills setPayment(String payment) {
        this.payment = payment;
        return this;
    }

    public Date getCheckoutdate() {
        return checkoutdate;
    }

    public Bills setCheckoutdate(Date checkoutdate) {
        this.checkoutdate = checkoutdate;
        return this;
    }

    @Override
    public String toString(){
        return String.valueOf(this.getId());
    }
}
