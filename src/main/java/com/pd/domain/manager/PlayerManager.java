package com.pd.domain.manager;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 3/21/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerManager {

    private String firstName;
    private String lastName;
    private String city;
    private String address;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void reset(){
        firstName = null;
        lastName = null;
        city = null;
        address = null;
    }
}
