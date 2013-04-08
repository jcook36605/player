/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "shot_rate")
@NamedQueries({
    @NamedQuery(name = "ShotRate.findAll", query = "SELECT s FROM ShotRate s"),
    @NamedQuery(name = "ShotRate.findById", query = "SELECT s FROM ShotRate s WHERE s.id = :id"),
    @NamedQuery(name = "ShotRate.findByRate", query = "SELECT s FROM ShotRate s WHERE s.rate = :rate"),
    @NamedQuery(name = "ShotRate.findByDate", query = "SELECT s FROM ShotRate s WHERE s.date = :date"),
    @NamedQuery(name = "ShotRate.findByRatedBy", query = "SELECT s FROM ShotRate s WHERE s.ratedBy = :ratedBy"),
    @NamedQuery(name = "ShotRate.findByRatedById", query = "SELECT s FROM ShotRate s WHERE s.ratedById = :ratedById")})
public class ShotRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "Rate")
    private Integer rate;
    @Basic(optional = false)
    @Column(name = "Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "RATED_BY", length = 200)
    private String ratedBy;
    @Column(name = "RATED_BY_ID")
    private Integer ratedById;
    @ManyToMany(mappedBy = "shotRateList")
    private List<PlayerShot> playerShotList;
    @JoinColumn(name = "PLAYER_SHOT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private PlayerShot playerShotId;

    public ShotRate() {
    }

    public ShotRate(Long id) {
        this.id = id;
    }

    public ShotRate(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(String ratedBy) {
        this.ratedBy = ratedBy;
    }

    public Integer getRatedById() {
        return ratedById;
    }

    public void setRatedById(Integer ratedById) {
        this.ratedById = ratedById;
    }

    public List<PlayerShot> getPlayerShotList() {
        return playerShotList;
    }

    public void setPlayerShotList(List<PlayerShot> playerShotList) {
        this.playerShotList = playerShotList;
    }

    public PlayerShot getPlayerShotId() {
        return playerShotId;
    }

    public void setPlayerShotId(PlayerShot playerShotId) {
        this.playerShotId = playerShotId;
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
        if (!(object instanceof ShotRate)) {
            return false;
        }
        ShotRate other = (ShotRate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.ShotRate[ id=" + id + " ]";
    }
    
}
