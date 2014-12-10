package example.hibernate.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

    @Column(name="street1")
    private String street;

    @Column(name="street2")
    private String streetAdditional;

    private String city;
    private String region;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="country_code")
    private String countryCode;


    public Address() {
    }
    public Address(String street, String streetAdditional, String city, String region, String postalCode, String countryCode) {
        this.street = street;
        this.streetAdditional = streetAdditional;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }


    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getStreetAdditional() {
        return streetAdditional;
    }
    public void setStreetAdditional(String streetAdditional) {
        this.streetAdditional = streetAdditional;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    @Override
    public String toString() {
        return String.format("street=%s, streetAdditional=%s, city=%s, region=%s, postalCode=%s, countryCode=%s", street, streetAdditional, city, region, postalCode, countryCode);
    }

}
