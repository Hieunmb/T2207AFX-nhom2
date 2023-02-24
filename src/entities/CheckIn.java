package entities;

import java.sql.Date;

public class CheckIn {
    private int id;
    private int customer_id;
    private int room_id;
    private Date checkindate;
    private Date checkoutDate;
    private String note;

    public CheckIn(int id, int customer_id, int room_id, Date checkindate, Date checkoutDate, String note) {
        this.id = id;
        this.customer_id = customer_id;
        this.room_id = room_id;
        this.checkindate = checkindate;
        this.checkoutDate = checkoutDate;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public CheckIn setId(int id) {
        this.id = id;
        return this;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public CheckIn setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public int getRoom_id() {
        return room_id;
    }

    public CheckIn setRoom_id(int room_id) {
        this.room_id = room_id;
        return this;
    }

    public Date getCheckindate() {
        return checkindate;
    }

    public CheckIn setCheckindate(Date checkindate) {
        this.checkindate = checkindate;
        return this;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public CheckIn setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public String getNote() {
        return note;
    }

    public CheckIn setNote(String note) {
        this.note = note;
        return this;
    }
}
