package entities;

import java.util.Date;

public class Bills {
    private int id;
    private int cus_id;
    private int price;
    private String payment;
    private Date checkoutdate;

    public Bills(int id, int cus_id, int price, String payment, Date checkoutdate) {
        this.id = id;
        this.cus_id = cus_id;
        this.price = price;
        this.payment = payment;
        this.checkoutdate = checkoutdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Date getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(Date checkoutdate) {
        this.checkoutdate = checkoutdate;
    }
}
