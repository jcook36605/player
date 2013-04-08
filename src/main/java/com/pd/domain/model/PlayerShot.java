/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "player_shot")
@NamedQueries({
    @NamedQuery(name = "PlayerShot.findAll", query = "SELECT p FROM PlayerShot p"),
    @NamedQuery(name = "PlayerShot.findByStatus", query = "SELECT p FROM PlayerShot p WHERE p.status = :status"),
    @NamedQuery(name = "PlayerShot.findByRate", query = "SELECT p FROM PlayerShot p WHERE p.rate = :rate"),
    @NamedQuery(name = "PlayerShot.findPlayerId", query = "SELECT p FROM PlayerShot p WHERE p.playerID.id = :playerId and p.shotID.shotTypeID.id = :shotId"),
    @NamedQuery(name = "PlayerShot.findPlayer", query = "SELECT p FROM PlayerShot p WHERE p.playerID.id = :playerId"),
    @NamedQuery(name = "PlayerShot.findById", query = "SELECT p FROM PlayerShot p WHERE p.id = :id")})
public class PlayerShot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "Status", length = 45)
    private String status;
    @Column(name = "Rate")
    private Integer rate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @JoinTable(name = "player_shot_rate", joinColumns = {
        @JoinColumn(name = "player_shot_id", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "shot_rate_id", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<ShotRate> shotRateList;
    @JoinColumn(name = "Player_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Player playerID;
    @JoinColumn(name = "Shot_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Shot shotID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playerShotId")
    private List<ShotRate> shotRateList1;
    @OneToMany(mappedBy = "playerShotID", fetch = FetchType.EAGER)
    private List<ShotTask> shotTaskList;

    public PlayerShot() {
        rate = new Integer(0);
        status = "";
    }

    public PlayerShot(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ShotRate> getShotRateList() {
        return shotRateList;
    }

    public void setShotRateList(List<ShotRate> shotRateList) {
        this.shotRateList = shotRateList;
    }

    public Player getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Player playerID) {
        this.playerID = playerID;
    }

    public Shot getShotID() {
        return shotID;
    }

    public void setShotID(Shot shotID) {
        this.shotID = shotID;
    }

    public List<ShotRate> getShotRateList1() {
        return shotRateList1;
    }

    public void setShotRateList1(List<ShotRate> shotRateList1) {
        this.shotRateList1 = shotRateList1;
    }

    public List<ShotTask> getShotTaskList() {
        return shotTaskList;
    }

    public void setShotTaskList(List<ShotTask> shotTaskList) {
        this.shotTaskList = shotTaskList;
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
        if (!(object instanceof PlayerShot)) {
            return false;
        }
        PlayerShot other = (PlayerShot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.PlayerShot[ id=" + id + " ]";
    }
    
}
