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
@Table(name = "shot", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@NamedQueries({
    @NamedQuery(name = "Shot.findAll", query = "SELECT s FROM Shot s"),
    @NamedQuery(name = "Shot.findById", query = "SELECT s FROM Shot s WHERE s.id = :id"),
    @NamedQuery(name = "Shot.findByName", query = "SELECT s FROM Shot s WHERE s.name = :name"),
    @NamedQuery(name = "Shot.findByDescription", query = "SELECT s FROM Shot s WHERE s.description = :description")})
public class Shot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "Name", length = 255)
    private String name;
    @Column(name = "Description", length = 1000)
    private String description;
    @ManyToMany(mappedBy = "shotList")
    private List<Recommendation> recommendationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shotID")
    private List<PlayerShot> playerShotList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shotID")
    private List<GroupShot> groupShotList;
    @JoinColumn(name = "Shot_Type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ShotType shotTypeID;

    public Shot() {
    }

    public Shot(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recommendation> getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(List<Recommendation> recommendationList) {
        this.recommendationList = recommendationList;
    }

    public List<PlayerShot> getPlayerShotList() {
        return playerShotList;
    }

    public void setPlayerShotList(List<PlayerShot> playerShotList) {
        this.playerShotList = playerShotList;
    }

    public List<GroupShot> getGroupShotList() {
        return groupShotList;
    }

    public void setGroupShotList(List<GroupShot> groupShotList) {
        this.groupShotList = groupShotList;
    }

    public ShotType getShotTypeID() {
        return shotTypeID;
    }

    public void setShotTypeID(ShotType shotTypeID) {
        this.shotTypeID = shotTypeID;
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
        if (!(object instanceof Shot)) {
            return false;
        }
        Shot other = (Shot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.Shot[ id=" + id + " ]";
    }
    
}
