package eu.wiegandt.example.model;

import java.util.Objects;
import java.util.UUID;

public class Address {
    //@Schema(hiddenFor = {"POST"} )
    private UUID id;
    private String address;
    private String city;
    private String zip;

    public Address() {
        super();
    }

    public Address(UUID id, String address, String city, String zip) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zip = zip;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address1 = (Address) o;
        return Objects.equals(getId(), address1.getId()) &&
                Objects.equals(getAddress(), address1.getAddress()) &&
                Objects.equals(getCity(), address1.getCity()) &&
                Objects.equals(getZip(), address1.getZip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getCity(), getZip());
    }
}