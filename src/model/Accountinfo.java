package model;

import java.util.Date;

public class Accountinfo {
    public int no;
    Date dateOfOpening;
    String name;

    public Accountinfo(int no, Date dateOfOpening, String name) {
        this.no = no;
        this.dateOfOpening = dateOfOpening;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Accountinfo{" +
                "no=" + no +
                ", dateOfOpening=" + dateOfOpening +
                ", name='" + name + '\'' +
                '}';
    }
}