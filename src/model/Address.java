package model;

public class Address {
    String city;
    String state;
    int pin;

    public Address(String city, String state, int pin) {
        this.city = city;
        this.state = state;
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pin=" + pin +
                '}';
    }
}
