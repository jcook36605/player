/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "player", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findById", query = "SELECT p FROM Player p WHERE p.id = :id"),
    @NamedQuery(name = "Player.findByLastName", query = "SELECT p FROM Player p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Player.findByFirstName", query = "SELECT p FROM Player p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Player.findByAddress", query = "SELECT p FROM Player p WHERE p.address = :address"),
    @NamedQuery(name = "Player.findByCity", query = "SELECT p FROM Player p WHERE p.city = :city"),
    @NamedQuery(name = "Player.findByZipCode", query = "SELECT p FROM Player p WHERE p.zipCode = :zipCode"),
    @NamedQuery(name = "Player.findByDob", query = "SELECT p FROM Player p WHERE p.dob = :dob")})
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "LastName", nullable = false, length = 255)
    private String lastName;
    @Basic(optional = false)
    @Column(name = "FirstName", nullable = false, length = 255)
    private String firstName;
    @Column(name = "Address", length = 255)
    private String address;
    @Column(name = "City", length = 255)
    private String city;
    @Lob
    @Column(name = "Rate", length = 16777215)
    private String rate;
    @Column(name = "ZipCode", length = 15)
    private String zipCode;
    @Column(name = "Dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @ManyToMany(mappedBy = "playerList")
    private List<SessionGroup> sessionGroupList;

    public Player() {
    }

    public Player(Long id) {
        this.id = id;
    }

    public Player(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<SessionGroup> getSessionGroupList() {
        return sessionGroupList;
    }

    public void setSessionGroupList(List<SessionGroup> sessionGroupList) {
        this.sessionGroupList = sessionGroupList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.Player[ id=" + id + " ]";
    }
    
}
