package entities;

import java.sql.Date;

public class CheckIn {
    private int id;
    private String nameCus;
    private String nameRoom;
    private Date checkindate;
    private Date checkoutDate;
    private String note;

    public CheckIn(int id, String nameCus, String nameRoom, Date checkindate, Date checkoutDate, String note) {
        this.id = id;
        this.nameCus = nameCus;
        this.nameRoom = nameRoom;
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

    public String getNameCus() {
        return nameCus;
    }

    public CheckIn setNameCus(String nameCus) {
        this.nameCus = nameCus;
        return this;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public CheckIn setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
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
